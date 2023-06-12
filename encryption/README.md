### RSA非对称加密

#### 1、通过keyPairGenerator生成RSA密钥对

    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    //设置密钥长度
    keyPairGenerator.initialize(1024)
    //keyPair.public - 公钥
    //keyPair.private - 私钥
    return keyPairGenerator.generateKeyPair()

#### 2、加密与解密
    //加密
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    return cipher.doFinal(data)

    //解密
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, key)
    return cipher.doFinal(data)

### 字节数组密钥转换为公钥私钥

#### 1、转换为公钥PublicKey
    val x509EncodedKeySpec = X509EncodedKeySpec(publicKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePublic(x509EncodedKeySpec)

#### 2、转换为私钥PrivateKey
    val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(privateKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePrivate(pkcS8EncodedKeySpec)


    