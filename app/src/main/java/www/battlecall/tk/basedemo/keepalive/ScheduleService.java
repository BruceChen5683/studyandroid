package www.battlecall.tk.basedemo.keepalive;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by BattleCall on 2018/4/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ScheduleService extends JobService{
	@Override
	public boolean onStartJob(JobParameters params) {
		Intent intent = new Intent(getApplicationContext(),PushService.class);
		startService(intent);
		jobFinished(params,false);
		return false;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		return false;
	}



}
