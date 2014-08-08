package acgmohu.funclogger;


import android.content.ContentValues;
import android.media.MediaSyncEvent;
import android.net.Uri;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;


/**
 * Hook
 * android.media.AudioRecord
 */
@SuppressWarnings("unchecked")
public class AudioRecordCLH extends CLH {

    private final static String className = "android.media.AudioRecord";
    private static AudioRecordCLH classLoadHook;


    public static AudioRecordCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new AudioRecordCLH();
        }

        return classLoadHook;
    }


    @Override
    public void classLoaded(Class<?> aClass) {

        Method method;

        try {
            /**
             *  startRecording()
             *      Starts recording from the AudioRecord instance.
             */
            method = aClass.getMethod("startRecording", new Class[]{});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("AudioRecord.startRecording()");
                    return invoke(_this, _args);
                }
            });

            /**
             *   startRecording(MediaSyncEvent syncEvent)
             *      Starts recording from the AudioRecord instance when the specified
             *      synchronization event occurs on the specified audio session.
             */
            method = aClass.getMethod("startRecording", new Class[]{MediaSyncEvent.class});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("AudioRecord.startRecording(MediaSyncEvent syncEvent)");
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
