import socket

CLOUD_SERVER_HOST = '34.64.228.181'
CLOUD_SERVER_PORT = 9999

def connect_to_cloud():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((CLOUD_SERVER_HOST, CLOUD_SERVER_PORT))
    print(f"로컬 클라이언트가 {CLOUD_SERVER_HOST}:{CLOUD_SERVER_PORT}에 연결됨")

    while True:
        data = input("보낼 데이터: ")
        client_socket.sendall(data.encode('utf-8'))

        response = client_socket.recv(4096)
        print("응답:", response.decode('utf-8'))

if __name__ == "__main__":
    connect_to_cloud()