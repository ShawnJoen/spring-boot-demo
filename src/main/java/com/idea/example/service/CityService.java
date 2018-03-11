package com.idea.example.service;

import com.idea.example.domain.dto.City;
import java.util.List;

public interface CityService {

    City findCityById(Long id);

    City findByCityName(String cityName);

    List<City> findAllCity();

    int saveCity(City city);

    int updateCity(City city);

    int deleteCity(Long id);
}
