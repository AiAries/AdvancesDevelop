package techown.login.network.api;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import techown.login.network.bean.User;

/**
 * Created by A170860 on 2018/6/22.
 */

public interface MainApi {

    @GET("common/visualmenumodule.do")
    Observable<BaseEntity<User>> getMainData();
}
