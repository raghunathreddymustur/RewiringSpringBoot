package com.raghu.spingboot.test.dao;

import com.raghu.spingboot.test.ds.City;
import org.springframework.data.repository.CrudRepository;

public interface CityDao extends CrudRepository<City, Integer> {
}
