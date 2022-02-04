package com.ivan.coinlore.monitor.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyPriceDto {

	private Integer id;

	private String symbol;

	private Double priceUsd;

	public CurrencyPriceDto() {
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

    @JsonProperty("price_usd")
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

		CurrencyPriceDto other = (CurrencyPriceDto) obj;
		return Objects.equals(symbol, other.symbol) && Objects.equals(priceUsd, other.priceUsd);
	}

	@Override
	public String toString() {
		return "CurrencyPriceDto [id=" + id + ", symbol=" + symbol + ", price=" + priceUsd + "]";
	}
}
