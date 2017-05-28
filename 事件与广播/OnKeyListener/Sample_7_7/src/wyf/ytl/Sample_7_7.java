package wyf.ytl;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ImageButton;
import android.widget.TextView;
public class Sample_7_7 extends Activity implements OnKeyListener,OnClickListener{
	ImageButton[] imageButtons = new ImageButton[4];//声明按钮数组
	TextView myTextView;//声明TextView的引用
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        myTextView = (TextView) this.findViewById(R.id.myTextView);//得到myTextView的引用
        imageButtons[0] = (ImageButton) this.findViewById(R.id.button01);//得到button01的引用
        imageButtons[1] = (ImageButton) this.findViewById(R.id.button02);//得到button02的引用
        imageButtons[2] = (ImageButton) this.findViewById(R.id.button03);//得到button03的引用
        imageButtons[3] = (ImageButton) this.findViewById(R.id.button04);//得到button04的引用
        for(ImageButton imageButton : imageButtons){
        	imageButton.setOnClickListener(this);//添加单击监听
        	imageButton.setOnKeyListener(this);//添加键盘监听
        }
    }
	@Override
	public void onClick(View v) {//实现了接口中的方法
		if(v.getId() == R.id.button01){//改变的是button01时
			myTextView.setText("您点击了按钮A！");
		}else if(v.getId() == R.id.button02){//改变的是button02时
			myTextView.setText("您点击了按钮B！");
		}else if(v.getId() == R.id.button03){//改变的是button03时
			myTextView.setText("您点击了按钮C！");
		}else if(v.getId() == R.id.button04){//改变的是button04时
			myTextView.setText("您点击了按钮D！");
		}else{//其他情况
			myTextView.setText("");
		}
	}    
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {//键盘监听
		switch(keyCode){//判断键盘码
		case 29://按键A
			imageButtons[0].performClick();//模拟单击
			imageButtons[0].requestFocus();//尝试使之获得焦点
			break;
		case 30://按键B
			imageButtons[1].performClick();//模拟单击
			imageButtons[1].requestFocus();//尝试使之获得焦点
			break;
		case 31://按键C
			imageButtons[2].performClick();//模拟单击
			imageButtons[2].requestFocus();//尝试使之获得焦点
			break;
		case 32://按键D
			imageButtons[3].performClick();//模拟单击
			imageButtons[3].requestFocus();//尝试使之获得焦点
			break;
		}
		return false;
	}
}