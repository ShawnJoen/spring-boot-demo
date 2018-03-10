package com.idea.example.controller;

import com.idea.example.domain.City;
import com.idea.example.service.cache.CityValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*lombok
* @Slf4j 内置日志门面 可以直接通过变量log使用
* */
@Slf4j
@Controller
@RequestMapping("/cache/crud/")
public class CrudRepositoryController {

    @Autowired
    private CityValueService cityValueService;

    @RequestMapping(value="getCityById")
    public @ResponseBody
    City
    getCityById(@RequestParam(value = "id", required = true) Long id){

        return cityValueService.getCityById(id);
    }

    @RequestMapping(value="getCityByCityName")
    public @ResponseBody
    City
    getCityByCityName(@RequestParam(value = "cityName", required = true) String cityName){

        return cityValueService.getCityByCityName(cityName);
    }
}
