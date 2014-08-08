package acgmohu.funclogger;

import com.saurik.substrate.MS;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;


/**
 * Created by acgmohu on 14-6-24.
 * <p/>
 * HOOK
 * org.apache.http.client.methods.HttpPost
 */
public class HttpPostCLH extends CLH {

    private static HttpPostCLH httpPostCLH;

    public HttpPostCLH() {
        super();
    }

    public static HttpPostCLH getInstance() {
        if (httpPostCLH == null) {
            httpPostCLH = new HttpPostCLH();
        }

        return httpPostCLH;
    }

    private final static String className = "org.apache.http.client.methods.HttpPost";

    @Override
    public void classLoaded(Class<?> aClass) {
        try {
            /**
             * HOOK
             *  HttpPost(URI uri)
             */
            Constructor constructor = aClass.getConstructor(URI.class);

            MS.hookMethod(aClass, constructor, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("HttpPost(String uri)");
                    Common.log("uri : " + _args[0].toString());
                    return invoke(_this, _args);
                }
            });
            /**
             * HOOK
             *  HttpPost(String uri)
             */
            constructor = aClass.getConstructor(String.class);

            MS.hookMethod(aClass, constructor, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("HttpPost(String uri)");
                    Common.log("uri : " + _args[0].toString());
                    return invoke(_this, _args);
                }
            });

            Method method = aClass.getMethod("setHeader", new Class[]{String.class, String.class});

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                @SuppressWarnings("unchecked")
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("HttpPost(String uri)");
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