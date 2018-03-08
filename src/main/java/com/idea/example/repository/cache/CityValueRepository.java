package com.idea.example.repository.cache;

import com.idea.example.domain.cache.CityValue;
import org.springframework.data.repository.CrudRepository;

public interface CityValueRepository extends CrudRepository<CityValue, String> {
    CityValue findByCityName(String cityName);//定义查询方法名称 和CrudRepository 相同
}
