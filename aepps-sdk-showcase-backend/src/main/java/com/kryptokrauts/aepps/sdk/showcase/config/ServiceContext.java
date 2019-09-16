package com.kryptokrauts.aepps.sdk.showcase.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kryptokrauts.aeternity.sdk.service.aeternity.AeternityServiceConfiguration;
import com.kryptokrauts.aeternity.sdk.service.aeternity.AeternityServiceFactory;
import com.kryptokrauts.aeternity.sdk.service.aeternity.impl.AeternityService;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairService;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairServiceFactory;

import lombok.Data;

@Data
@Service
public class ServiceContext {
	@Autowired
	private ServiceConfiguration serviceConfiguration;

	private KeyPairService keyPairService;

	private AeternityService aeternityService;

	@PostConstruct
	public void initalizeAEService() {
		this.keyPairService = new KeyPairServiceFactory().getService();
		if (this.serviceConfiguration.getEnvConfig().getBaseKeyPair() == null) {
			this.serviceConfiguration.getEnvConfig().setBaseKeyPair(this.keyPairService.generateBaseKeyPair());
		} else {
			if (this.serviceConfiguration.getEnvConfig().getBaseKeyPair().getPublicKey() == null
					&& this.serviceConfiguration.getEnvConfig().getBaseKeyPair().getPrivateKey() != null) {
				this.serviceConfiguration.getEnvConfig()
						.setBaseKeyPair(this.keyPairService.generateBaseKeyPairFromSecret(
								this.serviceConfiguration.getEnvConfig().getBaseKeyPair().getPrivateKey()));
			}
		}
		this.aeternityService = new AeternityServiceFactory().getService(
				AeternityServiceConfiguration.configure().baseUrl(this.serviceConfiguration.getEnvConfig().getBaseUrl())
						.contractBaseUrl(this.serviceConfiguration.getEnvConfig().getCompilerUrl())
						.baseKeyPair(this.serviceConfiguration.getEnvConfig().getBaseKeyPair()).compile());

	}
}
