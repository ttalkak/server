import socket

LOCAL_SERVER_HOST = '127.0.0.1'
LOCAL_SERVER_PORT = 8080

def start_local_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((LOCAL_SERVER_HOST, LOCAL_SERVER_PORT))
    server_socket.listen(5)
    print(f"로컬 서버가 {LOCAL_SERVER_PORT} 포트에서 대기 중...")

    while True:
        client_socket, _ = server_socket.accept()
        data = client_socket.recv(4096)
        print("로컬 서버에서 받은 데이터:", data.decode('utf-8'))
        client_socket.sendall(data)  # Echo 서버: 받은 데이터를 그대로 반환
        client_socket.close()

if __name__ == "__main__":
    start_local_server()