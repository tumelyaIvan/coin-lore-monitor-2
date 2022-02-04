package com.ivan.coinlore.monitor.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.coinlore.monitor.dto.CryptoCurrencyDto;
import com.ivan.coinlore.monitor.service.CryptoCurrencyService;

@Service
@Transactional
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

	private ObjectMapper jacksonMapper;

	private ResourceLoader resourceLoader;

	@Autowired
	public CryptoCurrencyServiceImpl(ObjectMapper jacksonMapper, ResourceLoader resourceLoader) {
		this.jacksonMapper = jacksonMapper;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<CryptoCurrencyDto> getAllCryptoCurrencies() {
		try {
			 Resource resource = resourceLoader.getResource("classpath:crypto-currencies-list.json");
			 InputStream currListStream = resource.getInputStream();
			 CryptoCurrencyDto[] currencyArray = jacksonMapper.readValue(currListStream, CryptoCurrencyDto[].class);
			 List<CryptoCurrencyDto> allCurrenciesList = new ArrayList<>(Arrays.asList(currencyArray));

			 return allCurrenciesList;
		} catch (Exception e) {
			throw new RuntimeException("Can not read list of currencies because of: ", e);
		}
	}
}
