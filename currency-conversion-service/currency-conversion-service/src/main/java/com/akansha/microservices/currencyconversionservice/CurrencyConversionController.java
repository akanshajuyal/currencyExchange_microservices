package com.akansha.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy proxy;


    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/10")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            )
    {
        //RestTemplate is used for making cross calls
        //Returns a responseEntity
        //body of the response is our response

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity=
        new RestTemplate().getForEntity
                ("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(currencyConversion.getId(), from ,to, quantity,currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), "");
    }

    //crossCall using feign client
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    )
    {


        CurrencyConversion currencyConversion =
                proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from ,to, quantity,currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
    }
}
