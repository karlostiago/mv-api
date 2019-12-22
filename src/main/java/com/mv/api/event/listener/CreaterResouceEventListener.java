package com.mv.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mv.api.event.CreateResourceEvent;

public class CreaterResouceEventListener implements ApplicationListener<CreateResourceEvent>  {

	@Override
	public void onApplicationEvent(CreateResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		
		addHeaderLocation(response, codigo);
	}
	
	private void addHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(codigo).toUri();
		response.addHeader("Location", uri.toASCIIString());
	}
}
