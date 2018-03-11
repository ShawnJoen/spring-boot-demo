package com.idea.example.domain.dto;

import lombok.Data;

@Data
public class CoinMarketCapDTO {

    @Data
    public class Ticker {
        private String id;
        private String name;
        private String symbol;
        private String rank;
        private String price_usd;
        private String price_btc;
        //private String 24h_volume_usd;
        private String market_cap_usd;
        private String available_supply;
        private String total_supply;
        private String max_supply;
        private String percent_change_1h;
        private String percent_change_24h;
        private String percent_change_7d;
        private String last_updated;
        private String price_usdt;
        //private String 24h_volume_usdt;
        private String market_cap_usdt;
    }

}
