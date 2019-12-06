package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.MainService;
import com.example.demo.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/all", method = RequestMethod.POST,headers="Accept=application/json")
    public BaseResponse getProducts(@RequestParam(value="countryCode", required=true) String countryCode,
                                    @RequestParam(value="matchRegion", required=false) boolean matchRegion,
                                    @RequestParam(value="incomeLevel", required=false) boolean incomeLevel,
                                    @RequestParam(value="lendingType", required=false) boolean lendingType) {

        System.out.println("countryCode "+ countryCode);
        System.out.println("matchRegion "+ matchRegion);
        System.out.println("incomeLevel "+ incomeLevel);
        System.out.println("lendingType "+ lendingType);

      return   mainService.getAllCountries(countryCode, matchRegion, incomeLevel, lendingType);
    }

    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

}
