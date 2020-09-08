package cn.com.codequality.extension;

import java.util.List;

/**
 * Created by  on 2018/6/7.
 */

public class Tree extends Plant {
    private List leaves;

    @Override
    protected void validate() {
        super.validate();
        if (leaves == null) throw new IllegalArgumentException("No leaves!");
    }

    public void grow() {
        validate();
    }
}