package acgmohu.funclogger;

import com.saurik.substrate.MS;

/**
 * Created by lai on 7/29/14.
 *
 * Abstract ClassLoadHook
 */
abstract class CLH implements MS.ClassLoadHook {
    abstract String getClassName();
}


