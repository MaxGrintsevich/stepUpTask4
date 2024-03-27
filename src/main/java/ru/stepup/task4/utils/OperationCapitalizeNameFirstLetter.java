package ru.stepup.task4.utils;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.stepup.task4.annotations.LogTransformation;
import ru.stepup.task4.interfaces.DataTransformable;
import ru.stepup.task4.model.Data;

import java.util.function.Consumer;

@Component
@Order(1)
public class OperationCapitalizeNameFirstLetter implements DataTransformable {
    private String capitalizeFirstLetter(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    @LogTransformation("log.txt")
    public void transform(Data data) {
        data.setFirstname(capitalizeFirstLetter(data.getFirstname()));
        data.setLastname(capitalizeFirstLetter(data.getLastname()));
        data.setSecondName(capitalizeFirstLetter(data.getSecondName()));
    }
}
