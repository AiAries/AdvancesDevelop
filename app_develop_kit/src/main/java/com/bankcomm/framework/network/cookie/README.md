PersistentCookieJar for OkHttp 3
===============================
cookie 小饼干
A persistent CookieJar implementation for OkHttp 3 based on SharedPreferences.

If you're looking for a OkHttp 2/HTTPUrlConnection persistent CookieStore it can be found at [this Gist](https://gist.github.com/franmontiel/ed12a2295566b7076161).

Download
--------
Step 1. Add the JitPack repository in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Step 2. Add the dependency
```groovy
dependencies {
    compile 'com.github.franmontiel:PersistentCookieJar:v1.0.1'
}
```
Usage
-----
Create an instance of `PersistentCookieJar` passing a `CookieCache` and a `CookiePersistor`:

```java
ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
```

Then just add the CookieJar when building your OkHttp client:

```java
OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
```

Features
--------
This is a really simple library but here are some of the things that it provides:
* Possibility to clear the jar: `PersistentCookieJar` implements `ClearableCookieJar` interface that declares a `clear()` method for removing all cookies from the jar.

* Possibility to clear session cookies: `PersistentCookieJar` implements `ClearableCookieJar` interface that declares a `clearSession()` method for removing session cookies from the jar.

* Decoupled and extensible: `CookieCache` and `CookiePersistor` are interfaces so you can provide your own implementation for each one.
    * `CookieCache` represents an in-memory cookie storage. `SetCookieCache` is the provided implementation that uses a Set to store the Cookies.
    * `CookiePersistor` represents a persistent storage. `SharedPrefsCookiePersistor` is the provided implementation that uses a SharedPreferences to persist the Cookies.

* Thread-safe: `PersistentCookieJar` public methods are synchronized so there is no need to worry about threading if you need to implement a `CookieCache` or a `CookiePersistor`.

ProGuard
-------
The following configuration is only needed for version 0.9.2 and below:
```
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**

-keepclassmembers class * implements java.io.Serializable {  
    static final long serialVersionUID;  
    private static final java.io.ObjectStreamField[] serialPersistentFields;  
    !static !transient <fields>;  
    private void writeObject(java.io.ObjectOutputStream);  
    private void readObject(java.io.ObjectInputStream);  
    java.lang.Object writeReplace();  
    java.lang.Object readResolve();  
}
```
====================
服务器使用shiro做登录校验及权限验证的时候

app端访问时就要带上cookie，不然请求会提示未登录
