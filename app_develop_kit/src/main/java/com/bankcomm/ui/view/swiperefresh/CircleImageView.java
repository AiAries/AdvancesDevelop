//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bankcomm.ui.view.swiperefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
class CircleImageView extends ImageView {
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final float X_OFFSET = 0.0F;
    private static final float Y_OFFSET = 1.75F;
    private static final float SHADOW_RADIUS = 3.5F;
    private static final int SHADOW_ELEVATION = 4;
    private AnimationListener mListener;
    int mShadowRadius;

    CircleImageView(Context context, int color) {
        super(context);
        float density = this.getContext().getResources().getDisplayMetrics().density;
        int shadowYOffset = (int)(density * 1.75F);
        int shadowXOffset = (int)(density * 0.0F);
        this.mShadowRadius = (int)(density * 3.5F);
        ShapeDrawable circle;
        if (this.elevationSupported()) {
            circle = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, 4.0F * density);
        } else {
            OvalShape oval = new CircleImageView.OvalShadow(this.mShadowRadius);
            circle = new ShapeDrawable(oval);
            this.setLayerType(1, circle.getPaint());
            circle.getPaint().setShadowLayer((float)this.mShadowRadius, (float)shadowXOffset, (float)shadowYOffset, 503316480);
            int padding = this.mShadowRadius;
            this.setPadding(padding, padding, padding, padding);
        }

        circle.getPaint().setColor(color);
        ViewCompat.setBackground(this, circle);
    }

    private boolean elevationSupported() {
        return VERSION.SDK_INT >= 21;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!this.elevationSupported()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
        }

    }

    public void setAnimationListener(AnimationListener listener) {
        this.mListener = listener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(this.getAnimation());
        }

    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(this.getAnimation());
        }

    }

    public void setBackgroundColorRes(int colorRes) {
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), colorRes));
    }

    public void setBackgroundColor(int color) {
        if (this.getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable)this.getBackground()).getPaint().setColor(color);
        }

    }

    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint = new Paint();

        OvalShadow(int shadowRadius) {
            CircleImageView.this.mShadowRadius = shadowRadius;
            this.updateRadialGradient((int)this.rect().width());
        }

        protected void onResize(float width, float height) {
            super.onResize(width, height);
            this.updateRadialGradient((int)width);
        }

        public void draw(Canvas canvas, Paint paint) {
            int viewWidth = CircleImageView.this.getWidth();
            int viewHeight = CircleImageView.this.getHeight();
            canvas.drawCircle((float)(viewWidth / 2), (float)(viewHeight / 2), (float)(viewWidth / 2), this.mShadowPaint);
            canvas.drawCircle((float)(viewWidth / 2), (float)(viewHeight / 2), (float)(viewWidth / 2 - CircleImageView.this.mShadowRadius), paint);
        }

        private void updateRadialGradient(int diameter) {
            this.mRadialGradient = new RadialGradient((float)(diameter / 2), (float)(diameter / 2), (float)CircleImageView.this.mShadowRadius, new int[]{1023410176, 0}, (float[])null, TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }
    }
}
