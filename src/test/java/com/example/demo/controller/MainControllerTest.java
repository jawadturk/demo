package com.example.demo.controller;

import com.example.demo.service.MainService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    MainService mainService = Mockito.mock(MainService.class);
    @Test
   public void getProducts() {
        MainController mainController = new MainController();
        mainController.setMainService(mainService);
        mainController.getProducts("",false,false,false);

        verify(mainService, times(1)).getAllCountries("",false,false,false);
    }
}
