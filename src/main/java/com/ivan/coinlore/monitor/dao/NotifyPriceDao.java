package com.ivan.coinlore.monitor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.coinlore.monitor.entity.NotifyPrice;

public interface NotifyPriceDao extends JpaRepository<NotifyPrice, Integer> {
}
