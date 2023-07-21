package com.raghu.test.mock.layer;

import org.springframework.stereotype.Service;
import  com.raghu.test.mock.pojo.Person;
import java.util.Arrays;
import java.util.List;

@Service
public class FtpDataLayer {
    public List<Person> getData() {
        System.out.println("Fetching data from ftp...");

        return Arrays.asList(
                new Person(1, "John"),
                new Person(2, "Dave"),
                new Person(3, "Peter")
        );
    }
}
