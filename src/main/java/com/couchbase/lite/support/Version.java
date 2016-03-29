package com.couchbase.lite.support;

import com.couchbase.lite.util.Log;

public class Version {
    public static final String SYNC_PROTOCOL_VERSION = "1.2";
    public static final String VERSION;

    private static final String VERSION_NAME="%VERSION_NAME%";  // replaced during build process
    private static final String VERSION_CODE="%VERSION_CODE%";  // replaced during build process
    private static final String COMMIT_HASH="%COMMIT_HASH%";  // replaced during build process

    static {
        int versCode = getVersionCode();
        if (versCode != 0) {
            VERSION = String.format("%s-%s", getVersionName(), getVersionCode());
        } else{
            VERSION = String.format("%s", getVersionName());
        }
    }

    public static String getVersionName() {
        if (VERSION_NAME.contains("VERSION_NAME")) {
            return "devbuild";
        }
        return VERSION_NAME;
    }

    public static int getVersionCode() {
        if (VERSION_CODE.contains("VERSION_CODE")) {
            return 0;
        }

        // VERSION_CODE should be empty string if it is release build
        if(VERSION_CODE == null || VERSION_CODE.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(VERSION_CODE);
        } catch (NumberFormatException e) {
            Log.w(Log.TAG, "Cannot parse version code: %s", VERSION_CODE);
            return 0;
        }
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getCommitHash(){
        if (COMMIT_HASH.contains("COMMIT_HASH")) {
            return "devbuild";
        }
        return COMMIT_HASH;
    }
}