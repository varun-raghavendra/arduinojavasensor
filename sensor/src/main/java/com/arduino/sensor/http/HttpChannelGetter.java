package com.arduino.sensor.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.arduino.sensor.utils.JsonToMap;

public class HttpChannelGetter {

	public ArrayList<Map<String, Object>> get(String profile_api_key) throws IOException, URISyntaxException{
		
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			URIBuilder uriBuilder = new URIBuilder("https://api.thingspeak.com/channels.json");
			uriBuilder.
					setParameter("api_key", profile_api_key);
			
			HttpGet request = new HttpGet(uriBuilder.build());
            
            HttpResponse response = client.execute(request);
            
            var bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            var builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                
                builder.append(line);
                builder.append(System.lineSeparator());
            }

//            System.out.println(builder.toString());
            
            JsonToMap mapConverter = new JsonToMap();
            ArrayList<Map<String, Object>> channelList = mapConverter.jsonToMap(builder.toString());
            
//            ArrayList<Map<String, Object>> api_key_list = (ArrayList<Map<String, Object>>) channelList.get(0).get("api_keys");
//			System.out.println((String) api_key_list.get(0).get("api_key"));
            
            return channelList;
        }
	}
}
