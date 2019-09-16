package com.kryptokrauts.aepps.sdk.showcase.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kryptokrauts.aepps.sdk.showcase.config.ServiceContext;

@RestController
@RequestMapping("/config")
public class ServiceConfigurationController {

	@Autowired
	private ServiceContext serviceContext;

	@GetMapping(path = "")
	public String ping() {
		return ServiceConfigurationController.class.getName() + " is available";
	}

	@GetMapping(path = "/get")
	public String getCurrentConfiguration() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.AUTO_DETECT_CREATORS, MapperFeature.AUTO_DETECT_FIELDS,
				MapperFeature.AUTO_DETECT_GETTERS, MapperFeature.AUTO_DETECT_IS_GETTERS);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return mapper.writeValueAsString(this.serviceContext.getServiceConfiguration());
	}
}
