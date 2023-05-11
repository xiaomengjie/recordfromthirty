package src.rsa.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class RSAAlgorithm {

    public static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1024;
    private final String PUBLIC_KEY_PATH = "./key/public";
    private final String PRIVATE_KEY_PATH = "./key/private";
    private final String ENCRYPT_FILENAME_FILE_PATH = "encrypt_file_name.md";
    private final String DECRYPT_FILENAME_FILE_PATH = "decrypt_file_name.md";

    public void encryptFiles() {
        /**
         * 1、生成密钥对
         * 2、读取密钥（公钥）
         * 3、读取所有需要加密的文件路径
         * 4、路径循环，文件加密
         */
        try {
            //1
            generateOrSearchKey();
            //2
            byte[] publicKey = readKeyFromFile(PUBLIC_KEY_PATH);
            //3
            List<String> fileNameList = readEncryptOrDecryptFileName(ENCRYPT_FILENAME_FILE_PATH);
            //4
            for (int i = 0; i < fileNameList.size(); i++) {
                RSAEncryptUtil.getInstance().encryptFileByPublicKey(fileNameList.get(i), publicKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decryptFiles() {
        /**
         * 1、读取密钥（私钥）
         * 2、读取所有需要解密的文件路径
         * 3、路径循环，文件解密
         */
        try {
            byte[] publicKey = readKeyFromFile(PRIVATE_KEY_PATH);
            List<String> fileNameList = readEncryptOrDecryptFileName(DECRYPT_FILENAME_FILE_PATH);
            for (int i = 0; i < fileNameList.size(); i++) {
                RSADecryptUtil.getInstance().decryptFileByPrivateKey(fileNameList.get(i), publicKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateOrSearchKey() throws Exception{
        File file = new File("./key");
        if (!file.exists() && file.mkdir()){
            //生成密钥对并保存在文件public和private文件中
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            byte[] publicKey = keyPair.getPublic().getEncoded();
            byte[] privateKey = keyPair.getPrivate().getEncoded();
            saveKey(publicKey, privateKey);
        }
    }

    private void saveKey(byte[] publicKey, byte[] privateKey) throws Exception{
        FileOutputStream publicKeyFileOutputStream = new FileOutputStream(PUBLIC_KEY_PATH);
        FileOutputStream privateKeyFileOutputStream = new FileOutputStream(PRIVATE_KEY_PATH);
        publicKeyFileOutputStream.write(publicKey);
        privateKeyFileOutputStream.write(privateKey);
        publicKeyFileOutputStream.close();
        privateKeyFileOutputStream.close();
    }

    private byte[] readKeyFromFile(String fileName) throws Exception{
        File file = new File(fileName);
        byte[] result = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] bytes = new byte[1024];
        int length;
        int totalLength = 0;
        while ((length = fileInputStream.read(bytes)) != -1){
            System.arraycopy(bytes, 0, result, totalLength, length);
            totalLength += length;
        }
        return result;
    }

    private void testEncryptDecrypt() throws Exception{
        PublicKey publicKey = RSAEncryptUtil.getInstance().convertByteToKey(
                readKeyFromFile("./key/public")
        );
        PrivateKey privateKey = RSADecryptUtil.getInstance().convertByteToPrivateKey(
                readKeyFromFile("./key/private")
        );
        String name = "this is nancy here";
        byte[] nameBytes = name.getBytes(Charset.defaultCharset());
        System.out.println("原始数据长度是" + nameBytes.length + "字节");
        byte[] encryptName = RSAEncryptUtil.getInstance().encryptByPublicKey(nameBytes, publicKey);
        System.out.println("公钥加密之后数据长度是" + encryptName.length + "字节");
        byte[] decryptName = RSADecryptUtil.getInstance().decryptByPrivateKey(encryptName, privateKey);
        System.out.println("私钥解密之后数据长度是" + decryptName.length + "字节");
        System.out.println("私钥解密之后数据是 = " + new String(decryptName, Charset.defaultCharset()));
    }

    private List<String> readEncryptOrDecryptFileName(String filePath) throws Exception{
        File file = new File(filePath);
        List<String> fileNameList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String fileName;
        while ((fileName = bufferedReader.readLine()) != null)
            fileNameList.add(fileName);
        bufferedReader.close();
        return fileNameList;
    }
}
