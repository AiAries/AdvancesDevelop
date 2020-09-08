package cn.com.codequality.business.enter;

import android.support.v7.widget.RecyclerView;

import com.bankcomm.ui.adapter.BGARecyclerViewDefaultAdapter;
import com.bankcomm.ui.adapter.BGAViewHolderHelper;

import cn.com.codequality.R;

public class EnterListAdapter extends BGARecyclerViewDefaultAdapter<String> {
    public EnterListAdapter(RecyclerView recyclerView, int defaultItemLayoutId) {
        super(recyclerView, defaultItemLayoutId);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
        helper.getTextView(R.id.message).setText(model);
    }
}
