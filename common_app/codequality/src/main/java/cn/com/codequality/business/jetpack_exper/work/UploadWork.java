package cn.com.codequality.business.jetpack_exper.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWork extends Worker {
    public UploadWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //todo upload image ...
        try {
            System.out.println("开始上传图片");
            Thread.sleep(50000);
            System.out.println("上传图片完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }
}
