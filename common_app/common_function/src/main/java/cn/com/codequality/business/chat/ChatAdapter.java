package cn.com.codequality.business.chat;

import android.support.v7.widget.RecyclerView;

import com.bankcomm.ui.adapter.BGARecyclerViewDefaultAdapter;
import com.bankcomm.ui.adapter.BGAViewHolderHelper;

import cn.com.codequality.R;
import cn.com.codequality.data.chat.bean.Chat;

public class ChatAdapter extends BGARecyclerViewDefaultAdapter<Chat> {

    public ChatAdapter(RecyclerView recyclerView, int defaultItemLayoutId) {
        super(recyclerView, defaultItemLayoutId);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Chat model) {
        helper.getTextView(R.id.message).setText(model.getMessage());
    }


}
