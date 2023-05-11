import src.rsa.code.RSAAlgorithm;

/*
* 使用javac编译时，需要添加-cp定义查找路径
* javac -p ./ RSAMain.java
*/
class RSAMain{
	public static void main(String[] args) {
        RSAAlgorithm rsaAlgorithm = new RSAAlgorithm();
        if (args.length > 0 && args[0].equals("decrypt")){
            rsaAlgorithm.decryptFiles();
        }else {
            rsaAlgorithm.encryptFiles();
        }
	}
}
