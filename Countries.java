package com.company;

import java.util.ArrayList;

public class Countries {
    private ArrayList<Country> countries = new ArrayList<>();
    public Country country = new Country();

    public Countries() {
        //Complete the constructor\
        countries = new ArrayList<>();
    }

    public void addCountry(String name, String iso2code) {
        country =  new Country(name, iso2code);
        countries.add(country);
    }


    public int getIndexOfCountryByIso2Code(String iso2code) {
        int index = 0;
        boolean isIndex = false;
        for (int i = 0; i < countries.size(); i++){
            isIndex = countries.get(i).getIso2Code().equalsIgnoreCase(iso2code);
            if (isIndex) {
                index = i;
                return index;
            } else  {
                index = -1;
            }

        }
        return index;
    }

    public void setRegion(String iso2code, String region) {
        int index = 0;
        index = getIndexOfCountryByIso2Code(iso2code);
        countries.get(index).setRegion(region);
    }

    public void setIncomeLevel(String iso2code, String incomeLevel) {
        int index = 0;
        index = getIndexOfCountryByIso2Code(iso2code);
        countries.get(index).setIncomeLevel(incomeLevel);
        //System.out.println(countries.get(index).getReportRow());
    }

    public void setLifeExpectancy(String iso2code, double lifeExpectancy) {
        int index = 0;
        index = getIndexOfCountryByIso2Code(iso2code);
        double lifeExpect =  lifeExpectancy;
        countries.get(index).setLifeExp(lifeExpect);
    }

    public void report() {
        for(int i = 0; i < countries.size(); i++) {
            String line = countries.get(i).getReportRow();
            System.out.println(line);
        }
    }
}

