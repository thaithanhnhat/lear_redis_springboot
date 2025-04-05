# 🚀 Hướng Dẫn Cài Đặt Redis và Sử Dụng Redis trong Spring Boot

## 1. Cài Đặt Redis trên Windows

### 🧑‍💻 Cài Đặt Redis

Redis không chính thức hỗ trợ Windows, nhưng bạn có thể sử dụng một trong các phương pháp dưới đây:

### A. Cài Đặt Redis qua WSL (Windows Subsystem for Linux)

1. **Cài WSL** (Windows Subsystem for Linux) từ Microsoft Store.
2. **Cài đặt Redis trên Ubuntu (WSL)**:
    ```bash
    sudo apt update
    sudo apt install redis-server
    sudo service redis-server start
    ```

### B. Cài Đặt Redis từ RedisLabs (Phiên bản Windows)

1. Truy cập trang **Redis Windows**: [Redis Windows](https://github.com/microsoftarchive/redis/releases)
2. **Tải Redis-x64** bản mới nhất và **cài đặt**.
3. Sau khi cài xong, mở **Command Prompt** và gõ:
    ```bash
    redis-server
    ```

### 📝 Kiểm tra Redis

Để kiểm tra Redis có đang chạy không, bạn có thể sử dụng lệnh:
```bash
redis-cli ping
