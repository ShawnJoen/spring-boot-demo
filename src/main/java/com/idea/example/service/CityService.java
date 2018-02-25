package com.idea.example.service;

import com.idea.example.domain.City;
import java.util.List;

public interface CityService {

    City findCityById(Long id);

    List<City> findAllCity();

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
