# Traveller_Server

Web Service and SOA课程项目

## 项目介绍

### 简介

“Traveller” app is based on B/S structure. It is an iOS app with a Java back-end. Its main objective is to improve people’s living standards by providing travel information for customers. The main functionality of traveller is to help users manage their travel plan, search for information of interesting scenery spots, and share wonderful places of interest with other users.

### 项目架构图

![图](http://cl.ly/3g1p2x0c2Z2X/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-06-27%20%E4%B8%8B%E5%8D%889.04.10.png)

## 配置方法

1. 安装gradle

	1. 用Homebrew安装
		
		```bash
		$ brew install gradle
		```
	2. 去官网下载gradle安装包，并配置~/.bash_profile
		1. 下载
		2. 将下载包解压放入指定目录
		3. 配置~/.bash_profile
			1. 打开命令行
		
				```
				$ open ~/.bash_profile
				```
			2. 在.bash_profile末尾添加：
			
				```
				# gradle
				GRADLE_HOME=/usr/local/bin/gradle-2.14;
				export GRADLE_HOME
				export PATH=$PATH:$GRADLE_HOME/bin
				```
				
			3. 在命令行中应用配置
			
				```
				$ source ~/.bash_profile
				```
2. 在Intellij idea中导入项目
3. 在Intellij idea中设置默认gradle路径
	
	![设置gradle路径](http://cl.ly/1k3z2o2g2y1v/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-06-22%20%E4%B8%8B%E5%8D%8811.21.15.png)
4. 运行gradle
	
	<img src="http://cl.ly/3d2J0l2x3K2N/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-06-22%20%E4%B8%8B%E5%8D%8811.21.33.png" width="200"></img>


## 链接

1. 演示视频：

	[http://v.qq.com/page/u/u/h/u0308of23uh.html](http://v.qq.com/page/u/u/h/u0308of23uh.html)
	
2. 前端代码：

	[https://github.com/MandyXue/Traveller](https://github.com/MandyXue/Traveller) 
	
3. 接口文档：（前端代码库Wiki中）
	[https://github.com/MandyXue/Traveller/wiki/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3](https://github.com/MandyXue/Traveller/wiki/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3)

4. 数据库设计文档：（前端代码库Wiki中）
	[https://github.com/MandyXue/Traveller/wiki/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1](https://github.com/MandyXue/Traveller/wiki/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1)
	
	
====
Copyright &copy; Traveller, Tongji SSE
