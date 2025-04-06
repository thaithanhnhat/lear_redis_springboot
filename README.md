# 🚀 Hướng Dẫn Cài Đặt Redis và Sử Dụng Redis trong Spring Boot

## ⚡ Redis là gì?

**Redis** là một database in-memory mã nguồn mở, hoạt động như một key-value store với tốc độ truy xuất cực nhanh (dưới 1ms). Trong Spring Boot, Redis thường được sử dụng cho:

1. **Caching**: Lưu trữ tạm thời dữ liệu thường xuyên truy cập
2. **Session Management**: Quản lý phiên đăng nhập người dùng
3. **Rate Limiting**: Giới hạn số lượng request
4. **Message Queue**: Xử lý các tác vụ bất đồng bộ

### 🔑 Các kiểu dữ liệu Redis thường dùng trong Spring Boot:

1. **String**: Lưu trữ text hoặc dữ liệu nhị phân
2. **Hash**: Lưu trữ object dạng key-value
3. **List**: Danh sách các phần tử theo thứ tự
4. **Set**: Tập hợp các phần tử không trùng lặp
5. **Sorted Set**: Tập hợp có sắp xếp theo điểm số

### ⚙️ Cấu hình cơ bản:

- Redis server mặc định chạy trên port 6379
- Hỗ trợ authentication qua password
- Có thể lưu dữ liệu xuống đĩa (persistence)
- Hỗ trợ replication (master-slave)

### 🎯 Lợi ích khi sử dụng Redis trong Spring Boot:

1. **Hiệu năng cao**: Truy xuất dữ liệu nhanh hơn database thông thường
2. **Giảm tải database**: Giảm số lượng truy vấn vào database chính
3. **Dễ mở rộng**: Hỗ trợ phân tán và scale-out
4. **Đa dạng use case**: Có thể sử dụng cho nhiều mục đích khác nhau

---

## 1. Cài Đặt Redis trên Windows

### 🧑‍💻 Cài Đặt Redis

Redis không chính thức hỗ trợ Windows, nhưng bạn có thể sử dụng một trong các phương pháp dưới đây:

### A. Cài Đặt Redis qua WSL (Windows Subsystem for Linux)
### **Cài WSL nếu chưa có** (Windows Subsystem for Linux):
   - Mở **PowerShell** dưới quyền quản trị và chạy lệnh sau để bật WSL:
     ```powershell
     wsl --install
     ```
   - Lệnh trên sẽ tự động cài đặt WSL và yêu cầu khởi động lại máy tính. Sau khi máy tính khởi động lại, bạn sẽ có thể cài đặt các bản phân phối Linux như Ubuntu.
1. **Cài WSL** (Windows Subsystem for Linux) từ **Microsoft Store**:
    - Truy cập [Windows Subsystem for Linux](https://aka.ms/wslstore) và cài đặt Ubuntu.

2. **Cài đặt Redis trên Ubuntu (WSL)**:
    Sau khi cài xong WSL, mở Ubuntu và chạy các lệnh sau:
    ```bash
    sudo apt update
    sudo apt install redis-server
    sudo service redis-server start
    ```

3. **Kiểm tra Redis**:
    Để kiểm tra Redis có đang chạy hay không, bạn có thể sử dụng lệnh:
    ```bash
    redis-cli ping
    ```
    Nếu Redis đang chạy, bạn sẽ nhận được:
    ```
    PONG
    ```

### B. Cài Đặt Redis từ RedisLabs (Phiên bản Windows)

1. **Truy cập trang Redis Windows**: [Redis Windows](https://github.com/microsoftarchive/redis/releases)
    - Tải bản Redis-x64 mới nhất và giải nén vào một thư mục, ví dụ: `C:\Redis`.

2. **Cấu hình PATH**:
    Để sử dụng các lệnh `redis-server` và `redis-cli` từ bất kỳ cửa sổ dòng lệnh nào, bạn cần thêm thư mục Redis vào biến môi trường `PATH`:
    - Mở **Control Panel** → **System and Security** → **System** → **Advanced system settings**.
    - Click vào **Environment Variables**.
    - Trong phần **System variables**, chọn `Path` và nhấn **Edit**.
    - Thêm đường dẫn tới thư mục Redis bạn đã giải nén (ví dụ: `C:\Redis`).
    - Nhấn **OK** để lưu lại thay đổi.

3. **Chạy Redis**:
    Mở **Command Prompt** hoặc **PowerShell**, và gõ:
    ```bash
    redis-server
    ```
    Redis sẽ bắt đầu chạy trên cổng mặc định `6379`.

4. **Kiểm Tra Redis**:
    Mở một cửa sổ **Command Prompt** hoặc **PowerShell** khác và kiểm tra Redis với lệnh:
    ```bash
    redis-cli ping
    ```
    Nếu Redis đang chạy đúng, bạn sẽ nhận được:
    ```
    PONG
    ```

---

### 📝 Kiểm Tra Redis

Để kiểm tra Redis có đang chạy không, bạn có thể sử dụng lệnh:

```bash
redis-cli ping
```

## 2. Sử Dụng Redis trong Spring Boot

### ⚙️ Thêm Dependencies vào `pom.xml`

Để sử dụng Redis trong Spring Boot, bạn cần thêm các dependencies sau vào file `pom.xml`:

```xml
<!-- Spring Boot Starter for Redis Data -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- Spring Boot Starter for Caching -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<!-- Apache Commons Pool2 for Redis connection pooling -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

Sau khi thêm, hãy cập nhật Maven project của bạn.

### 🔧 Cấu Hình Redis trong `application.properties`

Thêm các cấu hình sau vào file `application.properties` (hoặc `application.yml`):

```properties
# Redis Server Host
spring.redis.host=localhost
# Redis Server Port (mặc định là 6379)
spring.redis.port=6379
# Redis Password (nếu có)
# spring.redis.password=your_password

# Optional: Cấu hình Lettuce connection pool (nếu dùng Lettuce)
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
spring.redis.lettuce.pool.max-wait=-1ms # Thời gian chờ tối đa để lấy connection
```

### ✨ Sử Dụng Redis Cache trong Spring Boot

1.  **Bật Caching**:
    Thêm annotation `@EnableCaching` vào lớp Application chính của bạn:

    ```java
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cache.annotation.EnableCaching;

    @SpringBootApplication
    @EnableCaching // Bật tính năng caching
    public class YourApplication {

        public static void main(String[] args) {
            SpringApplication.run(YourApplication.class, args);
        }
    }
    ```

2.  **Các Mẫu Sử Dụng Cache**:
    Các ví dụ cụ thể về cách sử dụng các annotation như `@Cacheable`, `@CachePut`, `@CacheEvict` để quản lý cache với Redis có thể được tìm thấy trong các branch tương ứng của dự án này.

Với các bước trên, bạn đã tích hợp thành công Redis vào ứng dụng Spring Boot và sử dụng nó để caching, giúp cải thiện hiệu năng ứng dụng.
