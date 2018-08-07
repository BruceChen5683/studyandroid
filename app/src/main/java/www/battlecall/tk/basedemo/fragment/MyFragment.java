package www.battlecall.tk.basedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.battlecall.tk.basedemo.R;

/**
 * Created by BattleCall on 2018/8/3.
 */

public class MyFragment extends Fragment{
	@Override
	public void onAttach(Context context) {
		Log.d("cjl", "MyFragment ---------onAttach:      ");
		super.onAttach(context);
	}

	@Override
	public void onStart() {
		Log.d("cjl", "MyFragment ---------onStart:      ");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.d("cjl", "MyFragment ---------onResume:      ");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d("cjl", "MyFragment ---------onPause:      ");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d("cjl", "MyFragment ---------onStop:      ");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.d("cjl", "MyFragment ---------onDestroyView:      ");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.d("cjl", "MyFragment ---------onDestroy:      ");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.d("cjl", "MyFragment ---------onDetach:      ");
		super.onDetach();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Log.d("cjl", "MyFragment ---------onCreate:      ");
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d("cjl", "MyFragment ---------onCreateView:      ");
		View view = inflater.inflate(R.layout.frament_my,null);
		return view;
//		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
