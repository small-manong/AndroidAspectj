package com.cn;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
	public class Node {
		int row, col;
		int nextDir;// �ƶ�����
	}

	public Vector<Node> allBody;
	public Node sHead;// ��ͷ
	public Node food;// ʳ��
	public static final int DIR_UP = 1;
	public static final int DIR_DOWN = 2;
	public static final int DIR_LEFT = 3;
	public static final int DIR_RIGHT = 4;
	public static final int DIR_STOP = 0;
	public java.util.Random rand;

	public MyView(Context context) {
		super(context);
		this.setFocusable(true);
		initGame();
	}

	public void initGame() {
		sHead = new Node();
		rand = new Random();
		sHead.row = rand.nextInt(10);
		sHead.col = rand.nextInt(10);
		food = new Node();
		food.row = rand.nextInt(10);
		food.col = rand.nextInt(10);
		allBody = new Vector<Node>();
	}

	// �����һ�������
	public void newBody(Node lastBody) {
		Node newbody = new Node();
		switch (lastBody.nextDir) {
		case DIR_UP:
			newbody.row = lastBody.row + 1;
			newbody.col = lastBody.col;
			break;
		case DIR_DOWN:
			newbody.row = lastBody.row - 1;
			newbody.col = lastBody.col;
			break;
		case DIR_LEFT:
			newbody.row = lastBody.row;
			newbody.col = lastBody.col + 1;
			break;
		case DIR_RIGHT:
			newbody.row = lastBody.row;
			newbody.col = lastBody.col - 1;
			break;
		}
		newbody.nextDir = lastBody.nextDir;
		allBody.add(newbody);
	}

	public void moveBody() {
		for (int i = allBody.size() - 1; i >= 0; i--) {
			switch (allBody.get(i).nextDir) {
			case DIR_UP:
				allBody.get(i).row--;
				break;
			case DIR_DOWN:
				allBody.get(i).row++;
				break;
			case DIR_LEFT:
				allBody.get(i).col--;
				break;
			case DIR_RIGHT:
				allBody.get(i).col++;
				break;
			}
			if (i > 0)
				allBody.get(i).nextDir = allBody.get(i - 1).nextDir;
			else
				allBody.get(i).nextDir = sHead.nextDir;
		}
	}

	public MyView(Context context, AttributeSet att) {
		super(context, att);
		// ����ǰ��ͼ��Ϊ����
		this.setFocusable(true);
		initGame();
	}

	// �滮�Զ�����ͼ����ʾЧ��
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// �����ͼ��
		Paint p = new Paint();
		p.setARGB(255, 0, 0, 75);
		// ���ƾ���
		canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), p);
		// ����̰ʳ��ͷ
		p.setARGB(255, 255, 0, 0);
		canvas.drawRect(sHead.col * 30, sHead.row * 30, sHead.col * 30 + 30,
				sHead.row * 30 + 30, p);
		// ����ʳ��
		p.setARGB(255, 255, 255, 0);
		canvas.drawRect(food.col * 30, food.row * 30, food.col * 30 + 30,
				food.row * 30 + 30, p);
		// ��������
		p.setARGB(255, 0, 255, 0);
		for (int i = 0; i < allBody.size(); i++) {
			Node nown = allBody.get(i);
			canvas.drawRect(nown.col * 30, nown.row * 30, nown.col * 30 + 30,
					nown.row * 30 + 30, p);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d("KeyDown", "KeyCode:" + keyCode);
		switch (keyCode) {
		case 19:
			sHead.row--;
			sHead.nextDir = DIR_UP;
			break;
		case 20:
			sHead.row++;
			sHead.nextDir = DIR_DOWN;
			break;
		case 21:
			sHead.col--;
			sHead.nextDir = DIR_LEFT;
			break;
		case 22:
			sHead.col++;
			sHead.nextDir = DIR_RIGHT;
			break;
		}
		moveBody();
		if (sHead.row == food.row && sHead.col == food.col) { // ��������
			if (allBody.size() == 0)
				newBody(sHead);
			else
				newBody(allBody.lastElement());

			food.row = rand.nextInt(10);
			food.col = rand.nextInt(10);
		}
		this.invalidate();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// px=(int )event.getX();
		// py=(int )event.getY();
		// Log.d("onTouch", "Touch X:"+px+" Y:"+py);
		this.invalidate();
		return super.onTouchEvent(event);
	}

}