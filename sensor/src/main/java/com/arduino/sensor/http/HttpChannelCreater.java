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

public class HttpChannelCreater {
	public String create(String profile_api_key, String patient_name) throws IOException, InterruptedException, URISyntaxException {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			URIBuilder uriBuilder = new URIBuilder("https://api.thingspeak.com/channels.json");
			uriBuilder.
					setParameter("api_key", profile_api_key).
					setParameter("name", patient_name).
					setParameter("field1", "Patient Temperature");
			
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
