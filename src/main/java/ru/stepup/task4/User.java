package ru.stepup.task4;
import javax.persistence.*;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    long id;
    String username;
    String fio;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
