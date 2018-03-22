package www.battlecall.tk.basedemo.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BattleCall on 2018/3/22.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder{
	private SparseArray<View> mViews;
	private View mConvertView;
	private Context mContext;

	public CommonViewHolder(View itemView) {
		super(itemView);
	}

	public CommonViewHolder(Context context, View itemView, ViewGroup parent){
		super(itemView);
		mContext = context;
		mConvertView = itemView;
		mViews = new SparseArray<View>();
	}

	public static CommonViewHolder get(Context context,ViewGroup parent,@IdRes int layoutId){
		View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
		CommonViewHolder holder = new CommonViewHolder(context,itemView,parent);
		return holder;
	}

	public <T extends View> T getView(@IdRes int viewId){
		View view = mViews.get(viewId);
		if(null == view){
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId,view);
		}
		return (T) view;
	}

	public View getConvertView(){
		return mConvertView;
	}
}
