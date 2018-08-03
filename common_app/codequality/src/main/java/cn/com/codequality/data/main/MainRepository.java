package cn.com.codequality.data.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.com.codequality.data.main.bean.MainTabVo;
import io.reactivex.Flowable;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 主要职责：首页数据的仓库（CRUD等）<br>
 * 主要处理 内存缓存，本地缓存和从服务下载数据之间的优先级关系<br>
 * 真正的数据加载是由下面两个类
 *  {@link cn.com.codequality.data.main.remote.MainRemoteDataSource} and
 * {@link cn.com.codequality.data.main.local.MainLocalDataSource}
 * 来完成<br>
 *  巧妙设计就在于当前类和上面两个类都同时实现了接口 {@link MainDataSource}
 */

public class MainRepository implements MainDataSource{
    // Prevent direct instantiation.
    private static MainRepository INSTANCE;
    private final MainDataSource mMainRemoteDataSource;
    private final MainDataSource mMainLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    Map<String, MainTabVo> mCachedMainTabVo;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

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
        //Respond immediately with cache if available and not dirty
        if (mCachedMainTabVo!=null&&!mCacheIsDirty) {
            return Flowable.fromIterable(mCachedMainTabVo.values());
        }else if (mCachedMainTabVo == null) {
            mCachedMainTabVo = new LinkedHashMap<>();
        }
        return mMainRemoteDataSource.getMainTabData();
    }
}
