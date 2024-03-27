package ru.stepup.task4.utils;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.stepup.task4.annotations.LogTransformation;
import ru.stepup.task4.interfaces.DataTransformable;
import ru.stepup.task4.model.Data;

import java.util.function.Consumer;

@Component
@Order(3)
public class OperationDateChecker implements DataTransformable {


    @Override
    @LogTransformation("log.txt")
    public void transform(Data data) {
        if (data.getDate() == null){
            data.setError("Error: Date is empty");
        }
    }
}
