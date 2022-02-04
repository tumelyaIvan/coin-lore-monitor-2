package com.ivan.coinlore.monitor.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency_prices")
public class CurrencyPrice {

    @Column(name = "currency_id")
    @Id
    private Integer id;

    @Column(name = "currency_symbol")
    private String symbol;

    @Column(name = "currency_price")
    private Double priceUsd;

    public CurrencyPrice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.hash(symbol, priceUsd);
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

        CurrencyPrice other = (CurrencyPrice) obj;
        return Objects.equals(symbol, other.symbol) && Objects.equals(priceUsd, other.priceUsd);
    }

    @Override
    public String toString() {
        return "CurrencyPrice [id=" + id + ", symbol=" + symbol + ", price=" + priceUsd + "]";
    }
}
