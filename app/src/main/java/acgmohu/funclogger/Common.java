package acgmohu.funclogger;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {
    public static final String TAG = "FL_HOOK";

    public static String getCurrentTime() {
        Long currentTimeMillis = System.currentTimeMillis();
        return formatCurrentTime(currentTimeMillis);
    }

    private static String formatCurrentTime(long currentTimeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMdd-HHmmss", Locale.CHINA);
        Date currentDate = new Date(currentTimeMillis);
        return simpleDateFormat.format(currentDate);
    }


    public static void log(Object obj) {
        Log.d(TAG, obj.toString());
    }
}
