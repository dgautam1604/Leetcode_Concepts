package com.example.roughwork.Extras;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class result {
    public static List<String> getResponses(List<String> valid_auth_tokens, List<List<String>> requests){
        List<String> output=new ArrayList<>();

        for(List<String> l:requests){
            String httpType=l.get(0);
            String Url=l.get(1);
            Map<String, String> paramMap = getParametersFromURL(Url);
            if(httpType.equals("GET")){
                if(valid_auth_tokens.contains(paramMap.get("tokens"))){
                    String outputString="VALID";
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        outputString=outputString+","+entry.getKey() + "," + entry.getValue();
                        output.add(outputString);
                    }
                }else{
                    output.add("INVALID");
                }
            }else if(httpType.equals("POST")){
                if(valid_auth_tokens.contains(paramMap.get("tokens")) && isAlphanumericLowerCase(paramMap.get("csrf"))){
                    String outputString="VALID";
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        if(!entry.getKey().equals("csrf"))
                            outputString=outputString+","+entry.getKey() + "," + entry.getValue();
                        output.add(outputString);
                    }
                }else{
                    output.add("INVALID");
                }
            }
        }
        return output;
    }
    public static boolean isAlphanumericLowerCase(String input) {
        // Check for minimum length
        if (input.length() < 8) {
            return false;
        }

        // Check if all characters are lowercase alphanumeric
        for (char c : input.toCharArray()) {
            if (!Character.isLowerCase(c) && !Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        String urlString = "http://www.sample.com/?token=aadd34asdaf&variable1=value1&variable2=value2";
        Map<String, String> paramMap = getParametersFromURL(urlString);

        // Print the parameters stored in the HashMap
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, String> getParametersFromURL(String urlString) {
        Map<String, String> paramMap = new HashMap<>();

        try {
            URL url = new URL(urlString);
            String query = url.getQuery();

            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = URLDecoder.decode(keyValue[1], "UTF-8");
                    paramMap.put(key, value);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paramMap;
    }
}
