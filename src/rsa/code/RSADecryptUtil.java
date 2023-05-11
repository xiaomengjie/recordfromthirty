package src.rsa.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public class RSADecryptUtil {
    public static final String KEY_ALGORITHM = "RSA";
    private static final RSADecryptUtil util = new RSADecryptUtil();

    private static Cipher cipher;

    private volatile PrivateKey privateKey;

    public static RSADecryptUtil getInstance() throws Exception {
        cipher = Cipher.getInstance(KEY_ALGORITHM);
        return util;
    }

    public PrivateKey convertByteToPrivateKey(byte[] key) throws Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception{
        return decryptByPrivateKey(data, 0, data.length, convertByteToPrivateKey(key));
    }

    public byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception{
        return decryptByPrivateKey(data, 0, data.length, privateKey);
    }

    public byte[] decryptByPrivateKey(byte[] data, int inputOffset, int inputLen, PrivateKey key) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data, inputOffset, inputLen);
    }

    public void decryptFileByPrivateKey(String fileName, PrivateKey privateKey) throws Exception{
        File encryptFile = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(encryptFile);
        FileOutputStream fileOutputStream = new FileOutputStream(encryptFile.getPath().replace("_encrypt.md", "_decrypt.md"));
        byte[] bytes = new byte[(int)encryptFile.length()];
        fileInputStream.read(bytes);
        byte[] decrypt = decryptByPrivateKey(bytes, privateKey);
        fileOutputStream.write(decrypt);
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void decryptFileByPrivateKey(String fileName, byte[] key) throws Exception{
        if (privateKey == null) privateKey = convertByteToPrivateKey(key);
        decryptFileByPrivateKey(fileName, privateKey);
    }
}
