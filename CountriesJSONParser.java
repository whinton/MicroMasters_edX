package com.company;

import javax.json.*;
import java.io.StringReader;
import java.util.ArrayList;


public class CountriesJSONParser {

    public static void setCountries(Countries countries, String json) {
        Country country = new Country();
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        // Creates the json tree structure
        JsonStructure jsonst = jsonReader.read();
        JsonObject object = (JsonObject) jsonst;
        ArrayList<String> sbCntry = new ArrayList<>();
        ArrayList<String> sbIso2 = new ArrayList<>();
        for (int i = 0; i < sbIso2.size(); i++)
            System.out.println(i);

        navigateTree(jsonst, "result", sbCntry, sbIso2);

        for (int i = 0; i < sbCntry.size(); i++) {
            int last =  sbCntry.size() - (i + 1);
            String sbOut = sbCntry.get(last);
            String sbIso = sbIso2.get(last);
            countries.addCountry(sbOut, sbIso);
            //System.out.println(countries.report());
            //System.out.println(sbIso);
        }
    }

    public static void navigateTree(JsonValue tree, String key, ArrayList<String> sbCntry, ArrayList<String> sbIso2) {

        String country = "";
        String iso2code = "";
        Boolean ok = false;
        int i = 0;
        int j = 0;

        {
            switch (tree.getValueType()) {
                case OBJECT:
                    JsonObject object = (JsonObject) tree;

                    for (String  name : object.keySet()) {
                        navigateTree(object.get(name), name, sbCntry, sbIso2);
                    }

                    break;

                case ARRAY:
                    JsonArray array = (JsonArray) tree;
                    for (JsonValue val : array)
                        navigateTree(val, null, sbCntry, sbIso2 );
                    break;

                case STRING:
                    JsonString st = (JsonString) tree;
                    String nam = "name";
                    String i2code = "alpha2_code";

                    if (nam.equals(key)) {
                        country = ((JsonString) tree).getString();
                        sbCntry.add(i, country);
                        i++;
                    }
                    if (i2code.equals(key)) {
                        iso2code = ((JsonString) tree).getString();
                        sbIso2.add(j, iso2code);
                        j++;
                    }
                    break;

                case NUMBER:
                    JsonNumber num = (JsonNumber) tree;
                    break;

                case TRUE:
                case FALSE:
                case NULL:
                    //System.out.println(tree.getValueType().toString());
                    break;
            }

        }
    }
}
