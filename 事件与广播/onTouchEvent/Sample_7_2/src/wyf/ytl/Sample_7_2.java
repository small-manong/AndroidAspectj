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
	MyView myView;//�Զ���View������
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate����
        super.onCreate(savedInstanceState);
        myView = new MyView(this);//��ʼ���Զ����View
        setContentView(myView);//���õ�ǰ��ʾ���û�����
    }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN://����
			myView.x = (int) event.getX();//�ı�x����
			myView.y = (int) event.getY()-52;//�ı�y����
			myView.postInvalidate();//�ػ�
			break;
		case MotionEvent.ACTION_MOVE://�ƶ�
			myView.x = (int) event.getX();//�ı�x����
			myView.y = (int) event.getY()-52;//�ı�y����
			myView.postInvalidate();//�ػ�			
			break;
		case MotionEvent.ACTION_UP://̧��
			myView.x = -100;//�ı�x����
			myView.y = -100;//�ı�y����
			myView.postInvalidate();//�ػ�				
			break;
		}
		return super.onTouchEvent(event);
	}
    class MyView extends View{//�Զ����View
    	Paint paint;//����
    	int x = 50;//x����
    	int y = 50;//y����
    	int w = 50;//���εĿ��
		public MyView(Context context) {//������
			super(context);
			paint = new Paint();//��ʼ������
		}
		@Override
		protected void onDraw(Canvas canvas) {//���Ʒ���
			canvas.drawColor(Color.GRAY);//���Ʊ���ɫ
			canvas.drawRect(x, y, x+w, y+w, paint);//���ƾ���
			super.onDraw(canvas);
		}
    }
}