package com.gui.projectmanagementserver.converter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonConverter {
    public static <T> T convertJsonToObject(String json, Type type) {
        Gson gson = new Gson();
        T object = gson.fromJson(json, type);
        return object;
    }

    public static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

}
