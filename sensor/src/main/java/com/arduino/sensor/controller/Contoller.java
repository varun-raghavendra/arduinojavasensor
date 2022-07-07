package com.arduino.sensor.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.sensor.channel.ChannelManager;
import com.arduino.sensor.temperature.Temperature;
import com.arduino.sensor.temperaturesensor.TemperatureSensor;
import com.arduino.sensor.utils.Patient;

@Controller 
public class Contoller {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
    
    @RequestMapping(value="/test", method=RequestMethod.POST)
    public @ResponseBody String updateTemperature(Patient patient) throws InterruptedException, IOException, URISyntaxException {
    	
    	// Sense temperature of the patient
    	Temperature patient_temperature = new TemperatureSensor().senseTemperature();
    	
    	// Push patient to Thingspeak Cloud
    	ChannelManager channelManager = new ChannelManager();
    	String status = channelManager.findPatient(patient.getName());
    	
    	if (status.equals("not found")) {
    		if (channelManager.getNumChannels() == 4) {
    			return "Channel limit reached. Please delete existing channel to push";
    		}
    		
    		channelManager.createChannel(patient.getName());
    		System.out.println("New Channel Created!");
    	}
    	
    	channelManager.pushToChannel(channelManager.getApiKey(patient.getName()), patient_temperature);
    	
    	return "Success";
    }
}
