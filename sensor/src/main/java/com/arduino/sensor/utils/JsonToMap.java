package com.arduino.sensor.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMap {

	public ArrayList<Map<String, Object>> jsonToMap(String jsonString) {

		ObjectMapper mapper = new ObjectMapper();

        try {

            // convert JSON string to Map
        	ArrayList<Map<String, Object>> myObjects = mapper.readValue(jsonString , new TypeReference<ArrayList<Map<String, Object>>>(){});

			// it works
            //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

        	
            return myObjects;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return null;
    }
}
