package ru.stepup.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "ru.stepup.task4")
public class Start {

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Start.class);
        App app = ctx.getBean(App.class);
        app.execute();
    }

}
