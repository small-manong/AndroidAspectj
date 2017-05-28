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
		 * �����ε������Զ������ͼ���� 
		 * 1 ��������ָ�롣
		 * 2 ʹ�ò����ļ�,���ʱ��һ��Ҫע�⣻����Ǹ��Զ�����ͼ�ģ����췽��������дһ�� (Context context, AttributeSet attrs) 
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