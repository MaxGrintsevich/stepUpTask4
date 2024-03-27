package ru.stepup.task4.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import ru.stepup.task4.entities.Logins;

@EnableJpaRepositories
public interface LoginsRepo extends CrudRepository<Logins, Long> {

}