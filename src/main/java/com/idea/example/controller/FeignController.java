package com.idea.example.controller;

import com.idea.example.domain.dto.CoinMarketCapDTO;
import com.idea.example.provider.feign.CoinMarketCapProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feign/")
public class FeignController {

    private final CoinMarketCapProvider coinMarketCapProvider;

    @Autowired
    public FeignController(CoinMarketCapProvider coinMarketCapProvider) {
        this.coinMarketCapProvider = coinMarketCapProvider;
    }
    @GetMapping("ticker")
    public @ResponseBody
    List<CoinMarketCapDTO.Ticker> ticker() {

        log.info("-------------ticker");

        return coinMarketCapProvider.ticker();
    }

}
