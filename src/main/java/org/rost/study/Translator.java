package org.rost.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Translator {
    private static final String translateApiUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private static final Properties translateApiProp = new Properties();
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String texts = "texts";
    private final HttpHeaders headers = new HttpHeaders();
    private final Map<String, String> jsonData;

    public Translator() {
        StringSubstitutor IAM_TOKEN_formatter;
        try {
            translateApiProp.load(Translator.class.getResourceAsStream("/translateAPI.properties"));
            jsonData = new HashMap<>(){{
                put("folderId", translateApiProp.getProperty("folderId"));
            }};
            IAM_TOKEN_formatter = new StringSubstitutor(Map.of("IAM_TOKEN", translateApiProp.getProperty("IAM_TOKEN")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", IAM_TOKEN_formatter.replace("Bearer ${IAM_TOKEN}"));
    }

    public String translate() {
        String result = null;
        System.out.println("Введите предложение на русском языке:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String sentenceToTranslate = reader.readLine();
            jsonData.put(texts, sentenceToTranslate);
            jsonData.put("targetLanguageCode", "en");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);
            String response = restTemplate.postForObject(translateApiUrl, request, String.class);
            result = String.join("\n",
                    objectMapper.readTree(response)
                            .withArray("translations")
                            .findValuesAsText("text")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
