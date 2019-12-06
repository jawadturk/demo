package com.example.demo.utils;


import com.example.demo.model.Country;

import java.util.List;

public class BaseResponse {
    private String status;
    private Integer code;
    private List<Country> countries;

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public List<Country> getCountries() {
        return countries;
    }

 public BaseResponse(String status, Integer code) {
  this.status = status;
  this.code = code;
 }

 public BaseResponse(String status, Integer code, List<Country> countries) {
  this.status = status;
  this.code = code;
  this.countries = countries;
 }
}
