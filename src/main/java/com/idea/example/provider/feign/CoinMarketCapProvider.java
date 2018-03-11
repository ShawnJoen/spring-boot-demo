package com.idea.example.provider.feign;

import com.idea.example.config.FeignConfig;
import com.idea.example.domain.dto.CoinMarketCapDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/*
* @FeignClient参数说明
* name: 指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
* decode404: false(默认) 当发生http 404错误时接受html错误页 抛出FeignException, true:会调用decoder进行解码
* configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
* url: url一般用于调试，可以手动指定@FeignClient调用的地址
* fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
* fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
* path: 定义当前FeignClient的统一前缀
* */
@FeignClient(name = "coinMarketCapProvider",
        url = "https://api.coinmarketcap.com",
        configuration = FeignConfig.class,
        decode404 = true,
        fallback = CoinMarketCapProvider.DefaultFallback.class
)
public interface CoinMarketCapProvider {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/ticker/?convert=USDT&limit=3")
    List<CoinMarketCapDTO.Ticker> ticker();

    /**
     * 容错处理类，当调用失败时，简单返回空字符串
     */
    @Slf4j
    @Component
    class DefaultFallback implements CoinMarketCapProvider {
        @Override
        public List<CoinMarketCapDTO.Ticker> ticker() {

            log.info("-------------DefaultFallback 容错处理");

            return null;
        }
    }

}
