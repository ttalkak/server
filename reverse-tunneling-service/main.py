import socket
import select
import threading
import logging

logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

class TunnelServer:
    def __init__(self, listen_port, forward_port):
        self.listen_port = listen_port
        self.forward_port = forward_port
        self.internal_client = None

    def start(self):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        
        try:
            server_socket.bind(('0.0.0.0', self.listen_port))
            server_socket.listen(5)
            logging.info(f"Tunnel server listening on port {self.listen_port}")
        except Exception as e:
            logging.error(f"Failed to start server: {e}")
            return

        while True:
            try:
                client_socket, addr = server_socket.accept()
                logging.info(f"New connection from {addr}")
                threading.Thread(target=self.handle_new_connection, args=(client_socket,)).start()
            except Exception as e:
                logging.error(f"Error accepting connection: {e}")

    def handle_new_connection(self, client_socket):
        print(client_socket)
        try:
            # 클라이언트 타입 확인
            client_type = client_socket.recv(1024).decode().strip()
            
            if client_type == "INTERNAL":
                self.handle_internal_client(client_socket)
            else:
                self.handle_external_client(client_socket)
        except Exception as e:
            logging.error(f"Error in handle_new_connection: {e}")
            client_socket.close()

    def handle_internal_client(self, client_socket):
        if self.internal_client is not None:
            logging.warning("Another internal client tried to connect. Rejecting.")
            client_socket.close()
            return

        self.internal_client = client_socket
        logging.info("Internal client connected")
        
        try:
            while True:
                data = self.internal_client.recv(1024)
                if not data:
                    raise Exception("Connection closed")
        except Exception as e:
            logging.error(f"Internal client disconnected: {e}")
        finally:
            self.internal_client = None

    def handle_external_client(self, external_client):
        if self.internal_client is None:
            logging.error("No internal client connected. Closing external connection.")
            external_client.close()
            return

        try:
            self.internal_client.send(b"new_connection")
            logging.info("Notified internal client of new connection")
            
            while True:
                readable, _, _ = select.select([external_client, self.internal_client], [], [], 1)
                
                if external_client in readable:
                    data = external_client.recv(4096)
                    if not data:
                        logging.debug("No data received from external client, breaking the loop")
                        break
                    logging.debug(f"Received {len(data)} bytes from external client, sending to internal client")
                    self.internal_client.send(data)
                
                if self.internal_client in readable:
                    data = self.internal_client.recv(4096)
                    if not data:
                        logging.debug("No data received from internal client, breaking the loop")
                        break
                    logging.debug(f"Received {len(data)} bytes from internal client, sending to external client")
                    external_client.send(data)
        except Exception as e:
            logging.error(f"Error in handle_external_client: {e}")
        finally:
            logging.info("Closing external client connection")
            external_client.close()

if __name__ == "__main__":
    tunnel_server = TunnelServer(9999, 8000)
    tunnel_server.start()