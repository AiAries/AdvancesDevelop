package cn.com.codequality.extension.extension;

/**
 * Created by A170860 on 2018/6/7.
 */
public abstract class Plant {
    private String roots;
    private String trunk;

    private void validate() {
        if (roots == null) throw new IllegalArgumentException("No roots!");
        if (trunk == null) throw new IllegalArgumentException("No trunk!");
        validateEx();
    }

    protected void validateEx() { }

    public abstract void grow();
}
