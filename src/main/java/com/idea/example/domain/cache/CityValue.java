package com.idea.example.domain.cache;

import com.idea.example.domain.City;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash(value = "cityValue", timeToLive=3L) //数据是存储在Redis-Hash中,timeToLive秒单位存储保留时间
public class CityValue {
    @Id
    private String id;

    //新建值 建立索引 存储
    @Indexed
    private String cityName;

    private City city;
}
