# internal_client.py

import socket

EXTERNAL_SERVER_HOST = '34.64.228.181'  # 외부 서버의 IP 주소
EXTERNAL_SERVER_PORT = 9999  # 외부 서버 포트

def start_client():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((EXTERNAL_SERVER_HOST, EXTERNAL_SERVER_PORT))
        print(f"Connected to external server {EXTERNAL_SERVER_HOST}:{EXTERNAL_SERVER_PORT}")

        while True:
            print(client_socket.recv(4096))
            # 이곳에서 데이터를 처리하거나 전송하는 로직을 추가할 수 있습니다.
            pass

if __name__ == "__main__":
    start_client()