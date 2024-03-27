package ru.stepup.task4.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stepup.task4.entities.User;

public interface UserRepo extends CrudRepository<User, Long> {
}
