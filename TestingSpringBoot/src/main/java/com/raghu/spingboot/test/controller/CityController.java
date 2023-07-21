package com.raghu.spingboot.test.controller;

import com.raghu.spingboot.test.ds.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.raghu.spingboot.test.dao.CityDao;
import com.raghu.spingboot.test.ds.City;
@RestController
public class CityController {
    @Autowired
    private CityDao cityDao;

    @GetMapping("/cities")
    public Cities getCities() {
        return new Cities(cityDao.findAll());
    }

    @PutMapping("/cities")
    public void putCity(@RequestBody City city) {
        cityDao.save(city);
    }

}
