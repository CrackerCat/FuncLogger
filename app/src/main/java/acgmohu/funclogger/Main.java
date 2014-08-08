package acgmohu.funclogger;

import com.saurik.substrate.MS;

/**
 * Created by acgmohu on 14-6-20.
 * <p/>
 * CS 模式下, 只能大体上去了解一个应用的运行情况,不能去HOOK Class.forName(), System.loadLibrary() 这种函数.
 * 否则, 系统运行会缓慢, 应用无法正常加载. <p/>
 * 如需要单独对某个包进行HOOK, 则使用 Xposed, 这样可以得到更加细致的结果.
 */
@SuppressWarnings("UnusedDeclaration")
public class Main {
    static void initialize() {

        // Hook SMS
        hook(SmsManagerCLH.getInstance());

        // Hook Telephone
        hook(TelephonyManagerCLH.getInstance());

        // Hook Content Resolver
        hook(ContentResolverCLH.getInstance());

        // Hook Network
        hook(HttpGetCLH.getInstance());
        hook(HttpPostCLH.getInstance());
        hook(AbstractHttpClientCLH.getInstance());
        hook(URLCLH.getInstance());

        // Notification
        hook(NotificationManagerCLH.getInstance());

        // command
        hook(RuntimeCLH.getInstance());
        hook(ProcessBuilderCLH.getInstance());

        // Audio Record
        hook(AudioRecordCLH.getInstance());
    }

    static void hook(CLH clh) {
        MS.hookClassLoad(clh.getClassName(), clh);
    }


}
