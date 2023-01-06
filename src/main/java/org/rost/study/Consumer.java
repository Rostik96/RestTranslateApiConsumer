package org.rost.study;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class Consumer {
    public static void main( String[] args ) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        final Map<String, String> jsonToSendMap = new HashMap<String, String>() {{
            put("name", "testName");
            put("job", "Test job");
        }};
        //Преобразуется в JSON
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSendMap);
        String url = "https://reqres.in/api/users/";
        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println(new ObjectMapper().readTree(response).toPrettyString());
    }
}
