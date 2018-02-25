package com.idea.example.dao;

import com.idea.example.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//DAO没有额外实现类
@Repository
@Mapper
public interface CityDao {

    City findCityById(@Param("id") Long id);

    List<City> findAllCity();

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
