package com.example.cuishou.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cuishou.R;

public class ProgressWaitingDialog extends Dialog {
    public ProgressWaitingDialog(Context context,String msg) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        TextView msgView = (TextView) view.findViewById(R.id.msg);
        msgView.setText(msg);
        setContentView(view);
    }
    public ProgressWaitingDialog(Context context) {
        super(context);
    }
}
