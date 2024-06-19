package com.example.roughwork.Extras;
import org.json.*;
public class JsonStringToJsonObjectExample
{
    public static void main(String args[])
    {
        String string = "{\"name\": \"Sam Smith\", \"technology\": \"Python\"}";
        JSONObject json = new JSONObject(string);
        System.out.println(json.toString());
        String technology = json.getString("technology");
        System.out.println(technology);
    }
}