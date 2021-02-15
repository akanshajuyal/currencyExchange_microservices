package com.akansha.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//in the name we are hardcoding the name of the feign client
//but we want to make different instances of this service and distribute the load between them
//name is usually the name of the application whose service we want to call


//@FeignClient(name = "currency-exchange", url = "localhost:8001")
//After configuring eureka- spring cloud load balancer
//we dont have send URLEna
@FeignClient(name = "currency-exchange")

public interface CurrencyExchangeProxy {

    //the methods we want to call should be the part of the method definition

    //Currency conversion bean is similar to the CurrencyExchangeBean
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    );


}
