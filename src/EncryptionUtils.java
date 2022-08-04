import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptionUtils {
    private static final String SECRETE_PHRASE = "{<!Rqnm#>qb*Xmpam[";
    private static final String SALT_PHRASE = "my$altPhr@seNewL0gic";

    public EncryptionUtils() {
    }

    public static String encrypt(String strToEncrypt) {
        try {
            byte[] iv = new byte[16];
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(SECRETE_PHRASE.toCharArray(), SALT_PHRASE.getBytes(), 65536, 256);
            SecretKey secretKey = factory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception var8) {
            System.out.println("Error while encrypting: " + var8.toString());
            return null;
        }
    }

    public static String decrypt(String cipherText) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        byte[] iv = new byte[16];
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(SECRETE_PHRASE.toCharArray(), SALT_PHRASE.getBytes(), 65536, 256);
        SecretKey secretKey = factory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("PBKDF2WithHmacSHA256");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    public static void main(String[] args) {
        try {
            if (args != null && args[0] != null && args[1] != null && !args[0].equals("") && !args[1].equals("")) {
                if (args[0].equalsIgnoreCase("encrypt")) {
                    System.out.println(encrypt(args[1]));
                } else {
                    System.out.println(decrypt(args[0]));
                    //System.out.println("Invalid parameters. Please use this format: java -jar EncryptionUtils.jar encrypt <your plain text password>");
                }
            } else {
                System.out.println("Invalid parameters. Please use this format: java -jar EncryptionUtils.jar encrypt <your plain text password>");
            }
        } catch (Exception var2) {
            System.out.println("Invalid parameters. Please use this format: java -jar EncryptionUtils.jar encrypt <your plain text password>");
        }

    }
}
