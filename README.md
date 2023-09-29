Cơ bản về phần login, login xong tự chuyển đến các homepage của các role nhưng chưa phân quyền.
Phần log in dùng OTP chưa chạy được. Phần màn hình của lecturer chưa display được data.


UPDATE. 29/9
sửa lỗi OTP, thêm add account có validate dữ liệu, tách trang change password ra thành 1 màn hình riêng, profile -> change password => 1 trang change password
servlet HomeController(/home) có tác dụng khi ấn vào icon home ở bất kì màn hình nào thì tùy theo role mà đưa về màn hình homepage của role đấy, nếu chưa login thì về homepage index.html.
