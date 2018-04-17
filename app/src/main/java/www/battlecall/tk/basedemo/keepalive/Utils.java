package www.battlecall.tk.basedemo.keepalive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by BattleCall on 2018/4/13.
 */

public class Utils {
	private static final int DEFAULT_OOM_ADJ = 16;

	public static int getProcessOomAdj(int pid) {
		int oom_adj = DEFAULT_OOM_ADJ;
		File file = new File("proc/" + pid + "/oom_adj/");
		BufferedReader br = null;
		String s;
		try {
			br = new BufferedReader(new FileReader(file));
			if ((s = br.readLine()) != null) {
				oom_adj = Integer.valueOf(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oom_adj;
	}
}
