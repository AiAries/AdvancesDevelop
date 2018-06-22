package techown.login.network.bean.main;

import java.io.Serializable;

/**
 * ================================================
 * Author     : LiuHaiLin
 * Version    : 1.0
 * Create Time: 2017/10/27
 * Description:
 * ================================================
 */

public class BaseVo implements Serializable {

    private String STATUS;

    private String MSG;

    public String getSTATUS() {
        return STATUS;
    }

    public String getMSG() {
        return MSG;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public boolean isSuccess(){
        if("1".equals(STATUS)){
            return true;
        }else {
            return false;
        }
    }
}
