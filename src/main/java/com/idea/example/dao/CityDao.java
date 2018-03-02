package com.idea.example.dao;

import com.idea.example.domain.City;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/*
* 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
* 需要注意的是：
*   1.接口中不可以定义重载方法，因为会生成相同的id
*   2.如果使用ibatis需和指定mapper xml文件中namespace = com.idea.example.dao.CityDao 对应
*   3.对于多个参数的方法 参数之前都需要加上@Param注解，不然会找不到对应的参数进而报错
*/
@Repository
@Mapper
public interface CityDao {

    City findCityById(@Param("id") Long id);
    List<City> findAllCity();
    Long saveCity(City city);
    Long updateCity(City city);
    Long deleteCity(Long id);
}
