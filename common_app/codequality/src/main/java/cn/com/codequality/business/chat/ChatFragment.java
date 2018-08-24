package cn.com.codequality.business.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bankcomm.ui.base.BaseFragment;
import com.bankcomm.ui.view.dialogs.shade.IShade;
import com.bankcomm.ui.view.dialogs.shade.ProgressShadeImp;

import java.util.List;

import cn.com.codequality.R;
import cn.com.codequality.business.games.GameActivity;
import cn.com.codequality.data.chat.bean.Chat;

import static com.bankcomm.framework.utils.Utils.checkNotNull;

/**
 * Created by A170860 on 2018/6/22.
 */

public class ChatFragment extends BaseFragment implements ChatContract.View {

    private ChatContract.Presenter mPresenter;
    private TextView testJsonView;
    private ChatAdapter mChatAdapter;
    private View mNoDataView;
    private RecyclerView mChatList;
    private IShade mShade;

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        checkNotNull(presenter, "mainPresenter cannot be null");
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mShade = new ProgressShadeImp(getActivity());
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mNoDataView = view.findViewById(R.id.no_data_view);
        mChatList = view.findViewById(R.id.chat_list);
        testJsonView = view.findViewById(R.id.test_json);
        testJsonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mShade.showDialog();
                startActivity(new Intent(getContext(), GameActivity.class));
            }
        });
        mChatList.setLayoutManager(new LinearLayoutManager(getContext()));
        mChatList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        mChatAdapter = new ChatAdapter(mChatList, R.layout.recycler_chat);
        mChatList.setAdapter(mChatAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showChatList(List<Chat> data) {
        mNoDataView.setVisibility(View.GONE);
        mChatList.setVisibility(View.VISIBLE);
        mChatAdapter.setData(data);
    }

    @Override
    public void showNoData() {
        mNoDataView.setVisibility(View.VISIBLE);
        mChatList.setVisibility(View.GONE);
    }

    @Override
    public void showTestJson(String text) {
        testJsonView.setText(text);
    }

    @Override
    public void showWaitDialog() {
        mShade.showDialog();
    }

    @Override
    public void dismissDialog() {
        mShade.hideDialog();
    }
}
