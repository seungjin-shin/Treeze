package com.example.androidmind.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Locale;

import com.example.adroidmind.data.CurrentPositionOfNav;
import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Notes;
import com.example.adroidmind.data.Survey;
import com.example.adroidmind.data.Ticket;
import com.example.adroidmind.data.TicketInfo;
import com.example.android.network.ProfessorSocket;
import com.example.androidmind.PptImg;

import com.example.androidmind.R;
import com.example.androidmind.R.drawable;
import com.example.androidmind.R.string;
import com.example.androidmind.TicketActivity;

import com.example.androidmind.dialog.QuizDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Join;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.StaticLayout;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MindView extends View implements OnTouchListener, Runnable {
	static int a = 0;
	int startX = 0;
	int startY = 0;
	static boolean click;
	float[] value = new float[9];;
	private ArrayList<MindNode> nodes;
	private Survey survey;
	private Drawable map;
	private Matrix matrix = new Matrix();
	private static final int WIDTH = 0;
	private static final int HEIGHT = 1;
	private Matrix savedMatrix = new Matrix();
	private Matrix savedMatrix2 = new Matrix();
	private Matrix firstMatrix = new Matrix();
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	private PointF start = new PointF();
	private PointF mid = new PointF();
	private float oldDist = 1f;
	private int mode = NONE;
	private float scalex = 800f;
	private float scale = 1f;
	private float scaley = 480f;
	private int scaleWidth, scaleHeight;
	private OutputStream outputstream;
	private String ipAddress = new String();
	private com.example.adroidmind.data.Class classinfo;
	Intent ticketIntent = new Intent();
	
	
	
	// //longclick
	private int mTouchSlop = 10;
	private float mLastMotionX = 0;
	private float mLastMotionY = 0;
	private boolean mHasPerformedLongPress;
	private MindView mPendingCheckForLongPress;
	final Handler mHandler = new Handler();
	Handler viewUpdateHandler;
	Handler surveyHandler;
	// //
	Bitmap nodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.nodebg);
	Bitmap rootNodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.rootnodebg);
	Bitmap passNodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.passnodebg);
	Bitmap passRootNodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.passrootbg);
	Bitmap nextRootNodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.nextrootnodebg);
	Bitmap nextNodeBg = BitmapFactory.decodeResource(getResources(),
			R.drawable.nextnodebg);
	Bitmap ticketMark = BitmapFactory.decodeResource(getResources(),
			R.drawable.ticketmark);
	MindNode root;
	MindNode node;
	MindNode node1_1;
	MindNode node1_2;
	MindNode node1_3;
	MindNode node2;
	MindNode node3;
	MindNode node3_1;
	MindNode node3_2;
	MindNode node3_3;
	MindNode node3_4;
	MindNode node4;
	MindNode node4_1;
	MindNode node4_2;
	MindNode node4_3;

	Button btn;
	int sumX = 0;
	int sumY = 0;

	private Context vContext = null;
	private Activity ticketActivity;
	public MindView(Context context , ArrayList<MindNode> nodes, com.example.adroidmind.data.Class classinfo) {
		super(context);
		vContext = context;
		ticketActivity = (Activity) context;
		float screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
		float screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
		Bitmap onImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.nodebg);
		Bitmap resize = Bitmap.createScaledBitmap(onImg, "루트노드".length() * 15,
				"루트노드".length() * 8, true);
		this.nodes = nodes;
		this.classinfo = classinfo;
		
