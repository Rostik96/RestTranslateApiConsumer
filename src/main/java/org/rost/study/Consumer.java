package org.rost.study;

import java.util.Map;

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
        String url = "https://reqres.in/api/users/2";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(new ObjectMapper().readTree(response).toPrettyString());
    }
}
