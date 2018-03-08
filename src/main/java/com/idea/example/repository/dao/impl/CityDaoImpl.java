package com.idea.example.repository.dao.impl;

import com.idea.example.domain.City;
import com.idea.example.repository.dao.BaseDaoImpl;
import com.idea.example.repository.dao.CityDao;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
*   1.接口中不可以定义重载方法，因为会生成相同的id
*   2.如果使用ibatis需和指定mapper xml文件中namespace = com.idea.example.repository.dao.CityDao 对应
*   3.对于多个参数的方法 参数之前都需要加上@Param注解，不然会找不到对应的参数进而报错
*/
@Repository
public class CityDaoImpl extends BaseDaoImpl implements CityDao {

    @Override
    public City findByCityName(String cityName) {
        return getSqlSession().selectOne(getStatement("findByCityName"), cityName);
    }

    @Override
    public City findCityById(Long id) {
        return getSqlSession().selectOne(getStatement("findCityById"), id);
    }

    @Override
    public List<City> findAllCity() {
        return getSqlSession().selectList(getStatement("findAllCity"));
    }

    @Override
    public int saveCity(City city) {
        return getSqlSession().insert(getStatement("saveCity"), city);
    }

    @Override
    public int updateCity(City city) {
        return getSqlSession().update(getStatement("updateCity"), city);
    }

    @Override
    public int deleteCity(Long id) {
        return getSqlSession().delete(getStatement("deleteCity"), id);
    }

}
