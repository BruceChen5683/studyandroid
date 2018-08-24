package www.battlecall.tk.basedemo.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by BattleCall on 2018/8/24.
 */

public class OpHandlerThread extends HandlerThread implements Handler.Callback{
	private Handler uiHandler;
	private Handler workHandler;
	public static final int OP_1 = 1 ;
	private int c = 0;

	public void setUiHandler(Handler uiHandler) {
		this.uiHandler = uiHandler;
	}

	public Handler getWorkHandler() {
		return workHandler;
	}

	public OpHandlerThread(String name) {
		super(name);
	}

	public OpHandlerThread(String name, int priority) {
		super(name, priority);
	}

	@Override
	protected void onLooperPrepared() {
		Log.d("cjl", "OpHandlerThread ---------onLooperPrepared:      ");
		workHandler = new Handler(getLooper(),this){
			@Override
			public void handleMessage(Message msg) {
				Log.d("cjl", "workHandler OpHandlerThread ---------handleMessage:      "+msg.what);
			}
		};
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what){
			case OP_1:
				Log.d("cjl", "callback OpHandlerThread ---------handleMessage:      ");
				//do sth
				c++;
				Message message = Message.obtain(null, OP_1);
				uiHandler.sendMessage(message);
				break;
			default:
				break;
		}
		return true;
		//false
//		01-01 00:33:14.650 5426-5426/www.battlecall.tk.basedemo D/cjl: OpHandlerThread ---------op1:
//		01-01 00:33:14.660 5426-5858/www.battlecall.tk.basedemo D/cjl: callback OpHandlerThread ---------handleMessage:
//		01-01 00:33:14.670 5426-5858/www.battlecall.tk.basedemo D/cjl: workHandler OpHandlerThread ---------handleMessage:      1
//		01-01 00:33:14.800 5426-5426/www.battlecall.tk.basedemo D/cjl: MyHandler ---------handleMessage:      1

		//true
//		01-01 00:34:34.360 6777-7193/www.battlecall.tk.basedemo D/cjl: OpHandlerThread ---------onLooperPrepared:
//		01-01 00:34:39.390 6777-6777/www.battlecall.tk.basedemo D/cjl: OpHandlerThread ---------op1:
//		01-01 00:34:39.390 6777-7193/www.battlecall.tk.basedemo D/cjl: callback OpHandlerThread ---------handleMessage:
//		01-01 00:34:39.620 6777-6777/www.battlecall.tk.basedemo D/cjl: MyHandler ---------handleMessage:      1
	}

	public void op1(){
		Log.d("cjl", "OpHandlerThread ---------op1:      ");
		Message msg = Message.obtain(null, OP_1);
		workHandler.sendMessage(msg);
	}

	//TODO callback返回true时，不执行handler里的handleMessage 否则执行

}
