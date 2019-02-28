package com.nbenja.spring.r2dbcpostgresqlexample.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;
import static reactor.core.publisher.Mono.from;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.User;
import com.nbenja.spring.r2dbcpostgresqlexample.service.UserService;
import javax.jws.soap.SOAPBinding.Use;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

  private UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(User.class)
        .flatMap(u -> status(CREATED)
            .contentType(APPLICATION_JSON)
            .body(userService.createUser(u), User.class));
  }

  public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(userService.getUsers(), User.class)
        .switchIfEmpty(ServerResponse.noContent().build());
  }

  public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
    return from(userService.getUser(Integer.valueOf(serverRequest.pathVariable("id"))))
        .flatMap(u -> ok()
            .contentType(APPLICATION_JSON)
            .body(fromObject(u)))
        .switchIfEmpty(notFound().build());
  }

  public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
    return from(userService.deleteUser(Integer.valueOf(serverRequest.pathVariable("id"))))
        .flatMap(u -> ok()
            .contentType(APPLICATION_JSON)
            .build());
  }
}
