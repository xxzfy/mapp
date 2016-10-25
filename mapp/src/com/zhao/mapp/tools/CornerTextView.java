package com.zhao.mapp.tools;

import com.zhao.mapp.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

public class CornerTextView extends TextView {
	private float corner;
	public CornerTextView(Context context) {
		super(context);
	}
	public CornerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	public CornerTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context,attrs);
	}
	/**
	 * 初始化方法
	 * @param context
	 * @param attrs
	 */
	private void init(Context context, AttributeSet attrs) {
		TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CornerTextView);
		corner=typedArray.getDimension(R.styleable.CornerTextView_corner, 0);
		
	}
	public float getCorner() {
		return corner;
	}
	//设置之后重新加载
	public void setCorner(float corner) {
		this.corner = corner;
		invalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		int width=getWidth();
		int height=getHeight();
		canvas.drawRoundRect(new RectF(0, 0, width, height), corner, corner, paint);
		super.onDraw(canvas);
	}
}
