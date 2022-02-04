package com.ivan.coinlore.monitor.dto;

import java.util.Objects;

public class NotifyPriceDto {

    private Integer id;

    private String userName;

    private String symbol;

    private Double priceUsd;

    public NotifyPriceDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        NotifyPriceDto other = (NotifyPriceDto) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "NotifyPriceDto [id=" + id + ", userName=" + userName + ", symbol=" + symbol + ", priceUsd=" + priceUsd + "]";
    }
}
