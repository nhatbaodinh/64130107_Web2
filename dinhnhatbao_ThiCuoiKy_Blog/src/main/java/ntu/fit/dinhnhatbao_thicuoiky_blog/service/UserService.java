package ntu.fit.dinhnhatbao_thicuoiky_blog.service;

import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;

import java.util.Optional;

public interface UserService {
  boolean existsByUsername(String username);
  Optional<User> loginUser(String username, String password);
  void save(User user);
}