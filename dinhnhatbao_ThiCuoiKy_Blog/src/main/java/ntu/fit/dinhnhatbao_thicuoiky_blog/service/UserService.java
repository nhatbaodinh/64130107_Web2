package ntu.fit.dinhnhatbao_thicuoiky_blog.service;

import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;

import java.util.Optional;

public interface UserService {

  // Kiểm tra sự tồn tại của người dùng theo tên đăng nhập
  boolean existsByUsername(String username);

  // Đăng nhập người dùng
  Optional<User> loginUser(String username, String password);

  // Lưu người dùng
  void save(User user);

  // Tìm người dùng theo tên đăng nhập
  Optional<User> findByUsername(String username);
}