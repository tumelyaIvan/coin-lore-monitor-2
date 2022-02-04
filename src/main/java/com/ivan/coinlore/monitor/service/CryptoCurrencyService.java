package com.ivan.coinlore.monitor.service;

import java.util.List;

import com.ivan.coinlore.monitor.dto.CryptoCurrencyDto;

public interface CryptoCurrencyService {

	List<CryptoCurrencyDto> getAllCryptoCurrencies();
}
