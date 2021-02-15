package com.akansha.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	@Autowired
	CurrencyExchangeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			CurrencyExchange c1 = new CurrencyExchange(new Long("1"), "INR", "USD", new BigDecimal("65"));
			CurrencyExchange c2 = new CurrencyExchange(new Long("2"), "INR", "EUR", new BigDecimal("75"));

			repository.save(c1);
			repository.save(c2);

			List<CurrencyExchange> exchange = repository.findAll();
			 for(CurrencyExchange c : exchange)
			 {
			 	System.out.println(c.toString());
			 }

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}
}
