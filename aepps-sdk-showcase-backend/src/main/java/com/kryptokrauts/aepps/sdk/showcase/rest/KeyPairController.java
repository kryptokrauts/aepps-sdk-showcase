package com.kryptokrauts.aepps.sdk.showcase.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kryptokrauts.aeternity.sdk.domain.secret.impl.BaseKeyPair;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairServiceFactory;

@RestController
@RequestMapping("/keypair")
public class KeyPairController {

	@RequestMapping(method = RequestMethod.GET, path = "")
	public String ping() {
		return KeyPairController.class.getName() + " is available";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/generate")
	public BaseKeyPair generateKeyPair() {
		return new KeyPairServiceFactory().getService().generateBaseKeyPair();
	}

}
