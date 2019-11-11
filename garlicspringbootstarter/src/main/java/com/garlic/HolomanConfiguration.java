package com.garlic;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolomanConfiguration
{
    @Bean
    @ConditionalOnMissingBean // @ComponentScan에 의해 등록된 빈이 있으면 다시 등록하지 않는다.
                              // 즉, @ComponentScan에 의해 등록된 빈이 없을 때, 빈으로 등록한다.
    public Holoman holoman(HolomanProperties properties)
    {
        Holoman holoman = new Holoman();
        holoman.setName(properties.getName());
        holoman.setHowLong(properties.getHowLong());
        return holoman;
    }
}
