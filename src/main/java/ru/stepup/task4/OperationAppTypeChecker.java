package ru.stepup.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Component
@Order(2)
public class OperationAppTypeChecker implements Consumer<Data> {
    private List<String> etalonList =  Arrays.asList("web","mobile");

    @Override
    @LogTransformation("log.txt")
    public void accept(Data data) {
        data.appType = data.appType.toLowerCase();
        if (!etalonList.contains(data.appType)){
            data.appType = "other:"+data.appType;
        }
    }
}
