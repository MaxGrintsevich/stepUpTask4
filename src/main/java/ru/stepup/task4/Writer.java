package ru.stepup.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class Writer implements Consumer<List<Data>> {
    @Autowired
    private LoginsRepo lRepo;
    @Autowired
    private UserRepo   uRepo;

    Map<String, User> users = new HashMap<>();

    private void readUsers(){
        Iterable<User>  usersIterator = uRepo.findAll();
        usersIterator.forEach(user -> users.put(user.username, user));
    }
    User createUser(Data data){
        User user = new User();
        user.fio = data.lastname + " " + data.firstname + " " + data.secondName;
        user.username = data.login;
        uRepo.save(user);
        return user;
    }

    User getUser(Data data){
        if (! users.containsKey(data.login)){
            users.put(data.login, createUser(data));
        }
        return users.get(data.login);
    }

    Logins processDataObject(Data data) {
        Logins login = new Logins();

        login.application = data.appType;
        login.user_id = getUser(data).id;
        login.access_date = data.date;
        lRepo.save(login);
        return login;
    }

    @Override
    public void accept(List<Data> dataList) {
        readUsers();
        dataList.forEach(data -> {if(data.error == null) processDataObject(data);});
    }
}
