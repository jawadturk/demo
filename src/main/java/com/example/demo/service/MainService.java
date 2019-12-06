package com.example.demo.service;



import com.example.demo.model.Country;
import com.example.demo.utils.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MainService {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 201;
    private static final int CODE_FAIL = 400;
    public BaseResponse getAllCountries(String countryCode, boolean matchRegion, boolean incomeLevel, boolean lendingType) {

        if (countryCode.isEmpty()) {
            return new BaseResponse(ERROR_STATUS, CODE_FAIL);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange("http://api.worldbank.org/v2/country?format=json&per_page=304", HttpMethod.GET, entity, String.class);

        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(response.getBody());

        JsonArray trade = tradeElement.getAsJsonArray().get(1).getAsJsonArray();

        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries = new ArrayList<>();
        try {
           countries =Arrays.asList( mapper.readValue(trade.toString(), Country[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }


        Country matchingObject = countries.stream().
                filter(c -> c.getIso2Code().equalsIgnoreCase(countryCode)).
                findAny().orElse(null);

            List<Country> result = countries.stream()
                    .filter(matchRegion? country ->  country.getRegion().getIso2code().equalsIgnoreCase(matchingObject.getRegion().getIso2code()): country ->  true)
                    .filter(incomeLevel? country ->  country.getIncomeLevel().getIso2code().equalsIgnoreCase(matchingObject.getIncomeLevel().getIso2code()): country ->  true)
                    .filter(lendingType? country ->  country.getLendingType().getIso2code().equalsIgnoreCase(matchingObject.getLendingType().getIso2code()):  country -> true)
                    .collect(Collectors.toList());

           if (!matchRegion && !incomeLevel && !lendingType){
               result = countries.stream()
                       .filter(country -> country.getIso2Code().equalsIgnoreCase(countryCode))
                       .collect(Collectors.toList());
           }
         return  new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS, result);
    }
}
