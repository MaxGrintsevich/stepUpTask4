package ru.stepup.task4.utils;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.stepup.task4.annotations.LogTransformation;
import ru.stepup.task4.interfaces.DataTransformable;
import ru.stepup.task4.model.Data;

import java.util.Arrays;
import java.util.List;


@Component
@Order(2)
public class OperationAppTypeChecker implements DataTransformable {
    private List<String> etalonList =  Arrays.asList("web","mobile");

    @Override
    @LogTransformation("log.txt")
    public void transform(Data data) {
        data.setAppType(data.getAppType().toLowerCase());
        if (!etalonList.contains(data.getAppType())){
            data.setAppType("other:"+data.getAppType());
        }
    }
}
