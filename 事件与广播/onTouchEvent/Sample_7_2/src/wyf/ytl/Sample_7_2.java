package wyf.ytl;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
public class Sample_7_2 extends Activity {
	MyView myView;//自定义View的引用
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate方法
        super.onCreate(savedInstanceState);
        myView = new MyView(this);//初始化自定义的View
        setContentView(myView);//设置当前显示的用户界面
    }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN://按下
			myView.x = (int) event.getX();//改变x坐标
			myView.y = (int) event.getY()-52;//改变y坐标
			myView.postInvalidate();//重绘
			break;
		case MotionEvent.ACTION_MOVE://移动
			myView.x = (int) event.getX();//改变x坐标
			myView.y = (int) event.getY()-52;//改变y坐标
			myView.postInvalidate();//重绘			
			break;
		case MotionEvent.ACTION_UP://抬起
			myView.x = -100;//改变x坐标
			myView.y = -100;//改变y坐标
			myView.postInvalidate();//重绘				
			break;
		}
		return super.onTouchEvent(event);
	}
    class MyView extends View{//自定义的View
    	Paint paint;//画笔
    	int x = 50;//x坐标
    	int y = 50;//y坐标
    	int w = 50;//矩形的宽度
		public MyView(Context context) {//构造器
			super(context);
			paint = new Paint();//初始化画笔
		}
		@Override
		protected void onDraw(Canvas canvas) {//绘制方法
			canvas.drawColor(Color.GRAY);//绘制背景色
			canvas.drawRect(x, y, x+w, y+w, paint);//绘制矩形
			super.onDraw(canvas);
		}
    }
}