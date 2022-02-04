package com.ivan.coinlore.monitor.service;

import java.util.List;

import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;
import com.ivan.coinlore.monitor.dto.NotifyPriceDto;

public interface PriceService {

    void saveNewPrices(List<CurrencyPriceDto> allPrices);

    CurrencyPriceDto getCurrencyPrice(String currencyCode); 

    boolean notifyPrice(String userName, String currencyCode);

    List<NotifyPriceDto> getAllNotifyPrices();
}
