package cn.com.codequality.business.chat.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankcomm.ui.base.BaseFragment;

import cn.com.codequality.R;

public class ChatDetailFragment extends BaseFragment implements ChatDetailContract.View {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_detail, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void setPresenter(ChatDetailContract.Presenter presenter) {

    }
}
