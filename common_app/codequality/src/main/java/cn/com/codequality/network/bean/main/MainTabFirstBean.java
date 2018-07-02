package cn.com.codequality.network.bean.main;

import java.util.List;

/**
 * Copyright (C)2016 JYQF Inc.
 * Description:
 *
 * @author A168730
 *         Create time 2017/11/16 16:41
 */

public class MainTabFirstBean {
    private String desc;
    private String mIndex;
    private String mLevel;
    private String mType;
    private String dataIndex;
    private String mId;
    private String name;
    private List<MainTabSecondBean>modules;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getmIndex() {
        return mIndex;
    }

    public void setmIndex(String mIndex) {
        this.mIndex = mIndex;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MainTabSecondBean> getModules() {
        return modules;
    }

    public void setModules(List<MainTabSecondBean> modules) {
        this.modules = modules;
    }
}
