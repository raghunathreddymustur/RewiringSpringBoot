package com.raghu.test.mock.transfer;

import com.raghu.test.mock.layer.FtpDataLayer;
import com.raghu.test.mock.layer.HttpDataLayer;
import com.raghu.test.mock.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataTransferAction {
    @Autowired
    public FtpDataLayer ftpDataLayer;
    @Autowired
    public HttpDataLayer httpDataLayer;

    public void transfer() {
        List<Person> personData = ftpDataLayer.getData();

        httpDataLayer.saveData(personData);
    }
}
