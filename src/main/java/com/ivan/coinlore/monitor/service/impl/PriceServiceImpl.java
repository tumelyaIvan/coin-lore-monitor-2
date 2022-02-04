package com.ivan.coinlore.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.coinlore.monitor.dao.CurrencyPriceDao;
import com.ivan.coinlore.monitor.dao.NotifyPriceDao;
import com.ivan.coinlore.monitor.dto.CurrencyPriceDto;
import com.ivan.coinlore.monitor.dto.NotifyPriceDto;
import com.ivan.coinlore.monitor.entity.CurrencyPrice;
import com.ivan.coinlore.monitor.entity.NotifyPrice;
import com.ivan.coinlore.monitor.service.PriceService;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private CurrencyPriceDao priceDao;

    private NotifyPriceDao notifyDao;  
    
    @Autowired
    public PriceServiceImpl(CurrencyPriceDao priceDao, NotifyPriceDao notifyDao) {
        this.priceDao = priceDao;
        this.notifyDao = notifyDao;
    }

    @Override
    public void saveNewPrices(List<CurrencyPriceDto> allPrices) {
        List<CurrencyPrice> allPriceEntities = new ArrayList<>();
        
        for (CurrencyPriceDto dto : allPrices) {
            CurrencyPrice entity = new CurrencyPrice();
            entity.setId(dto.getId());
            entity.setSymbol(dto.getSymbol());
            entity.setPriceUsd(dto.getPriceUsd());
            
            allPriceEntities.add(entity);
        }

        priceDao.saveAll(allPriceEntities);
    }

    @Override
    public CurrencyPriceDto getCurrencyPrice(String currencyCode) {
        CurrencyPrice currencyPrice = priceDao.findBySymbol(currencyCode);

        CurrencyPriceDto dto = new CurrencyPriceDto();
        dto.setId(currencyPrice.getId());
        dto.setSymbol(currencyPrice.getSymbol());
        dto.setPriceUsd(currencyPrice.getPriceUsd());

        return dto;
    }

    @Override
    public boolean notifyPrice(String userName, String currencyCode) {
        CurrencyPrice currencyPrice = priceDao.findBySymbol(currencyCode);

        NotifyPrice notifyPrice = new NotifyPrice();
        notifyPrice.setSymbol(currencyPrice.getSymbol());
        notifyPrice.setUserName(userName);
        notifyPrice.setPriceUsd(currencyPrice.getPriceUsd());
        
        notifyDao.save(notifyPrice);

        return true;
    }

    @Override
    public List<NotifyPriceDto> getAllNotifyPrices() {
        List<NotifyPrice> allEntities = notifyDao.findAll();
        List<NotifyPriceDto> allDtos = new ArrayList<>();

        for (NotifyPrice entity : allEntities) {
            NotifyPriceDto dto = new NotifyPriceDto();
            dto.setId(entity.getId());
            dto.setSymbol(entity.getSymbol());
            dto.setPriceUsd(entity.getPriceUsd());
            dto.setUserName(entity.getUserName());
            
            allDtos.add(dto);
        }

        return allDtos;
    }
}
