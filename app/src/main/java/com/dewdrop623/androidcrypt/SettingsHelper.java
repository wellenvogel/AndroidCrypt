package com.dewdrop623.androidcrypt;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SettingsHelper provides an interface to Android's SharedPreferences.
 */

public final class SettingsHelper {

    public static final int FILE_ICON_VIEWER=0;
    public static final int FILE_LIST_VIEWER=1;
    public static final int FILE_DEFAULT_VIEWER=FILE_ICON_VIEWER;

    private static final String SHARED_PREFERENCES_FILE = "com.dewdrop623.androidcrypt.SettingsHelper.SHARED_PREFERENCES_FILE";
    private static final String AESCRYPT_VERSION_PREF = "com.dewdrop623.androidcrypt.SettingsHelper.AESCRYPT_VERSION_PREF";
    private static final String SHOW_INTERNAL_STORAGE_HELP = "com.dewdrop623.androidcrypt.SettingsHelper.SHOW_INTERNAL_STORAGE_HELP";
    private static final String FILE_VIEWER_TYPE = "com.dewdrop623.androidcrypt.FileBrowsing.ui.SettingsHelper.FILE_VIEWER_TYPE";
    private static final String SDCARD_ROOT = "com.dewdrop623.androidcrypt.FileBrowsing.ui.SettingsHelper.SDCARD_ROOT";

    public static final int AESCRYPT_DEFAULT_VERSION = CryptoThread.VERSION_2;

    private static SharedPreferences sharedPreferences;

    private SettingsHelper() {

    }

    private static SharedPreferences getSharedPreferencesFile(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
        }
        return sharedPreferences;
    }

    private static void sharedPreferencesPutInt(Context context, String key, int value) {
        getSharedPreferencesFile(context).edit().putInt(key, value).apply();
    }

    private static void sharedPreferencesPutBoolean(Context context, String key, boolean value) {
        getSharedPreferencesFile(context).edit().putBoolean(key, value).apply();
    }

    private static void sharedPreferencesPutString(Context context, String key, String value) {
        getSharedPreferencesFile(context).edit().putString(key, value).apply();
    }

    /***********************************
     * Set settings
     */

    public static void setAESCryptVersion(Context context, int version) {
        if (version != CryptoThread.VERSION_1 && version != CryptoThread.VERSION_2) {
            version = AESCRYPT_DEFAULT_VERSION;
        }
        sharedPreferencesPutInt(context, AESCRYPT_VERSION_PREF, version);
    }

    public static void setShowInternalStorageHelp(Context context, boolean show) {
        sharedPreferencesPutBoolean(context, SHOW_INTERNAL_STORAGE_HELP, show);
    }

    //set the type of file viewer using this class's integer global constants for file viewer types
    public static void setFilePickerType(Context context, int fileViewerType) {
        sharedPreferencesPutInt(context, FILE_VIEWER_TYPE, fileViewerType);
    }

    public static void setSdcardRoot(Context context, String sdCardRoot) {
        sharedPreferencesPutString(context, SDCARD_ROOT, sdCardRoot);
    }

    /***********************************
     * Get settings
     */

    public static int getAESCryptVersion(Context context) {
        return getSharedPreferencesFile(context).getInt(AESCRYPT_VERSION_PREF, AESCRYPT_DEFAULT_VERSION);
    }

    public static boolean getShowInternalStorageHelp(Context context) {
        return getSharedPreferencesFile(context).getBoolean(SHOW_INTERNAL_STORAGE_HELP, true);
    }

    //get the integer representing the file view type (as defined by this class's global constants)
    public static int getFilePickerType(Context context) {
        return getSharedPreferencesFile(context).getInt(FILE_VIEWER_TYPE, FILE_DEFAULT_VIEWER);
    }

    /**
     * Get the root directory of external storage. Returns null if not set.
     */
    public static String getSdcardRoot(Context context) {
        return getSharedPreferencesFile(context).getString(SDCARD_ROOT, null);
    }
}
