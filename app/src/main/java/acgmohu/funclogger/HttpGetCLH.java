package acgmohu.funclogger;

import com.saurik.substrate.MS;

import java.lang.reflect.Constructor;
import java.net.URI;


/**
 * Created by acgmohu on 14-6-24.
 * <p/>
 * HOOK
 * {@link org.apache.http.client.methods.HttpGet}
 */
public class HttpGetCLH extends CLH {

    private static HttpGetCLH classLoadHook;

    private final static String className = "org.apache.http.client.methods.HttpGet";

    public HttpGetCLH() {
        super();
    }

    public static HttpGetCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new HttpGetCLH();
        }

        return classLoadHook;
    }


    @Override
    public void classLoaded(Class<?> aClass) {
        try {
            /**
             * HOOK
             *  HttpGet(URI uri)
             */
            Constructor method = aClass.getConstructor(URI.class);

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("HttpGet(String uri)");
                    Common.log("uri : " + _args[0].toString());
                    return invoke(_this, _args);
                }
            });
            /**
             * HOOK
             *  HttpGet(String uri)
             */
            method = aClass.getConstructor(String.class);

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("HttpGet(String uri)");
                    Common.log("uri : " + _args[0].toString());
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