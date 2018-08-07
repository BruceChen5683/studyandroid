package www.battlecall.tk.basedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ExecCommandActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exec_command);
		
		
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int status = -1;
				try {
					Log.d("cjl", "ExecCommandActivity ---------onClick:      ");
//					Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","ls -l > test.txt"});
//					Process process = Runtime.getRuntime().exec("touch zzztt");
//					printMessage(process.getInputStream());

					Process p;

					p = Runtime.getRuntime().exec("chmod 777"+"/data/data/www.battlecall.tk.basedemo/"+"testpri");
//					p = Runtime.getRuntime().exec("ls -l");
					status = p.waitFor();



				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (status == 0){
					Toast.makeText(ExecCommandActivity.this,"success",Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(ExecCommandActivity.this,"failed",Toast.LENGTH_LONG).show();

				}
			}
		});
	}

	public static void printMessage(final InputStream input) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader = new InputStreamReader(input);
				BufferedReader bf = new BufferedReader(reader);
				String line = null;
				try {
					while ((line = bf.readLine()) != null) {
						Log.d("cjl", "ExecCommandActivity ---------run:      " + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}


