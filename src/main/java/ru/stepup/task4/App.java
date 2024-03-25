package ru.stepup.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class App {

    @Autowired
    private Supplier<List<Data>> reader;
    @Autowired
    private List<Consumer<Data>> operations;
    @Autowired
    private Consumer<List<Data>> writer;

    public void execute(){
        List<Data> list = reader.get();

        list.forEach(data -> operations.forEach(f->f.accept(data)));

        writer.accept(list);
    }

}
