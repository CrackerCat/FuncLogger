package acgmohu.funclogger;


import android.app.PendingIntent;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Created by acgmohu on 14-6-20.
 *
 * HOOK android.telephony.SmsManager
 */
public class SmsManagerCLH extends CLH {

    private static SmsManagerCLH classLoadHook;

    public SmsManagerCLH() {
        super();
    }

    public static SmsManagerCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new SmsManagerCLH();
        }

        return classLoadHook;
    }

    private final static String className = "android.telephony.SmsManager";

    @Override
    public void classLoaded(Class<?> SmsManager) {
        //code to modify the class when loaded

        /**
         * HOOK
         *  sendMultipartTextMessage(String destinationAddress, String scAddress,
         *                          ArrayList<String> parts, ArrayList<PendingIntent> sentIntents,
         *                          ArrayList<PendingIntent> deliveryIntents)
         */
        Method sendMultipartTextMessage;
        try {
            sendMultipartTextMessage = SmsManager.getMethod("sendMultipartTextMessage",
                    new Class[]{String.class, String.class,
                            ArrayList.class, ArrayList.class, ArrayList.class}
            );

            MS.hookMethod(SmsManager, sendMultipartTextMessage, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("sendMultipartTextMessage");
                    Common.log("destinationAddress :" + _args[0]);
                    Common.log("scAddress:" + _args[1]);
                    Common.log("destinationPort:" + _args[2]);
                    Common.log("data:" + _args[3]);
                    return invoke(_this, _args);
                }
            });
        } catch (NoSuchMethodException e) {
            Common.log(e.getLocalizedMessage());
        }

        /**
         * HOOK
         * sendTextMessage(String destinationAddress, String scAddress, String text,
         *                          PendingIntent sentIntent, PendingIntent deliveryIntent)
         */
        Method sendTextMessage;
        try {
            sendTextMessage = SmsManager.getMethod("sendTextMessage",
                    new Class[]{String.class, String.class, String.class,
                            PendingIntent.class, PendingIntent.class}
            );

            MS.hookMethod(SmsManager, sendTextMessage, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("sendTextMessage");
                    Common.log("destinationAddress :" + _args[0]);
                    Common.log("scAddress:" + _args[1]);
                    Common.log("text:" + _args[2]);
                    return invoke(_this, _args);
                }
            });
        } catch (NoSuchMethodException e) {
            Common.log(e.getLocalizedMessage());
        }

        /**
         * HOOK
         * sendDataMessage(String destinationAddress, String scAddress, short destinationPort,
         *                  byte[] data, PendingIntent sentIntent, PendingIntent deliveryIntent)
         */
        Method sendDataMessage;
        try {
            sendDataMessage = SmsManager.getMethod("sendDataMessage",
                    new Class[]{String.class, String.class, String.class,
                            PendingIntent.class, PendingIntent.class}
            );

            MS.hookMethod(SmsManager, sendDataMessage, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("sendDataMessage");
                    Common.log("destinationAddress :" + _args[0]);
                    Common.log("scAddress:" + _args[1]);
                    Common.log("destinationPort:" + _args[2]);
                    Common.log("data:" + _args[3]);
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
