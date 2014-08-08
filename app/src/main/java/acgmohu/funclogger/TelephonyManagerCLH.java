package acgmohu.funclogger;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;


public class TelephonyManagerCLH extends CLH {

    private final static String className = "android.telephony.TelephonyManager";
    private static TelephonyManagerCLH telephonyManagerCLH;

    public TelephonyManagerCLH() {
        super();
    }

    public static TelephonyManagerCLH getInstance() {
        if (telephonyManagerCLH == null) {
            telephonyManagerCLH = new TelephonyManagerCLH();
        }

        return telephonyManagerCLH;
    }


    @Override
    public void classLoaded(final Class<?> aClass) {

        Method method;
        try {
            method = aClass.getMethod("getDeviceId", new Class[]{});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("TelephonyManager.getDeviceId()");
                    return invoke(_this, _args);
                }
            });

            method = aClass.getMethod("getLine1Number", new Class[]{});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("TelephonyManager.getLine1Number()");
                    return invoke(_this, _args);
                }
            });

            method = aClass.getMethod("getSubscriberId", new Class[]{});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("TelephonyManager.getSubscriberId()");
                    return invoke(_this, _args);
                }
            });

            method = aClass.getMethod("getCellLocation", new Class[]{});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("TelephonyManager.getCellLocation()");
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