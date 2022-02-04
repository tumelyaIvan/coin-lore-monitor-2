package com.ivan.coinlore.monitor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivan.coinlore.monitor.entity.CurrencyPrice;

@Repository
public interface CurrencyPriceDao extends JpaRepository<CurrencyPrice, Integer> {

    CurrencyPrice findBySymbol(String symbol);
}
