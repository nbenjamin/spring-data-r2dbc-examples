package com.nbenja.spring.r2dbcpostgresqlexample.service;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.User;
import com.nbenja.spring.r2dbcpostgresqlexample.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<User> createUser(User user) {
    return userRepository.save(user);
  }

  public Flux<User> getUsers() {
    return userRepository.findAll();
  }

  public Mono<User> getUser(Integer id) {
    return userRepository.findById(id);
  }

  public Mono<Void> deleteUser(Integer id) {
    return userRepository.deleteById(id);
  }
}
