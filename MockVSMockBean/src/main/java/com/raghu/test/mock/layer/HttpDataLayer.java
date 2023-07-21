package com.raghu.test.mock.layer;

import com.raghu.test.mock.pojo.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HttpDataLayer {
    public void saveData(List<Person> persons) {
        System.out.println("Saving data to http...");

        persons.forEach(System.out::println);

        System.out.println("Done");
    }
}
