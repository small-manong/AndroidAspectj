package wyf.ytl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sample_7_6 extends Activity implements OnFocusChangeListener{
	TextView myTextView;
	ImageButton[] imageButtons = new ImageButton[4];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myTextView = (TextView) this.findViewById(R.id.myTextView);//得到myTextView的引用
        imageButtons[0] = (ImageButton) this.findViewById(R.id.button01);//得到button01的引用
        imageButtons[1] = (ImageButton) this.findViewById(R.id.button02);//得到button02的引用
        imageButtons[2] = (ImageButton) this.findViewById(R.id.button03);//得到button03的引用
        imageButtons[3] = (ImageButton) this.findViewById(R.id.button04);//得到button04的引用
        for(ImageButton imageButton : imageButtons){
        	imageButton.setOnFocusChangeListener(this);//添加监听
        }
    }
	@Override
	public void onFocusChange(View v, boolean hasFocus) {//实现了接口中的方法
		if(v.getId() == R.id.button01){//改变的是button01时
			myTextView.setText("您选中了羊！");
		}else if(v.getId() == R.id.button02){//改变的是button02时
			myTextView.setText("您选中了猪！");
		}else if(v.getId() == R.id.button03){//改变的是button03时
			myTextView.setText("您选中了牛！");
		}else if(v.getId() == R.id.button04){//改变的是button04时
			myTextView.setText("您选中了鼠！");
		}else{//其他情况
			myTextView.setText("");
		}
	}
}