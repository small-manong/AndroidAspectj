package com.amaker.mp.fileupload;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.amaker.mp.R;
import com.amaker.mp.R.id;
import com.amaker.mp.R.layout;
import com.amaker.util.HttpUtil;

public class UploadPicActivity extends Activity {

	private Button submitBtn, selectBtn;
	private EditText myEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_pic);

		myEditText = (EditText) findViewById(R.id.picPathEditText);

		submitBtn = (Button) findViewById(R.id.submitPicButton);
		selectBtn = (Button) findViewById(R.id.selectButton);

		selectBtn.setOnClickListener(new OnClickListener() {
		 
			public void onClick(View v) {
				Intent intent = new Intent(UploadPicActivity.this,FileExplorerActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		submitBtn.setOnClickListener(new OnClickListener() {
		 
			public void onClick(View v) {
				String fileName = myEditText.getText().toString();
				if(validate(fileName)){
					showAlert("��ЧͼƬ�ļ���");
				}else{
					if(uploadFile(fileName).equals("1")){
						showAlert("�ϴ��ɹ���");
					}else{
						showAlert("�ϴ�ʧ�ܣ�");
					}
				}
			}
		});
	}
	
	
	private String uploadFile(String fileName){
		String urlStr = HttpUtil.BASE_URL+"servlet/UploadPicServlet";
		String newName="image.jpg";
		String end = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    try
	    {
	      URL url =new URL(urlStr);
	      HttpURLConnection con=(HttpURLConnection)url.openConnection();
	      /* ����Input��Output����ʹ��Cache */
	      con.setDoInput(true);
	      con.setDoOutput(true);
	      con.setUseCaches(false);
	      /* ���ô��͵�method=POST */
	      con.setRequestMethod("POST");
	      /* setRequestProperty */
	      con.setRequestProperty("Connection", "Keep-Alive");
	      con.setRequestProperty("Charset", "UTF-8");
	      con.setRequestProperty("Content-Type",
	                         "multipart/form-data;boundary="+boundary);
	      
	      
	      /* ����DataOutputStream */
	      DataOutputStream ds = 
	        new DataOutputStream(con.getOutputStream());
	      ds.writeBytes(twoHyphens + boundary + end);
	      ds.writeBytes("Content-Disposition: form-data; " +
	                    "name=\"file1\";filename=\"" +
	                    newName +"\"" + end);
	      ds.writeBytes(end);   

	      /* ȡ���ļ���FileInputStream */
	      FileInputStream fStream = new FileInputStream(fileName);
	      /* ����ÿ��д��1024bytes */
	      int bufferSize = 1024;
	      byte[] buffer = new byte[bufferSize];

	      int length = -1;
	      /* ���ļ���ȡ������������ */
	      while((length = fStream.read(buffer)) != -1)
	      {
	        /* ������д��DataOutputStream�� */
	        ds.write(buffer, 0, length);
	      }
	      ds.writeBytes(end);
	      ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

	      /* close streams */
	      fStream.close();
	      ds.flush();

	      /* ȡ��Response���� */
	      InputStream is = con.getInputStream();
	      int ch;
	      StringBuffer b =new StringBuffer();
	      while( ( ch = is.read() ) != -1 )
	      {
	        b.append( (char)ch );
	      }
	    
	      ds.close();
	      
	      return b.toString().trim();
	    }
	    catch(Exception e)
	    {
	     // showAlert(""+e);
	      return ""+e;
	    }
	    
	   // return null;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==1){
			String path = data.getStringExtra("filePath");
			myEditText.setText(path);
		}
	}
	
	private boolean validate(String fileName){
		int idx = fileName.indexOf(".");
		String subfix = fileName.substring(idx);
		if(fileName.equals(""))return false;
		if(!subfix.equals("jpg")||!subfix.equals("png")||!subfix.equals("jpeg")){
			return false;
		}else{
			return true;
		}
	}
	
	private void showAlert(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}

}