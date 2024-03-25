package ru.stepup.task4;

import javax.persistence.*;

@Entity
@Table(name="logins")
public class Logins{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    long id;
    java.sql.Timestamp access_date;
    long user_id;
    String application;

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", access_date=" + access_date +
                ", user_id=" + user_id +
                ", application='" + application + '\'' +
                '}';
    }
}
