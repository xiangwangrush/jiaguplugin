# jiaguplugin
**前提：**

因为是基于360加固助手的加固功能,插件只是执行命令调用加固的jar文件提供的API，

所以首先还是要去下载一下360加固宝的应用，不过只需要对应的jar就可以了。

360加固助手的下载地址：[[360加固保-应用安全服务平台-app加固|漏洞扫描](https://jiagu.360.cn/#/global/index)]()



**插件使用步骤：**

1.**在工程的build.gradle文件中** 添加 加固 gradle插件依赖 "com.hippius:jiagu:1.0.0"

```groovy
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.hippius:jiagu:1.0.0"
    }
}
```

因为插件发布在jcenter上的，若使用建议下载代码到本地,添加到自己的buildSrc下自行使用。



2.配置插件预制信息

因为360加固插件在使用的时候要登录 需要配置对于登录的用户名和密码,这边是使用执行对于360提供的加固助手中的jar 执行命令进行打包,还需要配置本地环境下 360加固宝对于加固的jar路径。**在app 模块下的 build.gradle文件中进行配置(最终打包出apk的模块)**

```groovy
apply plugin: 'com.hippius.jiagu'

jiagu{
    userName '360加固助手的账号' 
    password '360加固助手的代码'
    jiaguToolPath '360加固助手中的jiagu.jar的绝对路径 例如 E:\\jiagu\\360\\jiagu\\jiagu.jar'
}
```



3.配置签名信息,因为加固后还会进行重签名

```groovy
    signingConfigs {
        debug {
            storeFile file("")
            keyAlias ""
            keyPassword ""
            storePassword ""
            v1SigningEnabled true
        }
        release {
            storeFile file("")
            keyAlias ""
            keyPassword ""
            storePassword ""
            v1SigningEnabled true
        }
    }
```



4.经过三面三个步骤之后,并且同步gradle之后查看是否有jiagu的task 如下图所示

![图片3](/../图片3.png)



5.使用

使用方法1：双击jiagu下面的两个Task可以执行对于的gradle task

使用方法2：gradlew jiaguDebug或者jiaguRelease

 第二种方法更适合构建自动打包的时候，打完apk后执行命令的流程

这边默认是用的build目录下寻找对应的apk文件进行加密,如果未找到则无法进行加固会有提示