//		root = new MindNode("1.리눅스소개", (int) screenWidth / 2
//				- resize.getWidth() / 2, (int) screenHeight / 2
//				- resize.getHeight() / 2);
//		node = new MindNode(root, "리눅스의 역사", 1);
//		node1_1 = new MindNode(node, "Linux도 운영 체제", 1);
//		node1_2 = new MindNode(node, "Linux 전에는 Unix…", 1);
//		node1_3 = new MindNode(node, "Linux의 특징", 1);
//
//		node2 = new MindNode(root, "공개 소스 소프트웨어", 0);
//
//		node3 = new MindNode(root, "Linux 배포판 들 ", 1);
//
//		node3_1 = new MindNode(node3, "ubuntu ", 1);
//		node3_2 = new MindNode(node3, "Linux 배포판 들 ", 1);
//		node3_3 = new MindNode(node3, "인터페이스 부분", 1);
//		node3_4 = new MindNode(node3, "Linux 기본 개념들…", 1);
//
//		node4 = new MindNode(root, "시스템 호출 정리", 0);
//		node4_1 = new MindNode(node4, "Top 명령 ", 0);
//		node4_2 = new MindNode(node4, "Windows 작업관리자", 0);
//		node4_3 = new MindNode(node4, "시스템 호출", 0);
//
//		nodes.add(root);
//		nodes.add(node);
//		nodes.add(node1_1);
//		nodes.add(node1_2);
//		nodes.add(node1_3);
//
//		nodes.add(node2);
//
//		nodes.add(node3);
//		nodes.add(node3_1);
//		nodes.add(node3_2);
//		nodes.add(node3_3);
//		nodes.add(node3_4);
//		nodes.add(node4);
//		nodes.add(node4_1);
//		nodes.add(node4_2);
//		nodes.add(node4_3);

		MindNode.setNow(null);
		setOnTouchListener(this);
		// try {
		// naviSocket = new Socket("113.198.84.70", 2141);
		// naviInputStream = naviSocket.getInputStream();
		// Log.d("check", "신건영짱");
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// Log.d("check", "신승진 병신");
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// Log.d("check", "신승진 병신");
		// e.printStackTrace();
		// } // 로컬 서버에 연결
		// // TODO Auto-generated constructor stub
		viewUpdateHandler = new Handler() {
			public void handleMessage(Message msg) {
				if(msg.what==1)
					Toast.makeText(vContext, "교수와 연결이 끊어 졌습니다.", Toast.LENGTH_SHORT).show();
				else
				invalidate();
					
			}
		};
		surveyHandler = new Handler() {
			public void handleMessage(Message msg) {

				showSurveyDialog(survey);

			}
		};
		socketThread socketThread = new socketThread(viewUpdateHandler);

		socketThread.start();
	}

	// private void saveImageMatrix() {
	//
	// matrix.getValues(value);
	//
	// int width = getWidth();
	// int height = getHeight();
	//
	// int imageWidth = 10000;
	// int imageHeight = 20000;
	// int scaleWidth = (int) (imageWidth * value[0]);
	// int scaleHeight = (int) (imageHeight * value[4]);
	//
	// value[2] = 0;
	// value[5] = 0;
	//
	// if (imageWidth > width || imageHeight > height) {
	// int target = WIDTH;
	// if (imageWidth < imageHeight)
	// target = HEIGHT;
	//
	// if (target == WIDTH)
	// value[0] = value[4] = (float) width / imageWidth;
	// if (target == HEIGHT)
	// value[0] = value[4] = (float) height / imageHeight;
	//
	// scaleWidth = (int) (imageWidth * value[0]);
	// scaleHeight = (int) (imageHeight * value[4]);
	//
	// if (scaleWidth > width)
	// value[0] = value[4] = (float) width / imageWidth;
	// if (scaleHeight > height)
	// value[0] = value[4] = (float) height / imageHeight;
	// }
	//
	// scaleWidth = (int) (imageWidth * value[0]);
	// scaleHeight = (int) (imageHeight * value[4]);
	// if (scaleWidth < width) {
	// value[2] = (float) width / 2 - (float) scaleWidth / 2;
	// }
	// if (scaleHeight < height) {
	// value[5] = (float) height / 2 - (float) scaleHeight / 2;
	// }
	// firstMatrix.setValues(value);
	// }

	private void matrixTurning(Matrix matrix) {
		// 매트릭스 값
		// float[] value = new float[9];
		matrix.getValues(value);
		float[] savedValue = new float[9];
		savedMatrix2.getValues(savedValue);

		// 뷰 크기
		int width = getWidth();
		int height = getHeight();

		// 이미지 크기
		int imageWidth = 800;
		int imageHeight = 480;
		scaleWidth = (int) (imageWidth * value[0]);
		scaleHeight = (int) (imageHeight * value[4]);

		// 이미지가 바깥으로 나가지 않도록.
		// if (value[2] < width - scaleWidth)
		// value[2] = width - scaleWidth;
		// if (value[5] < height - scaleHeight)
		// value[5] = height - scaleHeight;
		// if (value[2] > 0)
		// value[2] = 0;
		// if (value[5] > 0)
		// value[5] = 0;
		//
		// 10배 이상 확대 하지 않도록
		if (value[0] > 10 || value[4] > 10) {
			value[0] = savedValue[0];
			value[4] = savedValue[4];
			value[2] = savedValue[2];
			value[5] = savedValue[5];
		}

		if (imageWidth > width || imageHeight > height) {
			if (scaleWidth < width && scaleHeight < height) {
				int target = WIDTH;
				if (imageWidth < imageHeight)
					target = HEIGHT;

				if (target == WIDTH)
					value[0] = value[4] = (float) width / imageWidth;
				if (target == HEIGHT)
					value[0] = value[4] = (float) height / imageHeight;

				scaleWidth = (int) (imageWidth * value[0]);
				scaleHeight = (int) (imageHeight * value[4]);

				if (scaleWidth > width)
					value[0] = value[4] = (float) width / imageWidth;
				if (scaleHeight > height)
					value[0] = value[4] = (float) height / imageHeight;
			}
		}

		// 원래부터 작은 얘들은 본래 크기보다 작게 하지 않도록
		// else {
		// if (value[0] < 1)
		// value[0] = 1;
		// if (value[4] < 1)
		// value[4] = 1;
		// }

		// 그리고 가운데 위치하도록 한다.
		// scaleWidth = (int) (imageWidth * value[0]);
		// scaleHeight = (int) (imageHeight * value[4]);
		// if (scaleWidth < width) {
		// value[2] = (float) width / 2 - (float) scaleWidth / 2;
		// }
		// if (scaleHeight < height) {
		// value[5] = (float) height / 2 - (float) scaleHeight / 2;
		// }
		matrix.setValues(value);
		savedMatrix2.set(matrix);
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.concat(matrix);
		System.out.println(a);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) vContext).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		// int deviceWidth = displayMetrics.widthPixels;
		// int deviceHeight =displayMetrics.heightPixels;

		String str = new String("알짜만 골라 배우는 안드로이드" + a);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(20);

		// Bitmap resize = Bitmap.createScaledBitmap(onImg,
		// deviceWidth-(deviceWidth/str.length()*10),
		// deviceHeight-(deviceHeight/str.length()*20), true);

		Bitmap resize;
		drawMind(canvas, nodes);
		// resize = Bitmap.createScaledBitmap(onImg,
		// root.getScaleX(),
		// root.getScaleY(), true);
		// canvas.drawBitmap(resize, root.getLocateX(), root.getLocateY(),
		// null);
		// canvas.drawText(root.getNodeStr(),
		// root.getLocateX()+root.getNodeStr().length()*2,
		// (root.getMiddleY()+root.getEndY())/2, paint);
		//
		// resize = Bitmap.createScaledBitmap(onImg,
		// node.getScaleX(),node.getScaleY()
		// , true);
		// canvas.drawBitmap(resize, node.getLocateX(), node.getLocateY(),
		// null);
		// canvas.drawText(node.getNodeStr(),
		// node.getLocateX()+node.getNodeStr().length()*2,
		// (node.getEndY()+node.getMiddleY())/2, paint);
		// drawMind(canvas,root);
		// drawMind(canvas,node);
		// drawMind(canvas,node2);
		// drawMind(canvas,node3);
		// drawMind(canvas,node4);
		// drawMind(canvas,node1_1);
		// drawMind(canvas,node1_2);
		// drawMind(canvas,node1_3);
		// drawMind(canvas,node1_3_1);
		// drawMind(canvas,node1_3_2);
		// drawMind(canvas,node1_3_3);
		// drawMind(canvas,node2_1);
		// drawMind(canvas,node2_2);
		// drawMind(canvas,node2_3);
		// drawMind(canvas,node2_1_1);
		// drawMind(canvas,node2_1_2);
		// drawMind(canvas,node2_1_2_1);
		// drawMind(canvas,node2_1_2_2);
		// drawMind(canvas,node2_1_2_2);
		//
		// resize = Bitmap.createScaledBitmap(onImg,
		// node2.getNodeStr().length() * 15,
		// node2.getNodeStr().length() * 8, true);
		// canvas.drawBitmap(resize, node2.getLocateX(), node2.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node3.getLocateX(), node3.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node4.getLocateX(), node4.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node1_1.getLocateX(), node1_1.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node1_2.getLocateX(), node1_2.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node1_3.getLocateX(), node1_3.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node2_1.getLocateX(), node2_1.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node2_2.getLocateX(), node2_2.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node2_3.getLocateX(), node2_3.getLocateY(),
		// null);
		// canvas.drawBitmap(resize, node1_3_1.getLocateX(),
		// node1_3_1.getLocateY(), null);
		// canvas.drawBitmap(resize, node1_3_2.getLocateX(),
		// node1_3_2.getLocateY(), null);
		// canvas.drawBitmap(resize, node1_3_3.getLocateX(),
		// node1_3_3.getLocateY(), null);
		// canvas.drawBitmap(resize, node2_1_1.getLocateX(),
		// node2_1_1.getLocateY(), null);
		// canvas.drawBitmap(resize, node2_1_2.getLocateX(),
		// node2_1_2.getLocateY(), null);
		//
		// canvas.drawBitmap(resize, node2_1_2_1.getLocateX(),
		// node2_1_2_1.getLocateY(), null);
		// canvas.drawBitmap(resize, node2_1_2_2.getLocateX(),
		// node2_1_2_2.getLocateY(), null);
		// canvas.drawBitmap(resize, screenWidth/2-resize.getWidth()/2,
		// screenHeight/2-resize.getHeight()/2, null);

		// canvas.drawText(str, screenWidth/2-resize.getWidth()/2+str.length(),
		// screenHeight/2-resize.getHeight()/2+resize.getHeight()/2+paint.getTextSize()/2,
		// paint);
		// canvas.drawBitmap(resize, -100, -100, null);

		// canvas.drawBitmap(resize, 100, 100, null);
		// canvas.drawText(str, 5, 10, 100, 100, null);
		// paint.setTextScaleX(100);

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

	private void drawMind(Canvas canvas, ArrayList<MindNode> nodesList) {
		// TODO Auto-generated method stub
		Bitmap resize;

		Paint paint = new Paint();
		Paint linePaint = new Paint();
		Paint passLinePaint = new Paint();
		paint.setTextSize(20);
		// paint.setStyle(Paint.Style.FILL);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.BLUE);
		paint.setTextAlign(Align.CENTER);
		paint.setStrokeWidth(1);
		linePaint.setColor(Color.BLACK);
		linePaint.setStrokeWidth(2);
		passLinePaint.setColor(Color.rgb(37, 170, 226));
		passLinePaint.setStrokeWidth(10);
		
		for (MindNode node : nodesList) {
			
			if (node == node.getRoot()
					|| node.getParentNode() == node.getRoot()) {
				resize = Bitmap.createScaledBitmap(rootNodeBg,
						node.getScaleX(), node.getScaleY(), true);
			} else {
				resize = Bitmap.createScaledBitmap(nodeBg, node.getScaleX(),
						node.getScaleY(), true);
			}
			canvas.drawBitmap(resize, node.getLocateX(), node.getLocateY(),
					null);

			canvas.drawText(node.getNodeStr(),
					(node.getLocateX() + node.getEndX()) / 2, node.getMiddleY()
							+ (node.getEndY() - node.getMiddleY()) / 3, paint);
			if (node.isPassed()) {
				if (node != node.getRoot()) {
					if (node.getDirection() == 0) {
						canvas.drawLine(
								(float) (node.getParentNode().getEndX()), node
										.getParentNode().getMiddleY(), node
										.getLocateX(), node.getMiddleY(),
								passLinePaint);
					} else {
						canvas.drawLine(node.getEndX(), node.getMiddleY(), node
								.getParentNode().getLocateX(), node
								.getParentNode().getMiddleY(), passLinePaint);
					}

				}

			} else {

				if (node != node.getRoot()) {
					if (node.getDirection() == 0) {
						canvas.drawLine(
								(float) (node.getParentNode().getEndX()), node
										.getParentNode().getMiddleY(), node
										.getLocateX(), node.getMiddleY(),
								linePaint);
					} else {
						canvas.drawLine(node.getEndX(), node.getMiddleY(), node
								.getParentNode().getLocateX(), node
								.getParentNode().getMiddleY(), linePaint);
					}

				}
			}
			
			
	
		}
		if (MindNode.getNow() != null) {
			if (MindNode.getNow() == MindNode.getNow().getRoot()
					|| MindNode.getNow().getParentNode() == MindNode.getNow()
							.getRoot())
				resize = Bitmap.createScaledBitmap(passRootNodeBg, MindNode
						.getNow().getScaleX(), MindNode.getNow().getScaleY(),
						true);
			else {
				resize = Bitmap.createScaledBitmap(passNodeBg, MindNode
						.getNow().getScaleX(), MindNode.getNow().getScaleY(),
						true);
			}
			canvas.drawBitmap(resize, MindNode.getNow().getLocateX(), MindNode
					.getNow().getLocateY(), null);
			canvas.drawText(MindNode.getNow().getNodeStr(), (MindNode.getNow()
					.getLocateX() + MindNode.getNow().getEndX()) / 2, MindNode
					.getNow().getMiddleY()
					+ (MindNode.getNow().getEndY() - MindNode.getNow()
							.getMiddleY()) / 3, paint);
			if (MindNode.getNow().getNext() != null) {
				if (MindNode.getNow().getNext() == MindNode.getNow().getRoot()
						|| MindNode.getNow().getNext().getParentNode() == MindNode
								.getNow().getRoot())
					resize = Bitmap.createScaledBitmap(nextRootNodeBg, MindNode
							.getNow().getNext().getScaleX(), MindNode.getNow()
							.getNext().getScaleY(), true);
				else {
					resize = Bitmap.createScaledBitmap(nextNodeBg, MindNode
							.getNow().getNext().getScaleX(), MindNode.getNow()
							.getNext().getScaleY(), true);
				}
				canvas.drawBitmap(resize, MindNode.getNow().getNext()
						.getLocateX(),
						MindNode.getNow().getNext().getLocateY(), null);
				canvas.drawText(
						MindNode.getNow().getNext().getNodeStr(),
						(MindNode.getNow().getNext().getLocateX() + MindNode
								.getNow().getNext().getEndX()) / 2,
						MindNode.getNow().getNext().getMiddleY()
								+ (MindNode.getNow().getNext().getEndY() - MindNode
										.getNow().getNext().getMiddleY()) / 3,
						paint);
			}
		}
		for(MindNode node : nodesList){
		if(node.isExistTicket()){
			resize = Bitmap.createScaledBitmap(ticketMark, 40,
					40, true);
			canvas.drawBitmap(resize,node.getEndX()-50,
					node.getLocateY()+5, null);
		}
		}
	}

	// appnme
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();

		// float[] value = new float[9];
		matrixTurning(matrix);
		float absolute_x = (event.getX() / value[0]) - (value[2] / value[0]);
		float absolute_y = (event.getY() / value[4]) - (value[5] / value[4]);

		// float x = event.getX() / (scale + (scaleWidth - scalex) / 800)
		// + (Math.abs(value[2]) / (scale + (scaleWidth - scalex) / 800));
		// float y = event.getY() / (scale + (scaleHeight - scaley) / 480)
		// + (Math.abs(value[5]) / (scale + (scaleHeight - scaley) / 480));
		// matrix.getValues(value);

		// Log.d("check", "" +"x값 = "+absolute_x+"y값은 = "+absolute_y);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			click = true;
			mLastMotionX = event.getX();
			mLastMotionY = event.getY(); // 시작 위치 저장
		
			for (MindNode node : nodes) {
				if (node.clickNode((int) absolute_x, (int) absolute_y) != null) {
					
					MindNode.setNow(node);

					break;
				}

			}
			mHasPerformedLongPress = false;
			postCheckForLongClick(0);
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
			}
			
			break;

		case MotionEvent.ACTION_POINTER_UP:

			mode = NONE;

			break;
		case MotionEvent.ACTION_MOVE:
			
			final float mx = event.getX();
			final float my = event.getY();
			final int deltaX = Math.abs((int) (mLastMotionX - mx));
			final int deltaY = Math.abs((int) (mLastMotionY - my));
			if (mode == DRAG) {
				matrix.set(savedMatrix);
				// Log.d("check", "시작 = "+start.x + "무브 = "+event.getX());

				// Log.d("check", "Sumx = "+sumX);
				matrix.postTranslate(event.getX() - start.x, event.getY()
						- start.y);

			} else if (mode == ZOOM) {
				float newDist = spacing(event);
				
				if (newDist > 10f) {
					matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
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

			if (click) {
				for (MindNode node : nodes) {
					if (node.clickNode((int) absolute_x, (int) absolute_y) != null) {

						Log.d("check", "이도드가 클린됨->" + node.getNodeStr());
						final Context context = v.getContext();
						final Intent intent = new Intent();
						MindNode.setNow(node);
						// node.setPassed(true);
						intent.setClass(context, PptImg.class);
						intent.putExtra("class", classinfo);
						
						context.startActivity(intent);

						break;
					}

				}
				if (!mHasPerformedLongPress) {
					// Long Click을 처리되지 않았으면 제거함.
					removeLongPressCallback();
					// Short Click 처리 루틴을 여기에 넣으면 됩니다.
				}
				break;
			}
		}
		// 매트릭스 값 튜닝.
		matrixTurning(matrix);
		return true;

	
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
			mPendingCheckForLongPress = this;
		}

		mHandler.postDelayed(mPendingCheckForLongPress,
				ViewConfiguration.getLongPressTimeout() - delayOffset+300);
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

		// node.getNotes().add(
		// new Notes(start.x - Notes.getXScale() / 2, start.y
		// - Notes.getYScale() / 2));
		//
		click = false;
		if (node.getNow() == null||node.getNow().equals(node.getRoot()))
			return true;
		
		// MindNode.setNow(node);
		// intent.setClass(context, PptImg.class);
		ticketIntent.setClass(vContext, TicketActivity.class);
		ticketIntent.putExtra("class", classinfo);
		
		ticketActivity.startActivityForResult(ticketIntent, 1010);
		
		return true;
	}

	public void showSurveyDialog(Survey survey) {
		QuizDialog quizDialog = new QuizDialog(vContext, survey, outputstream);
		LayoutParams params = quizDialog.getWindow().getAttributes();
		params.width = LayoutParams.FILL_PARENT;
		params.height = LayoutParams.WRAP_CONTENT;
		quizDialog.getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);

		quizDialog.show();

	}

	class socketThread extends Thread {
		
		InputStream inputStream;
		Handler viewUpdateHandle;

		public socketThread(Handler viewUpdateHandle) {
			// TODO Auto-generated constructor stub
			this.viewUpdateHandle = viewUpdateHandle;
		}

		public void run() {

			try {
				// navi 0 //survey 1 // 질문 2
				// naviSocket = new Socket("113.198.84.71", 2141);
			
				// naviSocket = new Socket("127.0.0.1",2141);
				ProfessorSocket.setSocket(classinfo.getClassIP(), classinfo.getPort());
				if(ProfessorSocket.getSocket()==null||!(ProfessorSocket.getSocket().isConnected())){
					
					socketThread socketThread = new socketThread(viewUpdateHandler);
					socketThread.start();
					return;
				}
				inputStream = ProfessorSocket.getSocket().getInputStream();
				outputstream = ProfessorSocket.getSocket().getOutputStream();
				byte[] b = new byte[1024];
				Log.d("check", "성공");
				while (true) {
					int cnt = inputStream.read(b);
					if (cnt == -1) {
					
						return;
					}
					String str = new String(b, 0, cnt, "UTF-8");
					String flag = str.substring(0, 1);
					String data = str.substring(1, str.length());
					Gson gson = new Gson();

					if (flag.equals("0")) {
						if (data.equals("start")) {
							MindNode.setNow(MindNode.getRoot());
						} else {

							Type type = new TypeToken<CurrentPositionOfNav>() {
							}.getType();
							CurrentPositionOfNav naviInfo = (CurrentPositionOfNav) gson
									.fromJson(data, type);
							MindNode.setNav(naviInfo);
						}
						Message msg = new Message();
						msg.what =0;
						viewUpdateHandle.sendMessage(msg);

					}
					else if (flag.equals("1")) {
						Type type = new TypeToken<Survey>() {
						}.getType();
						data = new String(data.getBytes(), "UTF-8");
						Log.d("check", data);
						survey = (Survey) gson.fromJson(data, type);
						Message msg = new Message();
						surveyHandler.sendMessage(msg);

					}
					else if (flag.equals("2")) {
						Type type = new TypeToken<TicketInfo>() {
						}.getType();
						data = new String(data.getBytes(), "UTF-8");
						Log.d("check", data);
						TicketInfo ticketInfo = (TicketInfo) gson.fromJson(data, type);
						Ticket ticket = new Ticket(MindNode.getNode(ticketInfo.getTicketPosition()), ticketInfo.getTicketTitle(), ticketInfo.getContents(),ticketInfo.getUserName());
						Message msg = new Message();
						msg.what =0;
						viewUpdateHandle.sendMessage(msg);
						
						
//						ticketActivity.finishActivity(1010);
//						ticketActivity.startActivityForResult(ticketIntent, 1010);
						//asd.finishActivity(1010);
						
						//vContext.startActivityForResult(ticketIntent, 1010);

					}
					else if(flag.equals("3")){
						Message msg = new Message();
						msg.what =1;
						viewUpdateHandle.sendMessage(msg);
						//Toast.makeText(vContext, "교수와 연결이 끊어 졌습니다.", Toast.LENGTH_SHORT).show();
					}

				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				socketThread socketThread = new socketThread(viewUpdateHandler);

				socketThread.start();
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				
				e.printStackTrace();
				Log.d("check", e.toString());
			}

			// naviInputStream = naviSocket.getInputStream();
		}
	}
}
