package acgmohu.funclogger;

import android.app.Notification;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;


/**
 * Created by acgmohu on 14-7-8.
 *
 * Log Notification.
 */
@SuppressWarnings("unchecked")
public class NotificationManagerCLH extends CLH {

    private final static String className = "android.app.NotificationManager";

    private static NotificationManagerCLH classLoadHook;

    public NotificationManagerCLH() {
        super();
    }

    public static NotificationManagerCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new NotificationManagerCLH();
        }

        return classLoadHook;
    }

    @Override
    public void classLoaded(Class<?> notificationManager) {

        try {
            /**
             notify(String tag, int id, Notification notification)
             Post a notification to be shown in the status bar.
             */
            Class[] params = new Class[]{String.class, Integer.class, Notification.class};
            Method method = notificationManager.getMethod("notify", params);

            MS.hookMethod(notificationManager, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("notify(String tag, int id, Notification notification)");
                    Common.log("tag : " + _args[0]);
                    Common.log("id : " + _args[1]);
                    Common.log("notification : " + _args[2]);
                    return invoke(_this, _args);
                }
            });
        } catch (NoSuchMethodException e) {
            Common.log(e.getLocalizedMessage());
        }

    }

    @Override
    String getClassName() {
        return className;
    }
}
