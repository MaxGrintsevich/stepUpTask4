package ru.stepup.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stepup.task4.interfaces.DataTransformable;
import ru.stepup.task4.model.Data;
import ru.stepup.task4.service.App;
import ru.stepup.task4.utils.OperationAppTypeChecker;
import ru.stepup.task4.utils.OperationCapitalizeNameFirstLetter;
import ru.stepup.task4.utils.OperationDateChecker;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Tester {
    @Test
    void testOperationAppTypeChecker(){
        Data data = new Data("vtb4040984 ГРИНЦЕВИЧ максим ВиктОрович 21.04.2023 15:48:12 excel");
        DataTransformable operation = new OperationAppTypeChecker();
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "other:excel");
        data.setAppType( "web");
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "web");
        data.setAppType("mobile");
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "mobile");
    }

    @Test
    void testOperationDateChecker(){
        Data data = new Data("vtb4040984 ГРИНЦЕВИЧ максим ВиктОрович 21.04.2023 15:48:12 excel");
        DataTransformable operation = new OperationDateChecker();
        operation.transform(data);
        Assertions.assertEquals(data.getDate(), Timestamp.valueOf(LocalDateTime.parse("21.04.2023 15:48:12", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))));
        data = new Data("");
        Assertions.assertEquals(data.getDate(), null);
        Assertions.assertNotNull(data.getError());
    }

    @Test
    void testOperationCapitalizeNameFirstLetter(){
        Data data = new Data("vtb4040984 ГРИНЦЕВИЧ максим ВиктОрович 21.04.2023 15:48:12 excel");
        DataTransformable operation = new OperationCapitalizeNameFirstLetter();
        operation.transform(data);
        Assertions.assertEquals(data.getFirstname(), "Максим");
        Assertions.assertEquals(data.getSecondName(), "Викторович");
        Assertions.assertEquals(data.getLastname(), "Гринцевич");
    }
    @Test
    void testApp(){
        App app = new App();
        // reader
        List<Data> srcList = new ArrayList<>();
        String fileLine = "vtb4040984 ГРИНЦЕВИЧ максим ВиктОрович 21.04.2023 15:48:12 excel";
        srcList.add(new Data(fileLine));

        app.setReader(()->srcList);

        // operations
        List<DataTransformable> opList = new ArrayList<>();
        opList.add(new OperationAppTypeChecker());
        app.setOperations(opList);

        // writer
        List<Data> resultList = new ArrayList<>();
        app.setWriter((List<Data> list)->{ resultList.addAll(list); });

        // эталонный результат
        List<Data> etalon = new ArrayList<>();
        Data etalonData = new Data(fileLine);
        etalonData.setAppType("other:excel");;
        etalon.add(etalonData);


        app.execute();

        Assertions.assertEquals(resultList, etalon);

    }

}
