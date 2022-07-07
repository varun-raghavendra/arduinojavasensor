package com.arduino.sensor.temperaturesensor;

import java.util.concurrent.TimeUnit;

import com.arduino.sensor.temperature.Temperature;
import com.arduino.sensor.utils.StringTemperatureExtractor;

import arduino.Arduino;

public class TemperatureSensor {

	public Temperature senseTemperature() throws InterruptedException {
		
		Arduino myArduino = new Arduino("COM3", 9600);
		myArduino.openConnection();
		
		System.out.println("waiting...");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("starting sensing...");
		
		String temperature_string = myArduino.serialRead();
		
		myArduino.closeConnection();
		
		return new StringTemperatureExtractor().getTemperatureFromString(temperature_string);
	}
}
