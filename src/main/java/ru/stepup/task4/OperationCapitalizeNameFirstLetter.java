package ru.stepup.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Order(1)
public class OperationCapitalizeNameFirstLetter implements Consumer<Data> {
    private String capitalizeFirstLetter(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    @LogTransformation("log.txt")
    public void accept(Data data) {
        data.firstname = capitalizeFirstLetter(data.firstname);
        data.lastname = capitalizeFirstLetter(data.lastname);
        data.secondName = capitalizeFirstLetter(data.secondName);
    }
}
