package ru.stepup.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Order(3)
public class OperationDateChecker implements Consumer<Data> {

    @Override
    @LogTransformation("log.txt")
    public void accept(Data data) {
        if (data.date == null){
            data.error  = "Error: Date is empty";
        }
    }
}
