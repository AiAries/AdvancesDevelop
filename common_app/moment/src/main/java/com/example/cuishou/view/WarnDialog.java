package com.example.cuishou.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuishou.R;


public class WarnDialog extends Dialog {


    public WarnDialog(@NonNull Context context, String content, int theme, final Callback callback,String sureString,String cancelString) {
        super(context, theme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_warn, null);
        TextView tipContent = (TextView) view.findViewById(R.id.tip_content);
        tipContent.setText(content);
        Button sureView = (Button) view.findViewById(R.id.sure);
        sureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback!=null) {
                    callback.onSure();
                }
            }
        });
        Button noView = (Button) view.findViewById(R.id.no);
        noView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback!=null) {
                    callback.onCancel();
                }
            }
        });
        if (!TextUtils.isEmpty(sureString)) {
            sureView.setText(sureString);
        }
        if (!TextUtils.isEmpty(cancelString)) {
            noView.setText(cancelString);
        }
        setContentView(view);
    }

    public WarnDialog(@NonNull Context context,String content) {
        this(context, content,R.style.tipDialog,null);
    }
    public WarnDialog(@NonNull Context context,String content,Callback callback) {
        this(context, content,R.style.tipDialog,callback);
    }
    public WarnDialog(@NonNull Context context, String content, int theme, final Callback callback) {
        this(context,content,theme,callback,null,null);
    }
    public WarnDialog(@NonNull Context context,String content,Callback callback,String sureString,String cancelString) {
        this(context, content,R.style.tipDialog,callback, sureString, cancelString);
    }

    public interface Callback {
        void onSure();
        void onCancel();
    }

}
