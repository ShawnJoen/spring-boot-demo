package com.idea.example.repository.dao;

import com.idea.example.domain.dto.City;
import org.springframework.data.repository.query.Param;
import java.util.List;

/*
*   1.接口中不可以定义重载方法，因为会生成相同的id
*   2.如果使用ibatis需和指定mapper xml文件中namespace = com.idea.example.repository.dao.CityDao 对应
*   3.对于多个参数的方法 参数之前都需要加上@Param注解，不然会找不到对应的参数进而报错
*/
public interface CityDao {

    City findCityById(@Param("id") Long id);
    City findByCityName(@Param("cityName") String cityName);
    List<City> findAllCity();
    int saveCity(City city);
    int updateCity(City city);
    int deleteCity(Long id);
}
