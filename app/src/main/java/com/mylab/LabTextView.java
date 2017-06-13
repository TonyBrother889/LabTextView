package com.mylab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author HaoZhang
 */
public class LabTextView extends View {

    private Paint mPaint;
    private float mTextSize = 16;
    private String text = "text";
    private int color = R.color.colorAccent;
    private int width;
    private int hight;

    public LabTextView(Context context) {
        this(context, null);
    }

    public LabTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabTextView);
        mTextSize = array.getDimension(R.styleable.LabTextView_labTextViewSize, mTextSize);
        array.recycle();
        initPaint();
    }

    /**
     * 初始化画笔设置
     */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setLabColor(int color) {
        this.color = color;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(mTextSize);
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);

        //标签宽度是文字宽度*2
        width = (int) (rect.right * 1.5);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        Paint paint = new Paint();
        paint.setColor(this.getResources().getColor(color));
        paint.setStrokeWidth(hight);
        paint.setAntiAlias(true);

        canvas.drawRect(0, 0, width, hight, paint);  //绘制矩形
         /*画一个实心三角形*/
        Path path2 = new Path();
        path2.lineTo(((width - rect.right) / 2), hight / 2);
        path2.lineTo(((width - rect.right) / 2), hight / 2);
        path2.lineTo(0, hight);
        paint.setColor(Color.WHITE);
        path2.close();
        canvas.drawPath(path2, paint);  //绘制三角形
        canvas.drawText(text, (int) ((width - rect.right) * 0.8), (hight/2)+((int) (Math.abs(fontMetrics.ascent)*0.4)), mPaint);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        mPaint.setTextSize(mTextSize);
        Rect rect = new Rect();
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(text, 0, text.length(), rect);
            //标签宽度是文字宽度*2
            width = (int) (rect.right * 1.5);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            hight = heightSize;
        } else {
            //标签宽度是文字宽度*2
            width = (int) (rect.right * 1.5);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            hight = (int) (Math.abs(fontMetrics.ascent) * 1.4);
        }
        setMeasuredDimension(width, hight);

       /* mPaint.setTextSize(mTextSize);
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);

        //标签宽度是文字宽度*2
        width = (int) (rect.right * 1.5);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        hight = (int) (Math.abs(fontMetrics.ascent) * 1.4);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        // 在wrap_content的情况下默认长度为200dp
        // wrap_content的specMode是AT_MOST模式，这种情况下宽/高等同于specSize
        // 查表得这种情况下specSize等同于parentSize，也就是父容器当前剩余的大小
        // 在wrap_content的情况下如果特殊处理，效果等同martch_parent
        *//*if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            Log.e("111", "111");
            setMeasuredDimension(width, heightSpecSize);
        }*//*
    *//*    if (widthSpecMode == MeasureSpec.AT_MOST) {
            Log.e("222", "111");
            setMeasuredDimension(width, heightSpecSize);
        }*//*
        if (heightSpecMode == MeasureSpec.EXACTLY) {
            Log.e("333", "111");
            setMeasuredDimension(width, heightSpecSize);
        }*/
    }

    public int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }
}
