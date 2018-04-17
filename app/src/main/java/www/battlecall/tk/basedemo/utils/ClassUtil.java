package www.battlecall.tk.basedemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by BattleCall on 2018/3/22.
 */

public class ClassUtil {
	private final static String TAG = ClassUtil.class.getSimpleName();
	private ClassUtil(){}

	public final static List<Class> getActivitiesClass(Context context, String packageName, List<Class> execludeList){
		List<Class> classList = new ArrayList<Class>();

		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			if (packageInfo.activities != null){
				for (ActivityInfo activityInfo:packageInfo.activities){
					Class clazz;
					clazz = Class.forName(activityInfo.name);
					if(Activity.class.isAssignableFrom(clazz)){
						classList.add(clazz);
					}
				}
			}
		}catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(execludeList != null){
			classList.removeAll(execludeList);
		}
		Log.d(TAG, "Filter out, left "+ classList.size()  +" activity," + Arrays.toString(classList.toArray()));
		return classList;
	}

}
