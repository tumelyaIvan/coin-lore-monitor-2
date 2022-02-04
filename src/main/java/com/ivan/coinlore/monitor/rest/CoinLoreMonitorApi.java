package com.ivan.coinlore.monitor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.coinlore.monitor.dto.CryptoCurrencyDto;
import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;
import com.ivan.coinlore.monitor.service.CryptoCurrencyService;
import com.ivan.coinlore.monitor.service.PriceService;

@RestController
@RequestMapping(value = "/api/coin-lore", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
public class CoinLoreMonitorApi {

	private CryptoCurrencyService currencyService;

	private PriceService priceService;

	@Autowired
    public CoinLoreMonitorApi(CryptoCurrencyService currencyService, PriceService priceService) {
		this.currencyService = currencyService;
		this.priceService = priceService;
	}

	@GetMapping
    @RequestMapping(value = "/currencies/list")
	public ResponseEntity<List<CryptoCurrencyDto>> getAllAvailableCurrencies() {
		List<CryptoCurrencyDto> allCurrencies = currencyService.getAllCryptoCurrencies();
		return new ResponseEntity<>(allCurrencies, HttpStatus.OK);
	}

	@GetMapping
	@RequestMapping(value = "/price/{currencyCode}")
	public ResponseEntity<CurrencyPriceDto> getCurrencyPrice(@PathVariable String currencyCode) {
        CurrencyPriceDto currencyPrice = priceService.getCurrencyPrice(currencyCode);
        return new ResponseEntity<>(currencyPrice, HttpStatus.OK);
    }

	@PostMapping
	@RequestMapping(value = "/notify/{userName}/{currencyCode}")
	public ResponseEntity<Boolean> notifyPrice(@PathVariable String userName, @PathVariable String currencyCode) {
	    boolean result = priceService.notifyPrice(userName, currencyCode);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	} 
}
