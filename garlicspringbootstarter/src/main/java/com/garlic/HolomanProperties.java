package com.garlic;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 사용하는 곳에서 Bean을 재정의 할 필요없이 properties에서 값을 세팅하여 사용하고 싶을 때
// @ConfigurationProperties를 붙이고 prefix를 정의한다.
@ConfigurationProperties("holoman")
public class HolomanProperties
{

    private String name;

    private int    howLong;

    public String getName()
    {
        return this.name;
    }





    public void setName(String name)
    {
        this.name = name;
    }





    public int getHowLong()
    {
        return this.howLong;
    }





    public void setHowLong(int howLong)
    {
        this.howLong = howLong;
    }
}
