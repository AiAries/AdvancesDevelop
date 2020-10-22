

1.  在构建打包时，需要注意切换debug环境，还是生产release环境，因为日志的打印开关是由ENABLE_LOG决定的
 buildTypes {
        release {
            minifyEnabled false
            buildConfigField "boolean", "ENABLE_LOG", "false"
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        debug{
            minifyEnabled false
            buildConfigField "boolean", "ENABLE_LOG", "true"
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

2.  项目URL的配置的切换--这个通过主项目的差异化构建进行配置--已完成
    techown.login.network.downloader.Constant.BASE_URL 服务器url
    在主项目的Application--onCreate方法中初始化
3.  项目代码风格的校验--checkstyle  自定义 --已完成
    a.通过gradle脚本命令的方式进行校验
        配置一个gradle文件，如../gradle/check.gradle  line 2 --> line 8
    b.通过安装工具插件的方式
        一。install :setting -- plugins--browse repositories -- checkstyle idle -- click install
        二。config  :setting -- Other Setting -- CheckStyle -- Configuration File --click right add

    a.b两种方式都需要配置一个checkstyle.xml文件，制定代码的检查规范。
    注意：每次修改checkstyle.xml文件，都需要clean一下工程，Check才能生效。

4.  代码的模板设计 Live Templates
    现有的常用快捷方式
    android.xml
        lh      android:layout_height="$height$"
        lhm     android:layout_height="match_parent"\
        lhw     android:layout_height="wrap_content"
        lw
        lwm
        lhw
    android.log
        logt    private static final String TAG = "$className$";
        logd    android.util.Log.d(TAG, "$METHOD_NAME$: $content$");
        logm    android.util.Log.d(TAG, $content$);//Log method name and its arguments
        logr    android.util.Log.d(TAG, "$METHOD_NAME$() returned: " +  $result$);//Log result of this method
        .....
5.OkHttp --cookieJar 和 authenticator 机制
    Dispatcher 机制  retryOnConnectionFailure 重连接机制  ConnectionPool连接池机制  DEFAULT_PROTOCOLS
    DEFAULT_CONNECTION_SPECS

    handler  looper.prepared()   messageQueen(时序队列)  message
    ActivityThread   启动时候 就创建了 一个 looper
