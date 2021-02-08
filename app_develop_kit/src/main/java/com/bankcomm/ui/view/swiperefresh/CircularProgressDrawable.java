//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bankcomm.ui.view.swiperefresh;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final int LARGE = 0;
    private static final float CENTER_RADIUS_LARGE = 11.0F;
    private static final float STROKE_WIDTH_LARGE = 3.0F;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final int ARROW_HEIGHT_LARGE = 6;
    public static final int DEFAULT = 1;
    private static final float CENTER_RADIUS = 7.5F;
    private static final float STROKE_WIDTH = 2.5F;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_HEIGHT = 5;
    private static final int[] COLORS = new int[]{-16777216};
    private static final float COLOR_CHANGE_OFFSET = 0.75F;
    private static final float SHRINK_OFFSET = 0.5F;
    private static final int ANIMATION_DURATION = 1332;
    private static final float GROUP_FULL_ROTATION = 216.0F;
    private final CircularProgressDrawable.Ring mRing;
    private float mRotation;
    private static final float MAX_PROGRESS_ARC = 0.8F;
    private static final float MIN_PROGRESS_ARC = 0.01F;
    private static final float RING_ROTATION = 0.20999998F;
    private Resources mResources;
    private Animator mAnimator;
    float mRotationCount;
    boolean mFinishing;

    public CircularProgressDrawable(@NonNull Context context) {
        this.mResources = ((Context)Preconditions.checkNotNull(context)).getResources();
        this.mRing = new CircularProgressDrawable.Ring();
        this.mRing.setColors(COLORS);
        this.setStrokeWidth(2.5F);
        this.setupAnimators();
    }

    private void setSizeParameters(float centerRadius, float strokeWidth, float arrowWidth, float arrowHeight) {
        CircularProgressDrawable.Ring ring = this.mRing;
        DisplayMetrics metrics = this.mResources.getDisplayMetrics();
        float screenDensity = metrics.density;
        ring.setStrokeWidth(strokeWidth * screenDensity);
        ring.setCenterRadius(centerRadius * screenDensity);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
    }

    public void setStyle(int size) {
        if (size == 0) {
            this.setSizeParameters(11.0F, 3.0F, 12.0F, 6.0F);
        } else {
            this.setSizeParameters(7.5F, 2.5F, 10.0F, 5.0F);
        }

        this.invalidateSelf();
    }

    public float getStrokeWidth() {
        return this.mRing.getStrokeWidth();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.mRing.setStrokeWidth(strokeWidth);
        this.invalidateSelf();
    }

    public float getCenterRadius() {
        return this.mRing.getCenterRadius();
    }

    public void setCenterRadius(float centerRadius) {
        this.mRing.setCenterRadius(centerRadius);
        this.invalidateSelf();
    }

    public void setStrokeCap(@NonNull Cap strokeCap) {
        this.mRing.setStrokeCap(strokeCap);
        this.invalidateSelf();
    }

    @NonNull
    public Cap getStrokeCap() {
        return this.mRing.getStrokeCap();
    }

    public float getArrowWidth() {
        return this.mRing.getArrowWidth();
    }

    public float getArrowHeight() {
        return this.mRing.getArrowHeight();
    }

    public void setArrowDimensions(float width, float height) {
        this.mRing.setArrowDimensions(width, height);
        this.invalidateSelf();
    }

    public boolean getArrowEnabled() {
        return this.mRing.getShowArrow();
    }

    public void setArrowEnabled(boolean show) {
        this.mRing.setShowArrow(show);
        this.invalidateSelf();
    }

    public float getArrowScale() {
        return this.mRing.getArrowScale();
    }

    public void setArrowScale(float scale) {
        this.mRing.setArrowScale(scale);
        this.invalidateSelf();
    }

    public float getStartTrim() {
        return this.mRing.getStartTrim();
    }

    public float getEndTrim() {
        return this.mRing.getEndTrim();
    }

    public void setStartEndTrim(float start, float end) {
        this.mRing.setStartTrim(start);
        this.mRing.setEndTrim(end);
        this.invalidateSelf();
    }

    public float getProgressRotation() {
        return this.mRing.getRotation();
    }

    public void setProgressRotation(float rotation) {
        this.mRing.setRotation(rotation);
        this.invalidateSelf();
    }

    public int getBackgroundColor() {
        return this.mRing.getBackgroundColor();
    }

    public void setBackgroundColor(int color) {
        this.mRing.setBackgroundColor(color);
        this.invalidateSelf();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.mRing.getColors();
    }

    public void setColorSchemeColors(@NonNull int... colors) {
        this.mRing.setColors(colors);
        this.mRing.setColorIndex(0);
        this.invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Rect bounds = this.getBounds();
        canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(canvas, bounds);
        canvas.restore();
    }

    public void setAlpha(int alpha) {
        this.mRing.setAlpha(alpha);
        this.invalidateSelf();
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
        this.invalidateSelf();
    }

    private void setRotation(float rotation) {
        this.mRotation = rotation;
    }

    private float getRotation() {
        return this.mRotation;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    public void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666L);
            this.mAnimator.start();
        } else {
            this.mRing.setColorIndex(0);
            this.mRing.resetOriginals();
            this.mAnimator.setDuration(1332L);
            this.mAnimator.start();
        }

    }

    public void stop() {
        this.mAnimator.cancel();
        this.setRotation(0.0F);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.invalidateSelf();
    }

    private int evaluateColorChange(float fraction, int startValue, int endValue) {
        int startA = startValue >> 24 & 255;
        int startR = startValue >> 16 & 255;
        int startG = startValue >> 8 & 255;
        int startB = startValue & 255;
        int endA = endValue >> 24 & 255;
        int endR = endValue >> 16 & 255;
        int endG = endValue >> 8 & 255;
        int endB = endValue & 255;
        return startA + (int)(fraction * (float)(endA - startA)) << 24 | startR + (int)(fraction * (float)(endR - startR)) << 16 | startG + (int)(fraction * (float)(endG - startG)) << 8 | startB + (int)(fraction * (float)(endB - startB));
    }

    void updateRingColor(float interpolatedTime, CircularProgressDrawable.Ring ring) {
        if (interpolatedTime > 0.75F) {
            ring.setColor(this.evaluateColorChange((interpolatedTime - 0.75F) / 0.25F, ring.getStartingColor(), ring.getNextColor()));
        } else {
            ring.setColor(ring.getStartingColor());
        }

    }

    private void applyFinishTranslation(float interpolatedTime, CircularProgressDrawable.Ring ring) {
        this.updateRingColor(interpolatedTime, ring);
        float targetRotation = (float)(Math.floor((double)(ring.getStartingRotation() / 0.8F)) + 1.0D);
        float startTrim = ring.getStartingStartTrim() + (ring.getStartingEndTrim() - 0.01F - ring.getStartingStartTrim()) * interpolatedTime;
        ring.setStartTrim(startTrim);
        ring.setEndTrim(ring.getStartingEndTrim());
        float rotation = ring.getStartingRotation() + (targetRotation - ring.getStartingRotation()) * interpolatedTime;
        ring.setRotation(rotation);
    }

    void applyTransformation(float interpolatedTime, CircularProgressDrawable.Ring ring, boolean lastFrame) {
        if (this.mFinishing) {
            this.applyFinishTranslation(interpolatedTime, ring);
        } else if (interpolatedTime != 1.0F || lastFrame) {
            float startingRotation = ring.getStartingRotation();
            float startTrim;
            float endTrim;
            float rotation;
            if (interpolatedTime < 0.5F) {
                rotation = interpolatedTime / 0.5F;
                startTrim = ring.getStartingStartTrim();
                endTrim = startTrim + 0.79F * MATERIAL_INTERPOLATOR.getInterpolation(rotation) + 0.01F;
            } else {
                rotation = (interpolatedTime - 0.5F) / 0.5F;
                endTrim = ring.getStartingStartTrim() + 0.79F;
                startTrim = endTrim - (0.79F * (1.0F - MATERIAL_INTERPOLATOR.getInterpolation(rotation)) + 0.01F);
            }

            rotation = startingRotation + 0.20999998F * interpolatedTime;
            float groupRotation = 216.0F * (interpolatedTime + this.mRotationCount);
            ring.setStartTrim(startTrim);
            ring.setEndTrim(endTrim);
            ring.setRotation(rotation);
            this.setRotation(groupRotation);
        }

    }

    private void setupAnimators() {
        final CircularProgressDrawable.Ring ring = this.mRing;
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float interpolatedTime = (Float)animation.getAnimatedValue();
                CircularProgressDrawable.this.updateRingColor(interpolatedTime, ring);
                CircularProgressDrawable.this.applyTransformation(interpolatedTime, ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        animator.setRepeatCount(-1);
        animator.setRepeatMode(1);
        animator.setInterpolator(LINEAR_INTERPOLATOR);
        animator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.mRotationCount = 0.0F;
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.applyTransformation(1.0F, ring, true);
                ring.storeOriginals();
                ring.goToNextColor();
                if (CircularProgressDrawable.this.mFinishing) {
                    CircularProgressDrawable.this.mFinishing = false;
                    animator.cancel();
                    animator.setDuration(1332L);
                    animator.start();
                    ring.setShowArrow(false);
                } else {
                    ++CircularProgressDrawable.this.mRotationCount;
                }

            }
        });
        this.mAnimator = animator;
    }

    private static class Ring {
        final RectF mTempBounds = new RectF();
        final Paint mPaint = new Paint();
        final Paint mArrowPaint = new Paint();
        final Paint mCirclePaint = new Paint();
        float mStartTrim = 0.0F;
        float mEndTrim = 0.0F;
        float mRotation = 0.0F;
        float mStrokeWidth = 5.0F;
        int[] mColors;
        int mColorIndex;
        float mStartingStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        boolean mShowArrow;
        Path mArrow;
        float mArrowScale = 1.0F;
        float mRingCenterRadius;
        int mArrowWidth;
        int mArrowHeight;
        int mAlpha = 255;
        int mCurrentColor;

        Ring() {
            this.mPaint.setStrokeCap(Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Style.STROKE);
            this.mArrowPaint.setStyle(Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
            this.mCirclePaint.setColor(0);
        }

        void setArrowDimensions(float width, float height) {
            this.mArrowWidth = (int)width;
            this.mArrowHeight = (int)height;
        }

        void setStrokeCap(Cap strokeCap) {
            this.mPaint.setStrokeCap(strokeCap);
        }

        Cap getStrokeCap() {
            return this.mPaint.getStrokeCap();
        }

        float getArrowWidth() {
            return (float)this.mArrowWidth;
        }

        float getArrowHeight() {
            return (float)this.mArrowHeight;
        }

        void draw(Canvas c, Rect bounds) {
            RectF arcBounds = this.mTempBounds;
            float arcRadius = this.mRingCenterRadius + this.mStrokeWidth / 2.0F;
            if (this.mRingCenterRadius <= 0.0F) {
                arcRadius = (float) Math.min(bounds.width(), bounds.height()) / 2.0F - Math.max((float)this.mArrowWidth * this.mArrowScale / 2.0F, this.mStrokeWidth / 2.0F);
            }

            arcBounds.set((float)bounds.centerX() - arcRadius, (float)bounds.centerY() - arcRadius, (float)bounds.centerX() + arcRadius, (float)bounds.centerY() + arcRadius);
            float startAngle = (this.mStartTrim + this.mRotation) * 360.0F;
            float endAngle = (this.mEndTrim + this.mRotation) * 360.0F;
            float sweepAngle = endAngle - startAngle;
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(this.mAlpha);
            float inset = this.mStrokeWidth / 2.0F;
            arcBounds.inset(inset, inset);
            c.drawCircle(arcBounds.centerX(), arcBounds.centerY(), arcBounds.width() / 2.0F, this.mCirclePaint);
            arcBounds.inset(-inset, -inset);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.mPaint);
            this.drawTriangle(c, startAngle, sweepAngle, arcBounds);
        }

        void drawTriangle(Canvas c, float startAngle, float sweepAngle, RectF bounds) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }

                float centerRadius = Math.min(bounds.width(), bounds.height()) / 2.0F;
                float inset = (float)this.mArrowWidth * this.mArrowScale / 2.0F;
                this.mArrow.moveTo(0.0F, 0.0F);
                this.mArrow.lineTo((float)this.mArrowWidth * this.mArrowScale, 0.0F);
                this.mArrow.lineTo((float)this.mArrowWidth * this.mArrowScale / 2.0F, (float)this.mArrowHeight * this.mArrowScale);
                this.mArrow.offset(centerRadius + bounds.centerX() - inset, bounds.centerY() + this.mStrokeWidth / 2.0F);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                this.mArrowPaint.setAlpha(this.mAlpha);
                c.save();
                c.rotate(startAngle + sweepAngle, bounds.centerX(), bounds.centerY());
                c.drawPath(this.mArrow, this.mArrowPaint);
                c.restore();
            }

        }

        void setColors(@NonNull int[] colors) {
            this.mColors = colors;
            this.setColorIndex(0);
        }

        int[] getColors() {
            return this.mColors;
        }

        void setColor(int color) {
            this.mCurrentColor = color;
        }

        void setBackgroundColor(int color) {
            this.mCirclePaint.setColor(color);
        }

        int getBackgroundColor() {
            return this.mCirclePaint.getColor();
        }

        void setColorIndex(int index) {
            this.mColorIndex = index;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        int getNextColor() {
            return this.mColors[this.getNextColorIndex()];
        }

        int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        void goToNextColor() {
            this.setColorIndex(this.getNextColorIndex());
        }

        void setColorFilter(ColorFilter filter) {
            this.mPaint.setColorFilter(filter);
        }

        void setAlpha(int alpha) {
            this.mAlpha = alpha;
        }

        int getAlpha() {
            return this.mAlpha;
        }

        void setStrokeWidth(float strokeWidth) {
            this.mStrokeWidth = strokeWidth;
            this.mPaint.setStrokeWidth(strokeWidth);
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        void setStartTrim(float startTrim) {
            this.mStartTrim = startTrim;
        }

        float getStartTrim() {
            return this.mStartTrim;
        }

        float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        void setEndTrim(float endTrim) {
            this.mEndTrim = endTrim;
        }

        float getEndTrim() {
            return this.mEndTrim;
        }

        void setRotation(float rotation) {
            this.mRotation = rotation;
        }

        float getRotation() {
            return this.mRotation;
        }

        void setCenterRadius(float centerRadius) {
            this.mRingCenterRadius = centerRadius;
        }

        float getCenterRadius() {
            return this.mRingCenterRadius;
        }

        void setShowArrow(boolean show) {
            if (this.mShowArrow != show) {
                this.mShowArrow = show;
            }

        }

        boolean getShowArrow() {
            return this.mShowArrow;
        }

        void setArrowScale(float scale) {
            if (scale != this.mArrowScale) {
                this.mArrowScale = scale;
            }

        }

        float getArrowScale() {
            return this.mArrowScale;
        }

        float getStartingRotation() {
            return this.mStartingRotation;
        }

        void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }

        void resetOriginals() {
            this.mStartingStartTrim = 0.0F;
            this.mStartingEndTrim = 0.0F;
            this.mStartingRotation = 0.0F;
            this.setStartTrim(0.0F);
            this.setEndTrim(0.0F);
            this.setRotation(0.0F);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public @interface ProgressDrawableSize {
    }
}
