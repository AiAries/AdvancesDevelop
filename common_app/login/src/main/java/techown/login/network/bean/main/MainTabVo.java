package techown.login.network.bean.main;

import java.util.List;

/**
 * Copyright (C)2016 JYQF Inc.
 * Description:
 *
 * @author A168730
 *         Create time 2017/11/16 16:44
 */

public class MainTabVo extends BaseVo {
    private List<MainTabFirstBean>moduleData;
    private String signature;
    private String useLocalCache;

    public List<MainTabFirstBean> getModuleData() {
        return moduleData;
    }

    public void setModuleData(List<MainTabFirstBean> moduleData) {
        this.moduleData = moduleData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUseLocalCache() {
        return useLocalCache;
    }

    public void setUseLocalCache(String useLocalCache) {
        this.useLocalCache = useLocalCache;
    }
}
