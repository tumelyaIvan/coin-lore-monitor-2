package com.ivan.coinlore.monitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ivan.coinlore.monitor.dto.CryptoCurrencyDto;
import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;
import com.ivan.coinlore.monitor.service.CoinLoreWatcherService;
import com.ivan.coinlore.monitor.service.CryptoCurrencyService;

@Service
@Transactional
public class CoinLoreWatcherServiceImpl implements CoinLoreWatcherService {

	private CryptoCurrencyService currencyService;

	private RestTemplate restTemplate;

	public CoinLoreWatcherServiceImpl(CryptoCurrencyService currencyService, RestTemplate restTemplate) {
		this.currencyService = currencyService;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<CurrencyPriceDto> updatePriceForAllCurrencies() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.USER_AGENT, "Application");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		String baseUrl = "https://api.coinlore.net/api/ticker/";
		String priceUrlTemplate = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("id", "{currId}").encode().toUriString();
        Map<String, Object> params = new HashMap<>();

		List<CryptoCurrencyDto> allCurrencies = currencyService.getAllCryptoCurrencies();
		List<CurrencyPriceDto> allCurrenciesPrices = new ArrayList<>();

		for (CryptoCurrencyDto currency : allCurrencies) {
	        params.put("currId", currency.getId());
	        HttpEntity<CurrencyPriceDto[]> response = restTemplate.exchange(priceUrlTemplate,
                                                                          HttpMethod.GET, 
                                                                          entity, 
                                                                          CurrencyPriceDto[].class, 
                                                                          params);
	        CurrencyPriceDto[] priceArr = response.getBody();
	        if (priceArr.length > 0) {
	            allCurrenciesPrices.add(priceArr[0]);
	        }
		}

		return allCurrenciesPrices;
	}

}
