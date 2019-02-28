package com.nbenja.spring.r2dbcpostgresqlexample.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.nbenja.spring.r2dbcpostgresqlexample.api.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class RouterConfiguration {

  @Bean
  public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
    return route()
        .POST("/users", userHandler::createUser)
        .GET("/users", userHandler::getUsers)
        .GET("/users/{id}", userHandler::getUser)
        .build();
  }
}
