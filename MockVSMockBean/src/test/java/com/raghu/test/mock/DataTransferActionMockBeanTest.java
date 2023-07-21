package com.raghu.test.mock;

import com.raghu.test.mock.layer.FtpDataLayer;
import com.raghu.test.mock.layer.HttpDataLayer;
import com.raghu.test.mock.pojo.Person;
import com.raghu.test.mock.transfer.DataTransferAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTransferActionMockBeanTest {
    private static final List<Person> PERSON_LIST = Arrays.asList(
            new Person(98, "Test1"),
            new Person(99, "Test2")
    );

    @Autowired
    private DataTransferAction dataTransferAction;
    @MockBean
    private FtpDataLayer ftpDataLayer;
    @MockBean
    private HttpDataLayer httpDataLayer;

    @Test
    public void shouldTransferDataFromFtpToHttp() {
        when(ftpDataLayer.getData()).thenReturn(PERSON_LIST);

        dataTransferAction.transfer();

        verify(httpDataLayer).saveData(PERSON_LIST);
    }
}
