package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class CountriesSAXParser {
    public static void setWorldBankData(Countries countries, String xml) {
        //Removing the first blank space
        xml = xml.substring(1,xml.length());
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = null;
        SAXHandler handler = new SAXHandler(countries);
        try {
            parser = parserFactor.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SAXHandler extends DefaultHandler {
    ArrayList<Country> countryTmp;
    Countries countries;
    Country cntry = null;

    StringBuilder iso2codeSB, regionSB, incomelevelSB = new StringBuilder();

    String name, is2code = null;
    String content = null;

    public void startDocument()throws SAXException{
        countryTmp = new ArrayList<>();
    }

    public SAXHandler(Countries countries) {

        this.countries = countries;
    }

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        switch(qName){
            //Create a new Student object when the start tag is found
            case "wb:country":
                cntry = new Country();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {

            switch (qName) {
                case "wb:country":
                    countryTmp.add(cntry);
                    break;

                case "wb:iso2Code":
                    cntry.setIso2Code(content);
                    iso2codeSB.append(content);
                    break;

                case "wb:name":
                    cntry.setName(content);
                    //cntry = new Country(name, is2code);
                    break;

                case "wb:region":
                    cntry.setRegion(content);
                    break;

                case "wb:incomeLevel":
                    cntry.setIncomeLevel(content);
                    break;
            }
        }catch (NullPointerException e){
            //System.out.println("It is a null.");
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public void endDocument() throws SAXException {
        String i2Code, region, incomeLev, name = null;
        int index = 0;

        // /Printing the list of Students obtained from XML

        for (int i = 0 ; i < countryTmp.size(); i++) {
            i2Code = countryTmp.get(i).getIso2Code();
            //System.out.println(i2Code);
            index = countries.getIndexOfCountryByIso2Code(i2Code);

            if (index == -1){
                name = countryTmp.get(i).getName();
                countries.addCountry(name, i2Code);
            }

            region = countryTmp.get(i).getRegion();
            incomeLev = countryTmp.get(i).getIncomeLevel();

            countries.setIncomeLevel(i2Code, incomeLev);
            countries.setRegion(i2Code, region);

        }
    }
}


