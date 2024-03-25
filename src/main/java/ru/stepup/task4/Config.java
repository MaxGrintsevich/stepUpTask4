package ru.stepup.task4;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    String path(){
        return "c:\\tmp\\files";
    }
    @Bean
    DataSource dataSource(){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL("jdbc:postgresql://localhost:5432/postgres");
        ds.setUser("postgres");
        ds.setPassword("qwerty");
        return ds;
    }

}
