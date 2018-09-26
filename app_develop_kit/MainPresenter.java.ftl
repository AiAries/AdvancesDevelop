package ${packageName};

import android.content.Context;

public class ${activityClass}Presenter implements ${activityClass}Contract.Presenter {
    private Context mContext;
    private ${activityClass}Contract.View mView;

    public ${activityClass}Presenter(Context context, ${activityClass}Contract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}