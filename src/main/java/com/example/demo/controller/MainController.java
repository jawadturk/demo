package com.example.demo.controller;

import com.example.demo.model.Example;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class MainController {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int CODE_FAIL = 101;

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/all", method = RequestMethod.POST,headers="Accept=application/json")
    public String getProducts(@RequestParam(value="countryCode", required=false) String countryCode,
                            @RequestParam(value="matchRegion", required=false) boolean matchRegion,
                            @RequestParam(value="incomeLevel", required=false) String incomeLevel,
                            @RequestParam(value="lendingType", required=false) String lendingType)
    {


      return   mainService.getAllCountries();

    }
}
