package cn.com.codequality.business.databinding;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TagView extends View {

    private Paint p;
//    private String text="×";
    private String text="√";
//    private String text="未审核";
    private Rect tagBounds;

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        p = new Paint();
        // 水印颜色
        // 水印字体大小
        p.setTextSize(80);
        //抗锯齿
        p.setAntiAlias(true);
//        //绘制图像
//        canvas.drawBitmap(bitmap, 0, 0, p);
        //透明度
        p.setAlpha(50);
        //绘制文字
        tagBounds = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        p.setColor(Color.BLUE);
        if (text!=null) {
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.FILL);
            p.getTextBounds(text, 0, text.length(), tagBounds);
            float textWidths = tagBounds.width();

            float f = 0.5f;//文字和圆的间隙值
            float r = textWidths*(1+f)/2;
            canvas.drawCircle(w-r,h-r,r,p);
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.STROKE);
            float middle = (-p.ascent()-p.descent())/2;
            canvas.drawText(text, w-textWidths*(1+f/2), h-r+middle, p);
        }

    }
}
