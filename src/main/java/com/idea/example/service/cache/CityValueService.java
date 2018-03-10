package com.idea.example.service.cache;

import com.idea.example.domain.City;
import com.idea.example.domain.cache.CityValue;
import com.idea.example.repository.cache.CityValueRepository;
import com.idea.example.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityValueService {
    //CrudRepository的实现 操作Redis像关系型数据库一样
    private final CityValueRepository cityValueRepository;

    @Autowired
    public CityValueService(CityValueRepository cityValueRepository) {
        this.cityValueRepository = cityValueRepository;
    }

    @Autowired
    private CityService cityService;

    public City getCityById(Long id) {

        String key = "id_" + id;
        CityValue cacheCityValue = cityValueRepository.findOne(key);//findOne是条件为id自带方法获取一行
        if (cacheCityValue == null) {

            City city = cityService.findCityById(id);
            if (city != null) {

                log.info("-------------getCityByCityName from Db: {}", city.toString());

                CityValue cityValue = new CityValue();
                cityValue.setId(key);
                cityValue.setCityName(city.getCityName());
                cityValue.setCity(city);
                return cityValueRepository.save(cityValue)//save自带方法存储
                                    .getCity();
            }
            return null;
        }

        City city = cacheCityValue
                .getCity();

        log.info("-------------getCityById from Cache: {}", city.toString());

        return city;
    }

    public City getCityByCityName(String cityName) {

        CityValue cacheCityValue = cityValueRepository.findByCityName(cityName);
        if (cacheCityValue == null) {

            City city = cityService.findByCityName(cityName);
            if (city != null) {

                log.info("-------------getCityByCityName from Db: {}", city.toString());

                CityValue cityValue = new CityValue();
                cityValue.setId("id_" + city.getId());
                cityValue.setCityName(cityName);
                cityValue.setCity(city);
                return cityValueRepository.save(cityValue)
                        .getCity();
            }
            return null;
        }

        City city = cacheCityValue
                .getCity();

        log.info("-------------getCityByCityName from Cache: {}", city.toString());

        return city;
    }

}
