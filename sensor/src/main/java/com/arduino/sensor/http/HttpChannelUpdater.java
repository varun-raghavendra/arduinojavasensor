package com.arduino.sensor.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.arduino.sensor.temperature.Temperature;

public class HttpChannelUpdater {
	public String updateChannel(String channel_api_key, Temperature temperature) throws IOException, InterruptedException, URISyntaxException {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			System.out.println("writing to channel with api key:");
			System.out.println(channel_api_key);
			
			URIBuilder uriBuilder = new URIBuilder("https://api.thingspeak.com/update.json");
			uriBuilder.
					setParameter("api_key", channel_api_key).
					setParameter("field1", temperature.getObject_temperature());
			
			HttpPost request = new HttpPost(uriBuilder.build());
            
            HttpResponse response = client.execute(request);
            var bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            var builder = new StringBuilder();
            
            String line;

            while ((line = bufReader.readLine()) != null) {

                builder.append(line);
                builder.append(System.lineSeparator());
            }

//            System.out.println(builder);
            
            return "Success";
        }
	}
}
