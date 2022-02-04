package com.ivan.coinlore.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;
import com.ivan.coinlore.monitor.dto.NotifyPriceDto;
import com.ivan.coinlore.monitor.service.CoinLoreWatcherService;
import com.ivan.coinlore.monitor.service.PriceService;
import com.ivan.coinlore.monitor.service.SchedulingService;

@Service
@Transactional
public class SchedulingServiceImpl implements SchedulingService {

    private Logger logger = Logger.getLogger(SchedulingServiceImpl.class);
    
	private CoinLoreWatcherService watcherService;

	private PriceService priceService; 

   @Autowired
	public SchedulingServiceImpl(CoinLoreWatcherService watcherService, PriceService priceService) {
        this.watcherService = watcherService;
        this.priceService = priceService;
    }

    @Override
	@Scheduled(fixedDelay = 1000 * 60)
	public void updateCryptoPrices() {
		List<CurrencyPriceDto> allCurrenciesPrices = watcherService.updatePriceForAllCurrencies();
		priceService.saveNewPrices(allCurrenciesPrices);

		List<NotifyPriceDto> allNotifyPrices = priceService.getAllNotifyPrices();		

        for (CurrencyPriceDto actualPriceDto : allCurrenciesPrices) {
            String symbol = actualPriceDto.getSymbol();
            double actualPrice = actualPriceDto.getPriceUsd();

            List<NotifyPriceDto> notifyForSymbol = getAllNotyfyPricesNySymbol(allNotifyPrices, symbol);
            for (NotifyPriceDto dto : notifyForSymbol) {
                double notifyPrice = dto.getPriceUsd();
                double delta = (actualPrice * 100 / notifyPrice) - 100;
                delta = Math.abs(delta);
                
                if (delta > 1) {
                    logger.warn("Price Changed! Symbol: " + dto.getSymbol() 
                              + ", User Name: " + dto.getUserName() 
                            + ", Delta: " + delta);
                }
            }
        }
    }
    
    private List<NotifyPriceDto> getAllNotyfyPricesNySymbol(List<NotifyPriceDto> allNotifyPrices, String symbol) {
        List<NotifyPriceDto> symbolPrices = new ArrayList<>();

        for (NotifyPriceDto dto : allNotifyPrices) {
            if (symbol.contentEquals(dto.getSymbol())) {
                symbolPrices.add(dto);
            }
        }

        return symbolPrices;
    }
}
