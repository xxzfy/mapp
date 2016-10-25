package com.zhao.mapp.tools;

import com.zhao.mapp.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.graphics.Bitmap.Config;
import android.view.MotionEvent;
import android.view.View;

public class SignView extends View {
	private Paint paint;
	private Canvas cacheCanvas;
	private Bitmap cacheBitmap;
	private Path path;
	// 背景色
	private int BACKGROUND_COLOR;
	// 画笔颜色
	private int BRUSH_COLOR;
	private int mapwidth=0;
	private int mapheight=0;
	// 当前触摸位置
	private float cur_x, cur_y;

	/**
	 * 获取图片
	 * 
	 * @return
	 */
	public Bitmap getCacheBitmap() {
		return cacheBitmap;
	}

	public SignView(Context context) {
		super(context);
	}

	public SignView(Context context, AttributeSet attrs) {
		super(context,attrs);
		init(context, attrs);
	}

	public SignView(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
		init(context, attrs);
	}

	/**
	 * 初始化数据
	 */
	private void init(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SignView);
		BACKGROUND_COLOR = ta.getColor(R.styleable.SignView_background_color, Color.WHITE);
		BRUSH_COLOR = ta.getColor(R.styleable.SignView_brush_color, Color.BLACK);
		mapwidth=(int)ta.getLayoutDimension(R.styleable.SignView_android_layout_width, 200);
		mapheight=(int)ta.getLayoutDimension(R.styleable.SignView_android_layout_height, 100);
		// 初始化
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
		paint.setColor(BRUSH_COLOR);
		path = new Path();
		cacheBitmap = Bitmap.createBitmap(mapwidth,mapheight, Config.ARGB_8888);
		cacheCanvas = new Canvas(cacheBitmap);
		cacheCanvas.drawColor(BACKGROUND_COLOR);
		ta.recycle();
		
	}

	// 清除
	public void clear() {
		paint.setColor(BACKGROUND_COLOR);
		cacheCanvas.drawPath(path, paint);
		paint.setColor(BRUSH_COLOR);
		cacheCanvas.drawColor(BACKGROUND_COLOR);
		invalidate();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(cacheBitmap, 0, 0, null);
		canvas.drawPath(path, paint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		int curW = cacheBitmap != null ? cacheBitmap.getWidth() : 0;
		int curH = cacheBitmap != null ? cacheBitmap.getHeight() : 0;
		if (curW > w && curH > h)
			return;
		if (curW < w)
			curW = w;
		if (curH < h)
			curH = h;
		Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
		Canvas newCanvas = new Canvas();
		newCanvas.setBitmap(newBitmap);
		if (cacheBitmap != null) {
			newCanvas.drawBitmap(cacheBitmap, 0, 0, null);
		}
		cacheBitmap = newBitmap;
		cacheCanvas = newCanvas;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			cur_x = x;
			cur_y = y;
			path.moveTo(cur_x, cur_y);
			break;
		case MotionEvent.ACTION_MOVE:
			path.quadTo(cur_x, cur_y, x, y);
			cur_x = x;
			cur_y = y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			path.reset();
			break;
		default:
			break;
		}
		
		invalidate();
		return true;
	}

}
