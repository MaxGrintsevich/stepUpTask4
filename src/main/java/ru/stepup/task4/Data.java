package ru.stepup.task4;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Data {
    String login;
    String lastname;
    String firstname;
    String secondName;
    Timestamp date;
    String appType;
    String error;

    public Data(String s) {
        try{
            String[] words = s.split(" ");
            int size = words.length;
            login = words[0];
            lastname = words[1];
            firstname = words[2];
            secondName = words[3];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            date = Timestamp.valueOf(LocalDateTime.parse(words[4] + " " + words[5], dateFormat));
            appType = words[6];
            error = null;
        } catch( Exception e){
            error = e.getMessage();
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "login='" + login + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", date='" + date + '\'' +
                ", appType='" + appType + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
