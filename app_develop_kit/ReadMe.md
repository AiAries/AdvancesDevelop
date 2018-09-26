MVP 模板。MVP开发过程中一次性总是要生成几个文件，我们可以在模板中配置生成。
操作步骤如下：
一、自定义Gradle模板：
有时候新建项目的时候，Gradle文件里对应的sdk版本总是不正确，这个时候我们就要自定义模板了。
Android Studio安装目录\plugins\android\lib\templates\gradle-projects\NewAndroidModule\recipe.xml.ftl
查找com.android.support:appcompat，即可修改对应的support:appcompat版本

早期的版本在
Android Studio安装目录\plugins\android\lib\templates\gradle-projects\NewAndroidModule\root\build.gradle.ftl
可以修改compileSdkVersion和targetSdkVersion等信息，但是仔细看新版本的头有这么一句
<#import “./shared_macros.ftl” as shared>
所以，应该修改
Android Studio安装目录\plugins\android\lib\templates\gradle-projects\NewAndroidModule\root\shared_macros.ftl

二、MVP模板。MVP开发过程中一次性总是要新建很多文件，我们同样可以在模板里自己定义。
AndroidStudio安装路径\plugins\android\lib\templates\activities
复制EmptyActivity到同级目录重命名为MVPActivity，相关改动：
template.xml只要更改根节点的name为name=”MVPActivity”即可。
替换MVPActivity当前目录下的recipe.xml.ftl文件为app_develop_kit/recipe.xml.ftl

再进入AndroidStudio安装路\plugins\android\lib\templates\activities\MVPActivity\root\src\app_package，复制app_develop_kit/以下文件进去：
MainActivity.java.ftl
MainContract.java.ftl
MainPresenter.java.ftl

重启AndroidStudio，File –> New –> Activity –> MVP Activity即可。

---------------------

本文来自 ithouse 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/ithouse/article/details/80416037?utm_source=copy