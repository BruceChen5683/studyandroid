package www.battlecall.tk.basedemo.moretvmid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DeviceUtils {

    /**
     * @return 序列号
     */
    public static String getSN() {
        return android.os.Build.SERIAL;
    }

    public static String getMacAddress() {
    	String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
 
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        if(macSerial == null || macSerial.isEmpty()){
        	macSerial = "00:00:00:00:00:00";//广告请求mac required
        }
        return macSerial;
    }

    public static String getWiFiMacAddr(Context context) {
        return ""; //HardwareInfoManager.getInstance().getWiFiMacAddr(context);
    }

    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    public static String getModel() {
    	String model = android.os.Build.MODEL.replace(" ", "");
        if (model == null || model.equalsIgnoreCase("<NULL>")) {
            model = "";
        }
        return model;
    }

    /**
     * 固件版本号，也就是OTA版本号.
     * 
     * @return
     */
    public static String getFirmwareVersion() {
        return android.os.Build.ID + "." + android.os.Build.VERSION.INCREMENTAL;
    }

    @SuppressWarnings("deprecation")
    public static Drawable getTimerBackground() {
        InputStream stream = DeviceUtils.class.getResourceAsStream("/www/battlecall/tk/basedemo/moretvmid/adsystem_tips_bg.png");
        BitmapDrawable drawable = new BitmapDrawable(stream);
        return drawable;
    }

    @SuppressWarnings("deprecation")
    public static Drawable getSkipTipsIcon() {
        InputStream stream = DeviceUtils.class.getResourceAsStream("/www/battlecall/tk/basedemo/moretvmid/ad_icon_skip.png");
        BitmapDrawable drawable = new BitmapDrawable(stream);
        return drawable;
    }

    @SuppressWarnings("deprecation")
	public static Drawable getDetailTipsIcon() {
        InputStream stream = DeviceUtils.class.getResourceAsStream("/www/battlecall/tk/basedemo/moretvmid/ad_icon_detail.png");
        BitmapDrawable drawable = new BitmapDrawable(stream);
        return drawable;
    }
    
    /**
     * 获取硬件屏幕分辨率
     * 
     * @param context
     * @return
     */
//    public static String getPanelResolution() {
//        try {
//            int width = WtvApiPictureManager.getInstance().getPanelWidth();
//            int height = WtvApiPictureManager.getInstance().getPanelHeight();
//            return width + "x" + height;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "1920x1080";
//    }
    
}
