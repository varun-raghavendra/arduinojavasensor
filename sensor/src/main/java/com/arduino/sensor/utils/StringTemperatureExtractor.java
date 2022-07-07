package com.arduino.sensor.utils;

import com.arduino.sensor.temperature.Temperature;

public class StringTemperatureExtractor {
	
	public Temperature getTemperatureFromString(String temperature_string) {

		int num_correct_ambient_temperatures = 0;
		int num_correct_object_temperatures = 0;
		double ambient_temp = 0.0;
		double object_temp = 0.0;
		
		String[] temperature_series = temperature_string.split("\n");
		
		for (int i=0; i<temperature_series.length; ++i) {
			
			String[] current_temperature = temperature_series[i].split("=");
			
			if (current_temperature.length < 2) {
				continue;
			}
			
//			System.out.println(current_temperature[0]);
//			System.out.println(current_temperature[1]);
			
			if (current_temperature[0].equals("Ambient") && current_temperature.length > 1) {
				++num_correct_ambient_temperatures;
				ambient_temp += Double.parseDouble(current_temperature[1]);
			}
			
			if (current_temperature[0].equals("Object") && current_temperature.length > 1) {
				++num_correct_object_temperatures;
				object_temp += Double.parseDouble(current_temperature[1]);
			}
		}
		
		double final_ambient_temperature = 1.0*ambient_temp/num_correct_ambient_temperatures;
		double final_object_temperature = 1.0*object_temp/num_correct_object_temperatures;
		
		return new Temperature(String.valueOf(final_ambient_temperature), String.valueOf(final_object_temperature));
	}
}
