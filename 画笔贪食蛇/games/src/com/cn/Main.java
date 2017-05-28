package com.cn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initListener();

	}

	public void initListener() {
		Button b1 = (Button) this.findViewById(R.id.b1);
		
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toGame();

			}

		});

		Button b2 = (Button) this.findViewById(R.id.b2);
		b2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toAbout();

			}

		});

		Button b3 = (Button) this.findViewById(R.id.b3);
		Button b4 = (Button) this.findViewById(R.id.b4);

	}

	public void toHelp() {

	}

	public void toAbout() {
		setContentView(R.layout.about);
		Button breturn = (Button) this.findViewById(R.id.breturn);

		breturn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toHome();

			}

		});
	}

	public void toHome() {
		setContentView(R.layout.main);
		initListener();
	}

	public void toGame() {
		
		/*
		 * 面板如何调用你自定义的视图对象。 
		 * 1 传入对象的指针。
		 * 2 使用布局文件,这个时候一定要注意；你的那个自定义视图的，构造方法，必须写一个 (Context context, AttributeSet attrs) 
		 */
      this.setContentView(R.layout.game);
      /*
      Button b=new Button(this);
        b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				toHome();
				
			}
        	
        });
        */
	}
}