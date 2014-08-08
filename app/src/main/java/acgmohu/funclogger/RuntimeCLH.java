package acgmohu.funclogger;


import com.saurik.substrate.MS;

import java.io.File;
import java.lang.reflect.Method;


/**
 * Created by acgmohu on 14-6-20.
 * <p/>
 * HOOK
 * java.lang.Runtime
 */
@SuppressWarnings("unchecked")
public class RuntimeCLH extends CLH {

    private final static String className = "java.lang.Runtime";
    private static RuntimeCLH classLoadHook;

    public RuntimeCLH() {
        super();
    }

    public static RuntimeCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new RuntimeCLH();
        }

        return classLoadHook;
    }

    @Override
    public void classLoaded(Class<?> aClass) {
        try {

            /**
             * HOOK
             *   exec(String[] progArray, String[] envp, File directory)
             *      Executes the specified command and its arguments in a separate native process.
             */
            Class[] params = new Class[]{String[].class, String[].class, File.class};
            Method method = aClass.getMethod("exec", params);

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("exec(String[] progArray, String[] envp, File directory)");
                    String[] progArray = (String[]) _args[0];
                    for (String str : progArray) {
                        Common.log("prog :" + str);
                    }

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
