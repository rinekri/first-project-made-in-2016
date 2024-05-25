package com.rinekri.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class DirectoryReturner {
    public static final String IMAGE_CACHE_FOLDER = "image_cache";
    public static final String COLLAGE_FOLDER = "collage";

    private Context mContext;

    public DirectoryReturner(Context c) {
        mContext = c;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public File returnDirectoryWIthFile(String folder, String filename) {

        File directory = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            directory = innerDirectoryWithFile(folder, filename);
        } else {
            if (Environment.isExternalStorageEmulated() == true) {
                directory = outerDirectoryWithFile(folder, filename);
            } else {
                directory = innerDirectoryWithFile(folder, filename);
            }
        }
        return directory;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public File returnDirectory(String folder) {

        File directory = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            directory = innerDirectory(folder);
        } else {
            if (Environment.isExternalStorageEmulated() == true) {
                directory = outerDirectory(folder);
            } else {
                directory = innerDirectory(folder);
            }
        }
        return directory;
    }

    private File innerDirectoryWithFile(String folder, String filename) {
        File f = new File(mContext.getDir(folder,Context.MODE_PRIVATE), filename);
        f.setReadable(true, false);
        return f;
    }

    public File outerDirectoryWithFile(String folder, String filename) {
        return new File(mContext.getExternalFilesDir(folder), filename);
    }

    private File innerDirectory(String folder) {
        File f = mContext.getDir(folder,Context.MODE_PRIVATE);
        f.setReadable(true, false);
        return f;
    }

    public File outerDirectory(String folder) {
        return mContext.getExternalFilesDir(folder);
    }
}
