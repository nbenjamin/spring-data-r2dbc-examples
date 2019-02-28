package com.nbenja.spring.r2dbcpostgresqlexample.config;

import static java.util.Arrays.asList;

import com.nbenja.spring.r2dbcpostgresqlexample.domain.User;
import com.nbenja.spring.r2dbcpostgresqlexample.repository.UserRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.nbenja.spring.r2dbcpostgresqlexample.repository")
public class R2dbcPostgresqlConfiguration extends AbstractR2dbcConfiguration {

  @Value("${datasource.host}")
  private String host;
  @Value("${datasource.port}")
  private int port;
  @Value("${datasource.database}")
  private String database;
  @Value("${datasource.username}")
  private String username;
  @Value("${datasource.password}")
  private String password;

  @Bean
  @Override
  public PostgresqlConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
        .builder()
        .host(host)
        .database(database)
        .username(username)
        .password(password)
        .port(port)
        .build());
  }

  @Bean
  public CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return args -> {
      DatabaseClient databaseClient = DatabaseClient.create(connectionFactory());
      asList("DROP TABLE IF EXISTS users;",
          "CREATE TABLE users (id serial primary key, name varchar);")
          .forEach(s ->
              databaseClient.execute()
                  .sql(s)
                  .fetch()
                  .rowsUpdated()
                  .block()
          );
      userRepository.save(new User(1, "test")).log().subscribe();
      userRepository.findAll().log().subscribe(System.out::println);
    };
  }

}
