package acgmohu.funclogger;


import com.saurik.substrate.MS;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.lang.reflect.Method;


/**
 * Created by acgmohu on 14-6-20.
 * <p/>
 * HOOK
 * {@link org.apache.http.impl.client.AbstractHttpClient}
 */
public class AbstractHttpClientCLH extends CLH {

    private final static String className = "org.apache.http.impl.client.AbstractHttpClient";
    private static AbstractHttpClientCLH classLoadHook;

    public AbstractHttpClientCLH() {
        super();
    }

    public static AbstractHttpClientCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new AbstractHttpClientCLH();
        }

        return classLoadHook;
    }


    @Override
    public void classLoaded(Class<?> aClass) {
        try {
            /*
                https://github.com/android/platform_external_apache-http/blob/master/src/org/apache/http/impl/client/AbstractHttpClient.java
                non-javadoc, see interface HttpClient
                public final HttpResponse execute(HttpHost target, HttpRequest request,
                    HttpContext context)
            */
            Method method = aClass.getMethod("execute",
                    new Class[]{HttpHost.class, HttpRequest.class, HttpContext.class});

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("AbstractHttpClient.execute(HttpHost target, HttpRequest request, HttpContext context)");
                    Common.log("target : " + _args[0]);
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
