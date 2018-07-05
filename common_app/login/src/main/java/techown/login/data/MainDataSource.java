package techown.login.data;

import io.reactivex.Observable;
import techown.login.network.bean.main.MainTabVo;

/**
 * Created by A170860 on 2018/6/26.
 */

public interface MainDataSource {
    Observable<MainTabVo> getMainTabData();
}
