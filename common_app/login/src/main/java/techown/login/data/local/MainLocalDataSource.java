package techown.login.data.local;

import io.reactivex.Flowable;
import techown.login.data.MainDataSource;
import techown.login.network.bean.main.MainTabVo;

/**
 * Created by A170860 on 2018/6/26.
 */

public class MainLocalDataSource implements MainDataSource {
    @Override
    public Flowable<MainTabVo> getMainTabData() {
        return null;
    }
}
