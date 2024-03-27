package ru.stepup.task4.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepup.task4.entities.Logins;
import ru.stepup.task4.entities.User;
import ru.stepup.task4.interfaces.DataWritable;
import ru.stepup.task4.model.Data;
import ru.stepup.task4.repository.LoginsRepo;
import ru.stepup.task4.repository.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class Writer implements DataWritable {
    @Autowired
    private LoginsRepo lRepo;
    @Autowired
    private UserRepo uRepo;

    Map<String, User> users = new HashMap<>();

    private void readUsers(){
        Iterable<User>  usersIterator = uRepo.findAll();
        usersIterator.forEach(user -> users.put(user.getUsername(), user));
    }
    User createUser(Data data){
        User user = new User();
        user.setFio(data.getLastname() + " " + data.getFirstname() + " " + data.getSecondName());
        user.setUsername(data.getLogin());
        uRepo.save(user);
        return user;
    }

    User getUser(Data data){
        if (! users.containsKey(data.getLogin())){
            users.put(data.getLogin(), createUser(data));
        }
        return users.get(data.getLogin());
    }

    Logins processDataObject(Data data) {
        Logins login = new Logins();

        login.setApplication(data.getAppType());
        login.setUser_id(getUser(data).getId());
        login.setAccess_date(data.getDate());
        lRepo.save(login);
        return login;
    }

    @Override
    public void write(List<Data> dataList) {
        readUsers();
        dataList.forEach(data -> processDataObject(data));
    }
}
