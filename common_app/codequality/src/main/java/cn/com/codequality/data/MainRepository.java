package cn.com.codequality.data;

import android.support.annotation.NonNull;

import cn.com.codequality.network.bean.main.MainTabVo;
import io.reactivex.Flowable;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 主要职责：首页数据的仓库（CRUD等）<br>
 * 主要处理 内存缓存，本地缓存和从服务下载数据之间的优先级关系<br>
 * 真正的数据加载是由下面两个类
 *  {@link cn.com.codequality.data.remote.MainRemoteDataSource} and
 * {@link cn.com.codequality.data.local.MainLocalDataSource}
 * 来完成<br>
 *  巧妙设计就在于当前类和上面两个类都同时实现了接口 {@link MainDataSource}
 */

public class MainRepository implements MainDataSource{
    // Prevent direct instantiation.
    private static MainRepository INSTANCE;
    private final MainDataSource mMainRemoteDataSource;
    private final MainDataSource mMainLocalDataSource;

    private MainRepository(@NonNull MainDataSource mainRemoteDataSource,
                            @NonNull MainDataSource mainLocalDataSource) {
        mMainRemoteDataSource = checkNotNull(mainRemoteDataSource);
        mMainLocalDataSource = checkNotNull(mainLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param mainRemoteDataSource the backend data source
     * @param mainLocalDataSource  the device storage data source
     * @return the {@link MainDataSource} instance
     */
    public static MainRepository getInstance(@NonNull MainDataSource mainRemoteDataSource,
                                              @NonNull MainDataSource mainLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MainRepository(mainRemoteDataSource, mainLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Flowable<MainTabVo> getMainTabData() {
        return mMainRemoteDataSource.getMainTabData();
    }
}
