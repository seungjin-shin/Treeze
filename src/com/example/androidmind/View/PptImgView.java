package com.example.androidmind.View;

import java.io.File;
import java.util.ArrayList;

import com.example.adroidmind.data.Class;
import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Notes;
import com.example.androidmind.PptImg;
import com.example.androidmind.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.FloatMath;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewConfiguration;

public class PptImgView extends View implements OnTouchListener, Runnable,
		OnKeyListener {
	MindNode node;

	Boolean screenMode = false;
	private Drawable backgroud;
	private Class classinfo;
	NoteView noteView;
	private PopupWindow notePopupWindow;

	private ArrayList<Path> paths;
	private ArrayList<Path> hpaths = new ArrayList<Path>();
	private ArrayList<Path> wpaths = new ArrayList<Path>();
	private ArrayList<Path> rpaths = new ArrayList<Path>();

	private Paint mPaint, rPaint, hPaint, wPaint;
	private Path mPath;
	private float f_x = 0, f_y = 0;

	static View pv;
	static private PointF start = new PointF();
	Vibrator vibe;
	// 시작 위치를 저장을 위한 변수
	private float mLastMotionX = 0;
	private float mLastMotionY = 0;
	final Handler mHandler = new Handler();
	Bitmap NoteImg = BitmapFactory.decodeResource(getResources(),
			R.drawable.note);
	Bitmap bitmap = Bitmap.createScaledBitmap(NoteImg, 50, 50, true);
	// 마우스 move 로 일정범위 벗어나면 취소하기 위한 값
	private int mTouchSlop = 10;
	Context context;
	// long click 을 위한 변수들
	private boolean mHasPerformedLongPress;
	private PptImgView mPendingCheckForLongPress;

	public PptImgView(Context context, Class classinfo) {
		super(context);
		pv = this;
		// TODO Auto-generated constructor stub
		// setBackgroundResource(R.drawable.nodebg);
		this.classinfo = classinfo;
		setBackgroundColor(Color.WHITE);
		node = MindNode.getNow();
		paths = node.getPath();
		this.context = context;
		vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		init();
		setOnTouchListener(this);
		setOnKeyListener(this);
		Bitmap b = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() +"/Treeze/"+classinfo.getClassId()+"/"+node.getNodeStr()+".jpg");
		backgroud = new BitmapDrawable(b);
		this.setBackgroundDrawable(backgroud);
		// this.setBackgroundDrawable(backgroud);
		// this.setBackground(backgroud);

		for (Notes note : node.getNotes()) {
			note.setOpen(false); // 전에 note를 안끄고 전환했을경우가 있기때문에 false 로 init
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		for (Path p : paths) {
			canvas.drawPath(p, mPaint);
		}
//		for (Path p : rpaths) {
//			canvas.drawPath(p, rPaint);
//		}
//		for (Path p : wpaths) {
//			canvas.drawPath(p, wPaint);
//		}
//		for (Path p : hpaths) {
//			canvas.drawPath(p, hPaint);
//		}
		for (int i = 0; i < node.getNotes().size(); i++) {
			canvas.drawBitmap(bitmap,
					node.getNotes().get(node.getNotes().size() - (i + 1))
							.getX(),
					node.getNotes().get(node.getNotes().size() - (i + 1))
							.getY(), null);
		}

	}

	private void init() {
		// BitmapFactory.Options bfo = new BitmapFactory.Options();
		// bfo.inSampleSize = 1;

		// Bitmap b = BitmapFactory.decodeResource(getResources(),
		// R.drawable.slide);
		// Bitmap b = BitmapFactory.decodeFile(fileName);
		// Bitmap resize = Bitmap.createScaledBitmap(b, 1200, 720, true);
		// b= Bitmap.createScaledBitmap(b, 1200, 720, true);
		// map = new BitmapDrawable(b);
		paintset();

		mPath = new Path();

	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath = new Path();
		mPath.reset();
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	public void paintset() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFF000000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(2);
//		rPaint = new Paint();
//		rPaint.setAntiAlias(true);
//		rPaint.setDither(true);
//		rPaint.setColor(Color.RED);
//		rPaint.setStyle(Paint.Style.STROKE);
//		rPaint.setStrokeJoin(Paint.Join.ROUND);
//		rPaint.setStrokeCap(Paint.Cap.ROUND);
//		rPaint.setStrokeWidth(2);
//		wPaint = new Paint();
//		wPaint.setAntiAlias(true);
//		wPaint.setDither(true);
//		wPaint.setColor(Color.WHITE);
//		wPaint.setStyle(Paint.Style.STROKE);
//		wPaint.setStrokeJoin(Paint.Join.ROUND);
//		wPaint.setStrokeCap(Paint.Cap.ROUND);
//		wPaint.setStrokeWidth(2);
//		hPaint = new Paint();
//		hPaint.setAntiAlias(true);
//		hPaint.setDither(true);
//		hPaint.setColor(0x55f8fb07);
//		hPaint.setStyle(Paint.Style.STROKE);
//		hPaint.setStrokeJoin(Paint.Join.ROUND);
//		hPaint.setStrokeCap(Paint.Cap.ROUND);
//		hPaint.setStrokeWidth(10);
	}

	private void touch_start(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;

		paths.add(mPath);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		v.invalidate();
		float x = event.getX();
		float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			start.set(event.getX(), event.getY());
			mLastMotionX = event.getX();
			mLastMotionY = event.getY(); // 시작 위치 저장
			mHasPerformedLongPress = false;
			postCheckForLongClick(0); // Long click message 설정
			for (Notes note : node.getNotes()) {
				if (note.clickNote(x, y) != null) {
					Log.d("check", "이도드가 클린됨->" + node.getNodeStr());
					// final Context context = v.getContext();
					// final Intent intent = new Intent();
					// MindNode.setNow(node);
					// intent.setClass(context, PptImg.class);
					// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// context.startActivity(intent);
					if (!note.getOpen()) {
						noteView = new NoteView(v.getContext(), note);
						notePopupWindow = new PopupWindow(noteView,
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
						noteView.setPopupWindow(notePopupWindow);
						note.setPopupWindow(notePopupWindow);
						note.setOpen();
						notePopupWindow.setAnimationStyle(-1); // 애니메이션
																// 설정(-1:설정,
																// 0:설정안함)
						notePopupWindow.showAtLocation(this,
								Gravity.NO_GRAVITY, (int) note.getX(),
								(int) note.getY() + note.getYScale());
						// notePopupWindow.showAsDropDown(this,(int)note.getX(),(int)note.getY());
						notePopupWindow.setTouchable(true);
					} else {

						note.getNotePopupWindow().dismiss();
						note.setOpen();
					}
					break;
				}

			}
			touch_start(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			final float mx = event.getX();
			final float my = event.getY();
			final int deltaX = Math.abs((int) (mLastMotionX - mx));
			final int deltaY = Math.abs((int) (mLastMotionY - my));
			touch_move(x, y);
			// 일정 범위 벗어나면 취소함
			if (deltaX >= mTouchSlop || deltaY >= mTouchSlop) {
				if (!mHasPerformedLongPress) {
					// This is a tap, so remove the longpress check
					removeLongPressCallback();
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			if (!mHasPerformedLongPress) {
				// This is a tap, so remove the longpress check
				removeLongPressCallback();
			}
			break;
		case MotionEvent.ACTION_UP:
			if (!mHasPerformedLongPress) {
				// Long Click을 처리되지 않았으면 제거함.
				removeLongPressCallback();
				// Short Click 처리 루틴을 여기에 넣으면 됩니다.
			}
			break;

		}

		return true;
		// false;}
	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	public class drawPoint {
		private float x;
		private float y;
		private boolean draw;

		public drawPoint(float x, float y, boolean d) {
			this.x = x;
			this.y = y;
			draw = d;
		}

		public boolean getDraw() {
			return draw;
		}

		public void setDraw(boolean _draw) {
			draw = _draw;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (performLongClick()) {
			mHasPerformedLongPress = true;
		}
	}

	private void postCheckForLongClick(int delayOffset) {
		mHasPerformedLongPress = false;

		if (mPendingCheckForLongPress == null) {
			mPendingCheckForLongPress = new PptImgView(context,classinfo);
		}

		mHandler.postDelayed(mPendingCheckForLongPress,
				ViewConfiguration.getLongPressTimeout() - delayOffset);
		// 여기서 시스템의 getLongPressTimeout() 후에 message 수행하게 합니다.
		// 추가 delay가 필요한 경우를 위해서 파라미터로 조절가능하게 합니다.
	}

	private void removeLongPressCallback() {
		if (mPendingCheckForLongPress != null) {
			mHandler.removeCallbacks(mPendingCheckForLongPress);
		}
	}

	public boolean performLongClick() {
		// 실제 Long Click 처리하는 부분을 여기 둡니다.

		node.getNotes().add(
				new Notes(start.x - Notes.getXScale() / 2, start.y
						- Notes.getYScale() / 2)); // 가운데 위치하기위해
		vibe.vibrate(100); // 진동

		return true;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}