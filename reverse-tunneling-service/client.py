import socket
import select
import threading
import time
import logging

logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

class ReverseTunnel:
    def __init__(self, tunnel_host, tunnel_port, local_host, local_port):
        self.tunnel_host = tunnel_host
        self.tunnel_port = tunnel_port
        self.local_host = local_host
        self.local_port = local_port

    def start(self):
        while True:
            try:
                tunnel_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                logging.info(f"Attempting to connect to tunnel server at {self.tunnel_host}:{self.tunnel_port}")
                tunnel_socket.connect((self.tunnel_host, self.tunnel_port))
                logging.info(f"Connected to tunnel server at {self.tunnel_host}:{self.tunnel_port}")
                
                # 내부 클라이언트임을 서버에 알림
                tunnel_socket.send(b"INTERNAL\n")

                while True:
                    logging.info("Waiting for incoming connection through tunnel...")
                    signal = tunnel_socket.recv(1024)
                    if signal == b"new_connection":
                        logging.info("Received signal for new connection")
                        threading.Thread(target=self.handle_tunnel, args=(self.create_tunnel_socket(),)).start()
                    elif not signal:
                        logging.warning("Tunnel server closed the connection")
                        break
                    else:
                        logging.warning(f"Received unexpected signal: {signal}")
            except ConnectionRefusedError:
                logging.error(f"Connection to {self.tunnel_host}:{self.tunnel_port} refused. Retrying in 10 seconds...")
                time.sleep(10)
            except Exception as e:
                logging.error(f"An error occurred: {e}. Retrying in 10 seconds...")
                time.sleep(10)

    def create_tunnel_socket(self):
        tunnel_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        tunnel_socket.connect((self.tunnel_host, self.tunnel_port))
        return tunnel_socket

    def handle_tunnel(self, tunnel_socket):
        try:
            local_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            local_socket.connect((self.local_host, self.local_port))
            logging.info(f"Connected to local server at {self.local_host}:{self.local_port}")

            while True:
                readable, _, _ = select.select([tunnel_socket, local_socket], [], [], 10)
                print(readable)
                if tunnel_socket in readable:
                    data = tunnel_socket.recv(4096)
                    if not data:
                        logging.debug("No data received from tunnel socket, breaking the loop")
                        break
                    logging.debug(f"Received {len(data)} bytes from tunnel, sending to local server")
                    local_socket.sendall(data)
                
                if local_socket in readable:
                    data = local_socket.recv(4096)
                    print("Data: ", data)
                    if not data:
                        logging.debug("No data received from local server, breaking the loop")
                        break
                    logging.debug(f"Received {len(data)} bytes from local server, sending through tunnel")
                    tunnel_socket.sendall(data)

        except socket.timeout:
            logging.error("Connection timed out")
        except Exception as e:
            logging.error(f"Error in handle_tunnel: {e}")
        finally:
            logging.info("Closing connection")
            local_socket.close()
            tunnel_socket.close()

if __name__ == "__main__":
    tunnel = ReverseTunnel("34.64.228.181", 9999, "localhost", 8000)
    tunnel.start()