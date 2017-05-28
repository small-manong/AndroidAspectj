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
	final static int WRAP_CONTENT=-2;//��ʾWRAP_CONTENT�ĳ���
	final static int X_MODIFY=4;//�ڷ�ȫ��ģʽ��X���������ֵ
	final static int Y_MODIFY=52;//�ڷ�ȫ��ģʽ��Y���������ֵ
	int xSpan;//�ڴ��رʵ����ť�����������ڰ�ť�Լ�����ϵ��
	int ySpan;//X,Yλ��
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//���õ�ǰ���û�����  
        Button bok=(Button)this.findViewById(R.id.Button01);
        bok.setOnTouchListener(
        		new OnTouchListener(){
					public boolean onTouch(View view, MotionEvent event) {					
						switch(event.getAction()){
						 case MotionEvent.ACTION_DOWN://���رʰ���
							 xSpan=(int)event.getX();
							 ySpan=(int)event.getY();
						 break;
						 case MotionEvent.ACTION_MOVE://���ر��ƶ�
							 Button bok=(Button)findViewById(R.id.Button01);
							 //�ð�ť���Ŵ��رʵ��ƶ�һ���ƶ�
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
    public boolean onKeyDown (int keyCode, KeyEvent event){//���̼����µķ���
    	Button bok=(Button)this.findViewById(R.id.Button01);
    	bok.setText(keyCode+" Down");    	
    	return true;
    }
    public boolean onKeyUp (int keyCode, KeyEvent event){//���̼�̧��ķ���
    	Button bok=(Button)this.findViewById(R.id.Button01);
    	bok.setText(keyCode+" Up");    	
    	return true;
    }
    public boolean onTouchEvent (MotionEvent event){//�ð�ť���Ŵ��رʵ��ƶ�һ���ƶ�
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