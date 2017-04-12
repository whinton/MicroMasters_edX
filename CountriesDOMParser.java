package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CountriesDOMParser {
    public static void setLifeExpectancy(Countries countries, String xml) {
        //Removing the first blank space
        xml = xml.substring(1, xml.length());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Complete here...

        List<Country> cntryList = new ArrayList<>();
        Country cntry = null;

        document.getDocumentElement().normalize();

        NodeList dataList = document.getElementsByTagName("wb:data");
        NodeList cntryNodeList = document.getElementsByTagName("wb:country");
        NodeList lifeNodeList = document.getElementsByTagName("wb:value");

        for (int temp = 0; temp < dataList.getLength(); temp++) {

            //Setup new Country Object
            try {

                cntry = new Country();
                Node nNode = dataList.item(temp);
                Element eElement = (Element) nNode;

                //Get country data
                Element countryE = (Element) cntryNodeList.item(temp);
                String i2code = countryE.getAttribute("id");
                String countryName = countryE.getFirstChild().getNodeValue();
                cntry.setName(countryName);
                cntry.setIso2Code(i2code);
                //System.out.println("Country : " + countryE.getFirstChild().getNodeValue());
                //System.out.println("I2Code " + i2code);

                //Now for life expectancy
                Element lifeE = (Element) lifeNodeList.item(temp);
                String lifeExpect = lifeE.getFirstChild().getNodeValue();
                cntry.setLifeExp(Double.parseDouble(lifeExpect));
                //System.out.println("Life expectancy : " + lifeE.getFirstChild().getNodeValue());
            }catch (NullPointerException e){
                //System.out.println("Life expectancy :" + "-");

            }
            cntryList.add(cntry);
        }
        //Adding to countries

            for (int i = 0; i < cntryList.size()-1; i++) {
                String i2Code = cntryList.get(i).getIso2Code();
                int index = countries.getIndexOfCountryByIso2Code(i2Code);

                if (index == -1) {
                    String name = cntryList.get(i).getName();
                    countries.addCountry(name, i2Code);
                }

                try {
                    Double lifeExp = cntryList.get(i).getLifeExpectancy();
                    countries.setLifeExpectancy(i2Code, lifeExp);
                } catch (NullPointerException e){
                    double lifeExp = 0.0;
                    countries.setLifeExpectancy(i2Code, lifeExp);
                }
            }
    }
}




