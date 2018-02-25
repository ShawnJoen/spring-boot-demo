package com.idea.example;

import com.idea.example.domain.City;
import com.idea.example.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*lombok
* @Slf4j 内置日志门面 可以直接通过变量log使用
*
* */
@Slf4j
@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping(value="hello")
    public String hello(){
        return "hello";
    }

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "api/findOneCity", method = RequestMethod.GET)
    public @ResponseBody
    City findOneCity(@RequestParam(value = "id", required = true) Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "api/findAllCity", method = RequestMethod.GET)
    public @ResponseBody
    List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @RequestMapping(value = "api/createCity", method = RequestMethod.GET)
    public void createCity(@ModelAttribute("city") City city) {
        log.info("-------------createCity: {}", cityService.saveCity(city));
    }

    @RequestMapping(value = "api/updateCity", method = RequestMethod.GET)
    public void updateCity(@ModelAttribute("city") City city) {
        log.info("-------------updateCity: {}", cityService.updateCity(city));
    }

    @RequestMapping(value = "api/deleteCity/{id}", method = RequestMethod.GET)
    public void deleteCity(@PathVariable("id") Long id) {
        log.info("-------------updateCity: {}", cityService.deleteCity(id));
    }

}
