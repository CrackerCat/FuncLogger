package acgmohu.funclogger;


import com.saurik.substrate.MS;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by acgmohu on 14-7-30.
 * <p/>
 * HOOK
 * java.lang.Runtime
 */
@SuppressWarnings("unchecked")
public class ProcessBuilderCLH extends CLH {

    private final static String className = "java.lang.ProcessBuilder";
    private static ProcessBuilderCLH classLoadHook;

    public ProcessBuilderCLH() {
        super();
    }

    public static ProcessBuilderCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new ProcessBuilderCLH();
        }

        return classLoadHook;
    }

    @Override
    public void classLoaded(Class<?> aClass) {
        try {

            /**
             * HOOK
             *  command(List<String> command)
             *      Changes the program and arguments of this process builder.
             */
            Class[] params = new Class[]{List.class};
            Method method = aClass.getMethod("exec", params);

            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("ProcessBuilder.command(List<String> command)");
                    List<String> command = (List) _args[0];
                    for (String str : command) {
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
