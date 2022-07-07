package com.arduino.sensor.channel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.arduino.sensor.http.HttpChannelCreater;
import com.arduino.sensor.http.HttpChannelGetter;
import com.arduino.sensor.http.HttpChannelUpdater;
import com.arduino.sensor.temperature.Temperature;
import com.arduino.sensor.utils.Patient;

public class ChannelManager {
	
	private String profile_api_key;
	
	public ChannelManager() {
		this.profile_api_key = "6MGRGB19DNFMEEEF";
	}
	
	private ArrayList<Map<String, Object>> getAllChannels() throws IOException, URISyntaxException {
		
		// Complete method
		HttpChannelGetter httpChannelGetter = new HttpChannelGetter();
		System.out.println(httpChannelGetter.get(profile_api_key));
		return httpChannelGetter.get(profile_api_key);
	}
	
	public String createChannel(String patientName) throws IOException, InterruptedException, URISyntaxException {
		
		// Complete method
		HttpChannelCreater httpChannelCreater = new HttpChannelCreater();
		httpChannelCreater.create(profile_api_key, patientName);
		return "Success";
	}
	
	public String getApiKey(String patientName) throws IOException, URISyntaxException {
		
		// Complete method
		ArrayList<Map<String, Object>> channelList = getAllChannels();
		
		System.out.println("Patient Name is: ");
		System.out.println(channelList.toString());
		
		for (int i=0; i<channelList.size(); ++i) {
			
			System.out.println("Current channel name is: ");
			System.out.println(channelList.get(i).get("name"));
			
			if (channelList.get(i).get("name").equals(patientName)) {
				ArrayList<Map<String, Object>> api_key_list = (ArrayList<Map<String, Object>>) channelList.get(i).get("api_keys");
				
				System.out.println("api keys of channel i:");
				System.out.println(channelList.get(i).get("api_keys"));
				
				return (String) api_key_list.get(0).get("api_key");
			}
		}
		
		return "not found";
	}
	
	public int getNumChannels() throws IOException, URISyntaxException {
		
		// Complete method
		ArrayList<Map<String, Object>> channelList = getAllChannels();
		return channelList.size();
	}
	
	public String findPatient(String patientName) throws IOException, URISyntaxException {
		
		// Complete method
		return getApiKey(patientName);
	}
	
	public String pushToChannel(String channel_api_key, Temperature temperature) throws IOException, InterruptedException, URISyntaxException {
		
		// Complete method
		HttpChannelUpdater httpChannelUpdater = new HttpChannelUpdater();
		httpChannelUpdater.updateChannel(channel_api_key, temperature);
		return "Success";
	}
	
}

