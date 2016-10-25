package com.zhao.mapp.tools;

import com.zhao.mapp.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class StartFlowTextView extends TextView {
	private float arrowWidth;
	private int color;

	public StartFlowTextView(Context context) {
		super(context);
	}

	public StartFlowTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public StartFlowTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	/**
	 * 初始化
	 */
	private void init(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowTextView);
		arrowWidth = typedArray.getDimension(R.styleable.FlowTextView_arrowWidth, 0);
		color = typedArray.getColor(R.styleable.FlowTextView_bg, Color.RED);
	}

	/**
	 * @param arrowWidth
	 *            三角形箭头的宽度.......
	 */
	public void setArrowWidth(float arrowWidth) {
		this.arrowWidth = arrowWidth;
		invalidate();

	}
	
	/**
	 * @param color
	 *            箭头矩形的背景色.........
	 */
	public void setBgColor(int color) {
		this.color = color;
		invalidate();

	}
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(color==0?Color.RED:color);
		paint.setAntiAlias(true);

		int height = getHeight();
		int width=getWidth();
		arrowWidth = height / 2;
		//画左侧反箭头
		Path path=new Path();
		path.moveTo(0, 0);
		path.lineTo(width-arrowWidth, 0);
		path.lineTo(width, arrowWidth);
		path.lineTo(width-arrowWidth, height);
		path.lineTo(0, height);
		path.lineTo(0, 0);
		path.close();
		canvas.drawPath(path, paint);
		
		super.onDraw(canvas);
	}
}
