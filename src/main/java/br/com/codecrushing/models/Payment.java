package br.com.codecrushing.models;

import java.math.BigDecimal;

public class Payment {
	
	private BigDecimal value;

	public Payment() {}
	public Payment(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
