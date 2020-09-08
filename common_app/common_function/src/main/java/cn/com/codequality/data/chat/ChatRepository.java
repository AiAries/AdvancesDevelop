package cn.com.codequality.data.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.bankcomm.framework.network.bean.base.BaseEntity;

import java.util.List;
import java.util.Map;

import cn.com.codequality.data.chat.bean.Chat;
import io.reactivex.Flowable;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ChatRepository  implements ChatDataSource{
    // Prevent direct instantiation.
    private static ChatRepository INSTANCE;
    private final ChatDataSource mChatRemoteDataSource;
    private final ChatDataSource mChatLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    Map<String, BaseEntity<List<Chat>>> mCachedChatTabVo;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

    private ChatRepository(@NonNull ChatDataSource chatRemoteDataSource,
                           @NonNull ChatDataSource chatLocalDataSource) {
        mChatRemoteDataSource = checkNotNull(chatRemoteDataSource);
        mChatLocalDataSource = checkNotNull(chatLocalDataSource);
    }
    
    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param chatRemoteDataSource the backend data source
     * @param chatLocalDataSource  the device storage data source
     * @return the {@link ChatDataSource} instance
     */
    public static ChatRepository getInstance(@NonNull ChatDataSource chatRemoteDataSource,
                                             @NonNull ChatDataSource chatLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ChatRepository(chatRemoteDataSource, chatLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Flowable<BaseEntity<Chat>> getChatData() {
        mChatRemoteDataSource.getChatData();
        return   mChatLocalDataSource.getChatData();
    }

    @Override
    public Flowable<String> getTestJson() {
        return mChatRemoteDataSource.getTestJson();
    }
}
