package www.battlecall.tk.basedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.battlecall.tk.basedemo.adapter.CommonAdapter;
import www.battlecall.tk.basedemo.adapter.CommonViewHolder;
import www.battlecall.tk.basedemo.utils.ClassUtil;

public class MainActivity extends AppCompatActivity {
	private final static String TAG = MainActivity.class.getSimpleName();
	private RecyclerView recyclerView;
	private CommonAdapter<String> mAdapter;
	private List<String> datas = new ArrayList<String>();
	private List<Class> activities = new ArrayList<Class>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.rv);

		getData();

		Log.d(TAG, "onCreate: "+datas.size());

		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		mAdapter = new CommonAdapter<String>(this,R.layout.item_list,datas){

			@Override
			public void convert(CommonViewHolder holder, String s) {
				TextView tv = holder.getView(R.id.item_tv);
				tv.setText(s);
			}
		};

		recyclerView.setAdapter(mAdapter);

		mAdapter.setOnItemClickLister(new CommonAdapter.OnItemClickLister() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,activities.get(position));
				startActivity(intent);
			}
		});

	}

	private void getData() {
		List<Class> execudeList = new ArrayList<Class>();
		try {
			execudeList.add(Class.forName("www.battlecall.tk.basedemo.MainActivity"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		activities = ClassUtil.getActivitiesClass(this,this.getPackageName(),execudeList);
		for (Class clazz : activities){
			datas.add(clazz.getSimpleName());
		}
	}
}
