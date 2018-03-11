package com.idea.example.service.impl;

import com.idea.example.repository.dao.CityDao;
import com.idea.example.domain.dto.City;
import com.idea.example.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public City findCityById(Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            //从缓存
            City city = operations.get(key);
            if (city != null) {
                log.info("-------------缓存中获取了指定城市: {}", city.toString());
            }
            return city;
        }
        //从DB
        City city = cityDao.findCityById(id);
        //插入到缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        if (city != null) {
            log.info("-------------DB中获取了指定城市: {}", city.toString());
        }
        return city;
    }

    @Override
    public City findByCityName(String cityName) {
        return cityDao.findByCityName(cityName);
    }

    @Override
    public List<City> findAllCity() {
        return cityDao.findAllCity();
    }

    @Override
    public int saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public int updateCity(City city) {
        int result = cityDao.updateCity(city);
        //删除指定缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            log.info("-------------删除指定缓存: {}", city.toString());
        }

        return result;
    }

    @Override
    public int deleteCity(Long id) {
        int result = cityDao.deleteCity(id);
        //删除指定缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            log.info("-------------删除指定缓存: {}", id);
        }

        return result;
    }

}
