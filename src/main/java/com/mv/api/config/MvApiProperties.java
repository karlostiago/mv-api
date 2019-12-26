package com.mv.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mv")
public class MvApiProperties {
	
	private String origemPermitida = "http://localhost:4200";
	
	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}
	
	public String getOrigemPermitida() {
		return origemPermitida;
	}
}
