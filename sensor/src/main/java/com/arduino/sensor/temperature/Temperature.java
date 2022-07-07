package com.arduino.sensor.temperature;

public class Temperature {
	private String ambient_temperature;
	private String object_temperature;
	
	public Temperature(String ambient_temperature, String object_temperature) {
		this.ambient_temperature = ambient_temperature;
		this.object_temperature = object_temperature;
	}
	
	public String getAmbient_temperature() {
		return ambient_temperature;
	}
	public void setAmbient_temperature(String ambient_temperature) {
		this.ambient_temperature = ambient_temperature;
	}
	public String getObject_temperature() {
		return object_temperature;
	}
	public void setObject_temperature(String object_temperature) {
		this.object_temperature = object_temperature;
	}
}
