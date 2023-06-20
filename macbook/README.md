### Mac下HomeBrew的安装
	https://gitee.com/cunkai/HomebrewCN?_from=gitee_search

### Mac下git的配置文件(安装了HomeBrew)-路径：～/.gitconfig
	[safe]
		directory = /opt/homebrew/Library/Taps/homebrew/homebrew-core
		directory = /opt/homebrew/Library/Taps/homebrew/homebrew-cask
	[user]
		name = xiaomengjie
		email = xiaomengjie2020@yeah.net
	[http "https://github.com"]
		proxy = socks5://127.0.0.1:7890
	[https "https://github.com"]
		proxy = socks5://127.0.0.1:7890

### Mac下SSH的配置文件-路径：～/.ssh/config
	Host github.com
	Hostname ssh.github.com
	Port 443
	User git
	ProxyCommand nc -v -x 127.0.0.1:7890 %h %p

### Mac下Sublime中LiveReload自启动配置
	"enabled_plugins": [
        	"SimpleReloadPlugin",
        	"SimpleRefresh"
        ]

### Mac下zsh终端环境变量的配置
	1、在～/.bash_profile文件中配置路径
	2、在～/.zshrc文件中添加 source .bash_profile

### Mac更新.gitignore文件
	1、原因：.gitignore只能忽略那些原来没有被track的文件，如果某些文件已经被纳入了版本管理中，则修改.gitignore是无效的。
	2、生效方法：
		git rm -r --cached .（删除本地缓存）

### Mac命令行删除文件
	1、rm filename
	2、rm -r dirname

### Mac终端配置代理
#### 方法一：（推荐使用）
	为什么说这个方法推荐使用呢？因为他只作用于当前终端中，不会影响环境，而且命令比较简单
	在终端中直接运行：
	export http_proxy=http://proxyAddress:port
	如果你是SSR,并且走的http的代理ip是127.0.0.1端口是12333，想执行wget或者curl来下载国外的东西，
	如果你实际代理的IP是10.12.1.16端口是3128，想执行wget或者curl来下载东西，这里我用的就是这个代理IP
	可以使用如下命令：
	export http_proxy=http://10.12.1.16:3128
	如果是https那么就经过如下命令：
	export https_proxy=http://10.12.1.16:3128
	还有一种方法可以直接设置代理的账号和密码：
	export ALL_PROXY=http://F1232170:44DRunBA@10.12.1.16:3128

#### 方法二：
	这个办法的好处是把代理服务器永久保存了，下次就可以直接用了
	把代理服务器地址写入shell配置文件.bashrc或者.zshrc 直接在.bashrc或者.zshrc添加下面内容
	export http_proxy="http://localhost:port"
	export https_proxy="http://localhost:port"

	或者走socket5协议（ss,ssr）的话，代理端口是1080
	export http_proxy="socks5://10.12.1.16:3128"
	export https_proxy="socks5://10.12.1.16:3128"

	或者干脆直接设置ALL_PROXY
	export ALL_PROXY=socks5://10.12.1.16:3128

	最后在执行如下命令应用设置
	source ~/.bashrc

	或者通过设置alias简写来简化操作，每次要用的时候输入setproxy，不用了就unsetproxy。
	添加环境变量
	打开终端，执行
	vi ~/.bash_profile
	添加
	#proxy
	alias proxy='export http_proxy=http://10.12.1.16:3128;export https_proxy=http://10.12.1.16:3128;'
	alias unproxy='unset all_proxy'
	保存
	再执行以下命令，使配置生效
	source ~/.bash_profile
	source ~/.bash_profile只生效一次的解决方案
	在~/.zshrc文件最后，增加一行：
	source ~/.bash_profile

	三、验证
	#开启代理
	proxy
	#关闭代理
	unproxy

#### 方法三:
	改相应工具的配置，比如apt的配置

	sudo vim /etc/apt/apt.conf
	在文件末尾加入下面这行

	Acquire::http::Proxy "http://proxyAddress:port"
	重点来了！！如果说经常使用git对于其他方面都不是经常使用，可以直接配置git的命令。
	使用ss/ssr来加快git的速度
	直接输入这个命令就好了

	git config --global http.proxy 'socks5://10.12.1.16:3128' 
	git config --global https.proxy '10.12.1.16:3128'

