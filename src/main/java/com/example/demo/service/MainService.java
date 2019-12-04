package com.example.demo.service;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
public class MainService {

    public String getAllCountries() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
       
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange("http://api.worldbank.org/v2/country?format=json", HttpMethod.GET, entity, String.class);

        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(response.getBody());
        JsonArray trade = tradeElement.getAsJsonArray();

        System.out.println(trade.get(1).toString());

        return trade.get(1).toString();
    }
}
