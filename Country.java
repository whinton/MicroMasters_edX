package com.company;

public class Country {
    //public static int name;
    private String name;
    private String region;
    private String incomeLevel;
    private String iso2code;
    private Double lifeExpectancy;

    public Country(String name, String iso2code) {
        this.name = name;
        this.iso2code = iso2code;
    }

    public Country(){}

    public String getName() {

        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIso2Code(String iso2code){
        this.iso2code = iso2code;
    }

    public String getIso2Code() {

        return iso2code;
    }

    public void setRegion(String region) {

        this.region = region;
    }

    public String getRegion() {

        return region;
    }

    public void setIncomeLevel(String incomeLevel) {

        this.incomeLevel = incomeLevel;
    }

    public String getIncomeLevel() {

        return incomeLevel;
    }
    public void setLifeExp (double lifeExpectancy) {

        this.lifeExpectancy = lifeExpectancy;
    }

    public double getLifeExpectancy() {

        return lifeExpectancy;
    }

    public String getReportRow() {
        return name + ", " + iso2code + ", " +  region + ", " + incomeLevel + ", " + lifeExpectancy;
    }
}


