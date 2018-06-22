package cn.com.codequality.extension;

/**
 * Created by A170860 on 2018/6/7.
 */

public abstract class Plant {
    private String mRoot;
    private String trunk;

    protected void validate() {
        if (mRoot == null) throw new IllegalArgumentException("No mRoot!");
        if (trunk == null) throw new IllegalArgumentException("No trunk!");
    }

    public abstract void grow();
}


