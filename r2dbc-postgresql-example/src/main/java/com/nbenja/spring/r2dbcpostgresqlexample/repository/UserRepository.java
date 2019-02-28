package com.nbenja.spring.r2dbcpostgresqlexample.repository;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

}
