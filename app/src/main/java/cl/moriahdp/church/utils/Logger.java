package cl.moriahdp.church.utils;

import android.util.Log;

import cl.moriahdp.church.BuildConfig;

public class Logger {

    /**
     * TAG to be used for logging purposes
     */
    private static final String TAG = "JUMBO";

    /**
     * Perform logging with Verbose level of verbosity.
     *
     * @param message Message to log.
     */
    public static void verb(String message) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, message);
        }
    }

    public static void verb(String logType, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG + "_" + logType, message);
        }
    }

    /**
     * Perform logging with Info level of verbosity
     *
     * @param message Message to log.
     */
    public static void info(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, message);
        }
    }

    /**
     * Perform logging with Info level of verbosity
     *
     * @param message Message to log.
     */
    public static void debug(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

    /**
     * Perform logging with Error level of verbosity
     *
     * @param message Message to log.
     */
    public static void error(String message) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message);
        }
    }

    public static void error(String message, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message, e);
        }
    }

    public static void warn(String message) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, message);
        }
    }

    public static void info(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.i(tag, message);
    }

    public static void debug(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.d(tag, " " + message);
    }
    public static void warning(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.w(tag, message);
    }

    public static void error(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.e(tag, message);
    }

}
