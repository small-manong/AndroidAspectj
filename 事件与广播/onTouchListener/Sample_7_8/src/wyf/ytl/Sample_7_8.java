package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
public class Sample_7_8 extends Activity {   
	final static int WRAP_CONTENT=-2;//表示WRAP_CONTENT的常量
	final static int X_MODIFY=4;//在非全屏模式下X坐标的修正值
	final static int Y_MODIFY=52;//在非全屏模式下Y坐标的修正值
	int xSpan;//在触控笔点击按钮的情况下相对于按钮自己坐标系的
	int ySpan;//X,Y位置
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//设置当前的用户界面  
        Button bok=(Button)this.findViewById(R.id.Button01);
        bok.setOnTouchListener(
        		new OnTouchListener(){
					public boolean onTouch(View view, MotionEvent event) {					
						switch(event.getAction()){
						 case MotionEvent.ACTION_DOWN://触控笔按下
							 xSpan=(int)event.getX();
							 ySpan=(int)event.getY();
						 break;
						 case MotionEvent.ACTION_MOVE://触控笔移动
							 Button bok=(Button)findViewById(R.id.Button01);
							 //让按钮随着触控笔的移动一起移动
							 ViewGroup.LayoutParams  lp=
								new AbsoluteLayout.LayoutParams(
										WRAP_CONTENT, 
										WRAP_CONTENT, 
										(int)event.getRawX()-xSpan-X_MODIFY, 
										(int)event.getRawY()-ySpan-Y_MODIFY
								) ;					
							 bok.setLayoutParams(lp);
						break;
						}											
						return true;
					}        			
        		}
        );
    }
    public boolean onKeyDown (int keyCode, KeyEvent event){//键盘键按下的方法
    	Button bok=(Button)this.findViewById(R.id.Button01);
    	bok.setText(keyCode+" Down");    	
    	return true;
    }
    public boolean onKeyUp (int keyCode, KeyEvent event){//键盘键抬起的方法
    	Button bok=(Button)this.findViewById(R.id.Button01);
    	bok.setText(keyCode+" Up");    	
    	return true;
    }
    public boolean onTouchEvent (MotionEvent event){//让按钮随着触控笔的移动一起移动
    	Button bok=(Button)this.findViewById(R.id.Button01);
    	ViewGroup.LayoutParams  lp=
			new AbsoluteLayout.LayoutParams(
					WRAP_CONTENT, WRAP_CONTENT, 
					(int)event.getRawX()-xSpan-X_MODIFY, 
					(int)event.getRawY()-ySpan-Y_MODIFY
			) ;					
		bok.setLayoutParams(lp);
    	return true;
    }
}