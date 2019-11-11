package com.garlic;

public class Holoman
{

    String name;

    int    howLong;

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





    @Override
    public String toString()
    {
        return "Holoman{" + "name='" + this.name + '\'' + ", howLong='" + this.howLong + '\'' + '}';
    }
}
