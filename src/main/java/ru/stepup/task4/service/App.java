package ru.stepup.task4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepup.task4.interfaces.DataReadable;
import ru.stepup.task4.interfaces.DataTransformable;
import ru.stepup.task4.interfaces.DataWritable;
import ru.stepup.task4.model.Data;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class App {

    @Autowired
    private DataReadable reader;
    @Autowired
    private List<DataTransformable> operations;
    @Autowired
    private DataWritable writer;

    public void execute(){
        List<Data> list = reader.read();

        list.forEach(data -> operations.forEach(f->f.transform(data)));
        List<Data> correctList = list.stream().filter((data)->data.getError()==null).collect(Collectors.toList());

        writer.write(correctList);
    }

}
