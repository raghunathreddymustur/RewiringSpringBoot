package com.raghu.spingboot.test.controller;


import com.raghu.spingboot.test.ds.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerSpringBootTest {
    @Autowired
    private CityController cityController;
    @Test
    public void shouldSaveFewCities() {
        cityController.putCity(City.builder().name("Los Angeles").build());
        cityController.putCity(City.builder().name("New York").build());
        cityController.putCity(City.builder().name("San Francisco").build());

        assertThat(cityController.getCities().getCities())
                .containsOnly(
                        new City(1, "Los Angeles"),
                        new City(2, "New York"),
                        new City(3, "San Francisco")
                );
    }
}
