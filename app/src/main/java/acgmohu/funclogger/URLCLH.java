package acgmohu.funclogger;

import com.saurik.substrate.MS;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * Created by acgmohu on 14-6-24.
 * <p/>
 * HOOK {@link java.net.URL}
 */
public class URLCLH extends CLH {

    private static URLCLH clh;

    public URLCLH() {
        super();
    }

    public static URLCLH getInstance() {
        if (clh == null) {
            clh = new URLCLH();
        }

        return clh;
    }

    private String className = "java.net.URL";

    @Override
    public void classLoaded(Class<?> url) {
        try {
            /**
             * HOOK
             *  URL(String spec)
             *  Creates a new URL instance by parsing spec.
             */
            Constructor constructor = url.getConstructor(String.class);

            MS.hookMethod(url, constructor, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("URL(String spec)");
                    Common.log("url : " + _args[0]);
                    return invoke(_this, _args);
                }
            });

            /**
             *  HOOK
             *  openConnection()
             *  Returns a new connection to the resource referred to by this URL.
             */
            Method method = url.getMethod("openConnection", new Class[]{});
            MS.hookMethod(url, method, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("URL.openConnection()");
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