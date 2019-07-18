package com.kryptokrauts.aepps.sdk.showcase.rest;

import com.kryptokrauts.aeternity.sdk.domain.secret.impl.BaseKeyPair;
import com.kryptokrauts.aeternity.sdk.domain.secret.impl.MnemonicKeyPair;
import com.kryptokrauts.aeternity.sdk.exception.AException;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairService;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairServiceConfiguration;
import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairServiceFactory;
import com.kryptokrauts.aeternity.sdk.util.EncodingUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keypair")
public class KeyPairController {

  private static KeyPairService keyPairService = new KeyPairServiceFactory().getService();

  @GetMapping(path = "")
  public String ping() {
    return KeyPairController.class.getName() + " is available";
  }

  @GetMapping(path = "/generate")
  public BaseKeyPair generateKeyPair() {
    return keyPairService.generateBaseKeyPair();
  }

  @GetMapping(path = "/generateMnemonic")
  public List<String> generateMnemonic(@RequestParam Optional<Integer> entropyInBytes)
      throws AException {
    MnemonicKeyPair mnemonicKeyPair;
    if (entropyInBytes.isPresent()) {
      mnemonicKeyPair = new KeyPairServiceFactory().getService(
          KeyPairServiceConfiguration.configure().entropySizeInByte(entropyInBytes.get()).compile())
          .generateMasterMnemonicKeyPair(null);
    } else {
      mnemonicKeyPair = keyPairService.generateMasterMnemonicKeyPair(null);
    }
    return mnemonicKeyPair.getMnemonicSeedWords();
  }

  @GetMapping(path = "/recover")
  public BaseKeyPair recoverFromPrivateKey(@RequestParam String privateKey) {
    return keyPairService.generateBaseKeyPairFromSecret(privateKey);
  }

  @GetMapping(path = "/recoverFromMnemonic")
  public List<BaseKeyPair> recoverFromMnemonic(@RequestParam List<String> mnemonic,
      @RequestParam int numberOfKeyPairs,
      @RequestParam Optional<String> password)
      throws AException {
    List<BaseKeyPair> baseKeyPairs = new ArrayList<>();
    MnemonicKeyPair mnemonicKeyPair = keyPairService
        .recoverMasterMnemonicKeyPair(mnemonic, password.orElse(null));
    for (int i = 0; i < numberOfKeyPairs; i++) {
      baseKeyPairs.add(EncodingUtils
          .createBaseKeyPair(keyPairService.generateDerivedKey(mnemonicKeyPair, true)));
    }
    return baseKeyPairs;
  }
}
