package com.cn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertDialogActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new Builder(
						AlertDialogActivity.this).setTitle("Àý×Ó")
						.setMessage("·ÃÎÊÍøÕ¾Âð£¿").setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(Intent.ACTION_VIEW,
										Uri.parse("http://www.baidu.com"));
								startActivity(intent);
							}

							public void onClick(View v) {
								
							}
						}).setNegativeButton("NO", new  DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}

							public void onClick(View v) {
								
							}
						});

				AlertDialog alert = builder.create();
				alert.show();

			}
		});
	}
}