package com.mv.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreateResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = -5088848689334051023L;

	private HttpServletResponse response;
	private Long codigo;
	
	public CreateResourceEvent(Object source, HttpServletResponse response,  Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public Long getCodigo() {
		return codigo;
	}
}
