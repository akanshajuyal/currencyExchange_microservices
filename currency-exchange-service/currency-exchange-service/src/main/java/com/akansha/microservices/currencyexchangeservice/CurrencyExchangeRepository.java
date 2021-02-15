package com.akansha.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends MongoRepository< CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
