package cn.com.codequality.data.remote;

import com.bankcomm.framework.log.AresLog;
import com.bankcomm.framework.network.RetrofitApi;
import com.bankcomm.framework.utils.android.AndroidUtil;
import com.bankcomm.ui.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import cn.com.codequality.data.MainDataSource;
import cn.com.codequality.network.api.MainApi;
import cn.com.codequality.network.bean.main.MainTabVo;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by A170860 on 2018/6/26.
 */

public class MainRemoteDataSource implements MainDataSource {
    @Override
    public Flowable<MainTabVo> getMainTabData() {
        Map<String, Object> params = getParams();
        return RetrofitApi.getRetrofit().create(MainApi.class).getMainData(params);
    }

    /**s
     * get tab'info
     */
    public void getTabInfo() {
        Map<String, Object> params = getParams();
        RetrofitApi.getRetrofit().create(MainApi.class).getMainData(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MainTabVo>() {
                               @Override
                               public void accept(MainTabVo result) throws Exception {
                                   AresLog.d(TAG, "accept: result" + result.getSTATUS());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   AresLog.d(TAG, "accept: throwable" + throwable);
                               }
                           }
                );
       /* MdbApi.getMenuModule(params, new AsyncHttpResponseCallback<MainTabVo>() {
            @Override
            public void onSuccess(MainTabVo result) {
                TLog.d(TAG, "success and status is" + result.getSTATUS());
                mHaveTabInfo = true;
                if (result == null) {
                    return;
                }
                if (Constans.NET_REQUEST_SUCCESS.equals(result.getSTATUS())) {
                    if (!TextUtils.isEmpty(result.getSignature())) {
                        SharedPreferenceUtil.setInfoToShared(Constans.CACHE_TAB_SIGNATURE, result.getSignature());
                    }
                    TLog.d(TAG, "useLocalCache is" + result.getUseLocalCache());
                    if (Constans.STATUS_ONE.equals(result.getUseLocalCache())) {
                        //use local cache, but have no cache
                        MainTabVo mainTabVo = getLocalCashMainTabVo(false);
                        if (mainTabVo == null && useLocalSignature) {
                            getTabInfo(false);
                        }
                    } else {
                        //use data from network
                        mMainTabVo = result;
                        info = new JMGLBaseActivity(getMainTabSecondBeanList(), MainActivity.this, new JMGLBaseActivity.AllTabPicLoad() {
                            @Override
                            public void allTabPicLoad() {
                                updateTabView();
                            }
                        });
                        SharedPreferenceUtil.setInfoToShared(Constans.MAIN_TAB_VO, new Gson().toJson(mMainTabVo));
                    }

                }
                refreshFragmentAsTabInfoUpdate();
            }

            @Override
            public void onFailure(HttpException error) {
                TLog.d(TAG, "failure and error code is" + error.getCode());
                mHaveTabInfo = true;
                if (error.getCode() == Constans.NET_REQUEST_ERROR) {
                    sessionTimeout(error.getMessage());
                }
                refreshFragmentAsTabInfoUpdate();
            }
        });
    */
    }

    /**
     * get params
     *
     * @return
     */
    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
//        if (AccountHelper.getInstance().getUserInfo() != null) {
//            params.put("UserId", AccountHelper.getInstance().getUserId());
//        }
        //获取安卓系统
        params.put("os", "1000");
        //获取客户群体
        String groupId = "";//实名类型
//        UserLoginInfoVo userLoginInfoVo = AccountHelper.getInstance().getUserInfo();
//        if (userLoginInfoVo == null) {
//            userLoginInfoVo = AccountHelper.getInstance().getLastLoginInfo();
//        }
//        if (userLoginInfoVo != null) {
//            if (StringUtil.isNotEmpty(userLoginInfoVo.getIsCertVarify()) && userLoginInfoVo.getIsCertVarify().equals("true")) {//实名
//                if (StringUtil.isNotEmpty(userLoginInfoVo.getVerifyLevel())) {
//                    if (userLoginInfoVo.getVerifyLevel().equals("0")) {//非实名
//                        groupId = "1";
//                    } else if (userLoginInfoVo.getVerifyLevel().equals("1")) {//交行卡
//                        groupId = "3";
//                    } else if (userLoginInfoVo.getVerifyLevel().equals("2")) {//他行卡
//                        groupId = "2";
//                    }
//                }
//            } else {
//                groupId = "1";
//            }
//        }
        params.put("groupId", groupId);//默认：空字符串”” 1：非实名 2：他行卡 3：本行卡
        //获取城市id
//        if (StringUtil.isNotEmpty(SharedPreferenceUtil.getInfoFromShared("noError", ""))) {
//
//            String saveName = SharedPreferenceUtil.getInfoFromShared("noError", null);
//            listAll l = LBSInfoDBManager.getInstance(MyApplication.getInstance()).getCityByNameFromAPP(saveName);
//            if (null != l) {
//                params.put("cityId", l.getID());//城市ID
//            }
//        } else {
        params.put("cityId", "");//城市ID
//        }
        params.put("tabId", "");
        params.put("version", AndroidUtil.getAppVersion(BaseApplication.mGlobalApp));
//        if (useLocalSignature) {
//            if (StringUtil.isNotEmpty(SharedPreferenceUtil.getInfoFromShared(Constans.CACHE_TAB_SIGNATURE, null))) {
//                params.put("signature", SharedPreferenceUtil.getInfoFromShared(Constans.CACHE_TAB_SIGNATURE, null));
//            } else {
//                params.put("signature", "");
//            }
//        } else {
        params.put("signature", "");
//        }
        return params;
    }
}
