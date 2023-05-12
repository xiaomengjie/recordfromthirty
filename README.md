### RSA加密解密练习
	加密之前需要在encrypt_file_name.md文件中添加需要加密的文件路径  
	解密之前也需要在decrypt_file_name.md文件中添加需要解密的文件路径

	1、对于RSAMain.java的编译需指定class path
    	javac -cp ./ RSAMain.java  
	2、运行时，传入参数 decrypt 代表解密文件，否则是加密文件  
    	java RSAMain or java RSAMain decrypt

### Mac下HomeBrew的安装
	https://gitee.com/cunkai/HomebrewCN?_from=gitee_search

### Mac下git的配置文件
	[safe]
		directory = /opt/homebrew/Library/Taps/homebrew/homebrew-core
		directory = /opt/homebrew/Library/Taps/homebrew/homebrew-cask
	[user]
		name = xiaomengjie
		email = xiaomengjie2020@yeah.net
	[http "https://github.com"]
		proxy = socks5://127.0.0.1:7890

### Mac下SSH的配置文件
	Host github.com
	Hostname ssh.github.com
	Port 443
	User git
	ProxyCommand nc -v -x 127.0.0.1:7890 %h %p