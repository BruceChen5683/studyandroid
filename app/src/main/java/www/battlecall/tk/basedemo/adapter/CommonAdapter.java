package www.battlecall.tk.basedemo.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by BattleCall on 2018/3/22.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> implements View.OnClickListener{
	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mLayoutInflater;
	private static final String TAG = CommonAdapter.class.getSimpleName();

	private OnItemClickLister mOnItemClickListener = null;


	public CommonAdapter(Context context,@IdRes int layoutId,List<T> datas){
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mLayoutId = layoutId;
		mDatas = datas;
	}

	@NonNull
	@Override
	public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		CommonViewHolder viewHolder = CommonViewHolder.get(mContext,parent,mLayoutId);
		viewHolder.getConvertView().setOnClickListener(this);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
		holder.itemView.setTag(position);
		convert(holder,mDatas.get(position));
	}

	public abstract void convert(CommonViewHolder holder, T t);

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public void onClick(View v) {
		if(mOnItemClickListener != null){
			mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
		}
	}

	public void setOnItemClickLister(OnItemClickLister listener){
		this.mOnItemClickListener = listener;
	}

	public interface OnItemClickLister{
		void onItemClick(View view,int position);
	}
}

