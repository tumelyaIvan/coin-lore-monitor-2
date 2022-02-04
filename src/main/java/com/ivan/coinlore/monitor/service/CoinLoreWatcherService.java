package com.ivan.coinlore.monitor.service;

import java.util.List;

import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;

public interface CoinLoreWatcherService {

	List<CurrencyPriceDto> updatePriceForAllCurrencies();
}
