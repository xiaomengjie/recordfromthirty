import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@ExperimentalEncodingApi
fun main() {
    val keyPair = generateKey()
    val data = "abcdefghijklmnopqrstuvwxyz"

    //公钥加密私钥解密
    val encryptedData = encryptDataUsingPublic(data.toByteArray(), keyPair.public.encoded)
    println(Base64.encode(encryptedData))
    val decryptedData = decryptDataUsingPrivate(encryptedData, keyPair.private.encoded)
    println(decryptedData.decodeToString())

    //私钥加密公钥解密
    val encrypt = encryptDataUsingPrivate(data.toByteArray(), keyPair.private.encoded)
    println(Base64.encode(encrypt))
    val decrypt = decryptDataUsingPublic(encrypt, keyPair.public.encoded)
    println(decrypt.decodeToString())
}

private fun generateKey(): KeyPair{
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(1024)
    return keyPairGenerator.generateKeyPair()
}

private fun encryptDataUsingPublic(data: ByteArray, publicKey: ByteArray): ByteArray{
    val x509EncodedKeySpec = X509EncodedKeySpec(publicKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePublic(x509EncodedKeySpec)
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    return cipher.doFinal(data)
}

private fun decryptDataUsingPrivate(data: ByteArray, privateKey: ByteArray): ByteArray{
    val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(privateKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePrivate(pkcS8EncodedKeySpec)
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, key)
    return cipher.doFinal(data)
}

private fun encryptDataUsingPrivate(data: ByteArray, privateKey: ByteArray): ByteArray{
    val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(privateKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePrivate(pkcS8EncodedKeySpec)
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    return cipher.doFinal(data)
}

private fun decryptDataUsingPublic(data: ByteArray, publicKey: ByteArray): ByteArray{
    val x509EncodedKeySpec = X509EncodedKeySpec(publicKey)
    val keyFactory = KeyFactory.getInstance("RSA")
    val key = keyFactory.generatePublic(x509EncodedKeySpec)
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, key)
    return cipher.doFinal(data)
}