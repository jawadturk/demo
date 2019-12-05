package com.example.demo.service;

import com.example.demo.utils.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainServiceTest {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 201;
    private static final int CODE_FAIL = 400;

    @Autowired
    private MainService mainService;

    @Test
    public void onGetAllCountries_countryCodeIsEmpty_returnBaseResponseWithErrorCode() {

        BaseResponse baseResponse = mainService.getAllCountries("",false,false,false);

        assertEquals(baseResponse.getCode(), CODE_FAIL);
        assertEquals(baseResponse.getStatus(),ERROR_STATUS);
    }

    @Test
    public void onGetAllCountries_countryCodeIsNotEmpty_returnBaseResponseWithSuccessCode() {

        BaseResponse baseResponse = mainService.getAllCountries("AR",false,false,false);

        assertEquals(baseResponse.getCode(), CODE_SUCCESS);
        assertEquals(baseResponse.getStatus(),SUCCESS_STATUS);
        assertEquals(baseResponse.getCountries().size(),1);
    }

    @Test
    public void onGetAllCountries_countryCodeIsNotEmptyAndMatchRegion_returnBaseResponseWithSuccessCode() {

        BaseResponse baseResponse = mainService.getAllCountries("AR",true,false,false);

        assertEquals(baseResponse.getCode(), CODE_SUCCESS);
        assertEquals(baseResponse.getStatus(),SUCCESS_STATUS);
        assertEquals(baseResponse.getCountries().size(),9);
    }
}