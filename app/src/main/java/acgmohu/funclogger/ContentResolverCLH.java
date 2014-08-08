package acgmohu.funclogger;


import android.content.ContentValues;
import android.net.Uri;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;


@SuppressWarnings("unchecked")
public class ContentResolverCLH extends CLH {

    private final static String className = "android.content.ContentResolver";
    private static ContentResolverCLH classLoadHook;


    public static ContentResolverCLH getInstance() {
        if (classLoadHook == null) {
            classLoadHook = new ContentResolverCLH();
        }

        return classLoadHook;
    }


    @Override
    public void classLoaded(Class<?> aClass) {

        Method method;

        try {
            /**
             * delete(Uri url, String where, String[] selectionArgs)
             * Deletes row(s) specified by a content URI.
             */
            method = aClass.getMethod("delete", new Class[]{Uri.class, String.class, String[].class});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("ContentResolver.delete(Uri url, String where, String[] selectionArgs)");
                    Common.log("uri :" + _args[0]);
                    Common.log("where:" + _args[1]);
                    Common.log("selectionArgs :" + _args[2]);
                    return invoke(_this, _args);
                }
            });

            /**
             * insert(Uri url, ContentValues values)
             * Inserts a row into a table at the given URL.
             */
            method = aClass.getMethod("insert", new Class[]{Uri.class, ContentValues.class});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("ContentResolver.insert(Uri url, ContentValues values)");
                    Common.log("uri :" + _args[0]);
                    Common.log("values :" + _args[1]);
                    return invoke(_this, _args);
                }
            });

            /**
             *  query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
             *    Query the given URI, returning a Cursor over the result set.
             */
            method = aClass.getMethod("query",
                    new Class[]{Uri.class, String[].class, String.class, String[].class, String.class});
            MS.hookMethod(aClass, method, new MS.MethodAlteration() {
                public Object invoked(Object _this, Object... _args) throws Throwable {
                    Common.log("ContentResolver.query(Uri uri, String[] projection, " +
                            "String selection, String[] selectionArgs, String sortOrder)");
                    Common.log("uri :" + _args[0]);
                    Common.log("selection:" + _args[2]);
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
