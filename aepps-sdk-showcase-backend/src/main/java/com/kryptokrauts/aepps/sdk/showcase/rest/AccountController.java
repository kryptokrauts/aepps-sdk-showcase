package com.kryptokrauts.aepps.sdk.showcase.rest;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kryptokrauts.aepps.sdk.showcase.config.ServiceContext;
import com.kryptokrauts.aeternity.sdk.service.account.domain.AccountResult;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	ServiceContext serviceContext;

	@GetMapping(path = "")
	public String ping() {
		return AccountController.class.getName() + " is available";
	}

	@GetMapping(path = "/getAccount")
	public AccountResult getAccount(@RequestParam(required = false) final String publicKey) {
		return this.serviceContext.getAeternityService().accounts.blockingGetAccount(Optional.ofNullable(publicKey));
	}

	@GetMapping(path = "/getAccountsNextNonce")
	public BigInteger getAccountsNextNonce(@RequestParam(required = false) final String publicKey) {
		return this.serviceContext.getAeternityService().accounts
				.blockingGetNextBaseKeypairNonce(Optional.ofNullable(publicKey));
	}
}
