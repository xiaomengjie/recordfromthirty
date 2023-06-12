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