package com.ivan.coinlore.monitor.dto;

import java.util.Objects;

public class CryptoCurrencyDto {

	private int id;

	private String symbol;

	public CryptoCurrencyDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol);
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

		CryptoCurrencyDto other = (CryptoCurrencyDto) obj;
		return Objects.equals(symbol, other.symbol);
	}

	@Override
	public String toString() {
		return "CryptoCurrencyDto [id=" + id + ", symbol=" + symbol + "]";
	}
}
