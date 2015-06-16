package redes_digitais_controle_de_notas.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.slf4j.Logger;

public class SecurityHelper {

  @Inject
  private static br.gov.frameworkdemoiselle.util.ResourceBundle bundle;
  
  @Inject
  private static Logger logger;
  
  public static String cryptMD5(String param) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      BigInteger hash = new BigInteger(1, md.digest(param.getBytes()));
      param = hash.toString(32);
    } catch (NoSuchAlgorithmException e) {
      logger.info(bundle.getString("infra.msg.crypterror"), e);
    }
    return param;
  }

}
