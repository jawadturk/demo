package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.MainService;
import com.example.demo.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MainController {



    @Autowired
    MainService mainService;

    @RequestMapping(value = "/all", method = RequestMethod.POST,headers="Accept=application/json")
    public BaseResponse getProducts(@RequestParam(value="countryCode", required=true) String countryCode,
                                    @RequestParam(value="matchRegion", required=false) boolean matchRegion,
                                    @RequestParam(value="incomeLevel", required=false) boolean incomeLevel,
                                    @RequestParam(value="lendingType", required=false) boolean lendingType) {

        System.out.println("Country Code"+ countryCode);
        System.out.println("matchRegion" + matchRegion);
        System.out.println("incomeLevel" + incomeLevel);
        System.out.println("lendingType" + lendingType);

      return   mainService.getAllCountries(countryCode, matchRegion, incomeLevel, lendingType);

    }
}
