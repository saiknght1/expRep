package com.amazon.utils;

import static java.lang.Integer.parseInt;

public class CleanersandConvertors {


    public String priceStringCleaner(String inputString){
        String input = inputString.replaceAll("[^0-9]", "");
        return input;
    }

    public static int stringToIntConvertor(String inputString){

       return parseInt(inputString);
    }



}
