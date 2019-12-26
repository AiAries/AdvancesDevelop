package cn.com.codequality.extension.extension;

import java.util.List;


/**
 * Created by  on 2018/6/7.
 */

public class Tree extends Plant {
    private List leaves;

    @Override
    public void grow() {

    }

   /* @Overrides
    protected void validate() {
        super.validate();
        if (leaves == null) throw new IllegalArgumentException("No leaves!");
    }

    public void grow() {
        validate();
    }*/
}