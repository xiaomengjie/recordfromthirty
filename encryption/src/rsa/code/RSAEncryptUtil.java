package src.rsa.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAEncryptUtil {

    public static final String KEY_ALGORITHM = "RSA";
    private static final RSAEncryptUtil util = new RSAEncryptUtil();

    private static Cipher cipher;

    public static RSAEncryptUtil getInstance() throws Exception{
        cipher = Cipher.getInstance(KEY_ALGORITHM);
        return util;
    }

    public PublicKey convertByteToKey(byte[] key) throws Exception{
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception{
        PublicKey publicKey = convertByteToKey(key);
        return encryptByPublicKey(data, 0, data.length, publicKey);
    }

    public byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception{
        return encryptByPublicKey(data, 0, data.length, publicKey);
    }

    public byte[] encryptByPublicKey(byte[] data, int inputOffset, int inputLen, PublicKey key) throws Exception{
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data, inputOffset, inputLen);
    }

    public void encryptFileByPublicKey(String fileName, PublicKey publicKey) throws Exception{
        encryptFileByPublicKey(fileName, publicKey.getEncoded());
    }

    public void encryptFileByPublicKey(String fileName, byte[] key) throws Exception{
        File file = new File(fileName);
        String parentName = file.getParentFile().getName();
        String encryptFilePath = file.getParentFile().getParent() + "/" + parentName + "_encrypt";
        String encryptFileName = encryptFilePath + "/" + file.getName().replace(".md", "") +  "_encrypt.md";

        File encryptParentFile = new File(encryptFilePath);
        if (!encryptParentFile.exists()) encryptParentFile.mkdir();

        File encryptFile = new File(encryptFileName);
        if (encryptFile.exists()) encryptFile.delete();

        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(encryptFileName);
        PublicKey publicKey = convertByteToKey(key);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes)) != -1){
            byte[] encrypt = encryptByPublicKey(bytes, 0, length, publicKey);
            fileOutputStream.write(encrypt);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
