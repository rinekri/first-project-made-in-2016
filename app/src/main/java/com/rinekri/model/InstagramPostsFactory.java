package com.rinekri.model;

import android.content.Context;
import android.util.Log;

import com.rinekri.json.InstagramJSONWorker;

import java.util.ArrayList;
import java.util.Collections;

public class InstagramPostsFactory {
    public static final String TAG = "InstagramPostsFactory";

    private static InstagramPostsFactory sInstagramPostsFactory;
    private static String sUserID;

    private Context mAppContext;
    private ArrayList<InstagramPost> mInstagramPosts;
    private InstagramJSONWorker JSONworker;

    private InstagramPostsFactory(Context c) {
        mAppContext = c;
        JSONworker = new InstagramJSONWorker(mAppContext);
    }

    public static InstagramPostsFactory getFactory(Context c) {

        if (sInstagramPostsFactory == null) {
            sInstagramPostsFactory = new InstagramPostsFactory(c);
        }
        return sInstagramPostsFactory;
    }

    public ArrayList<InstagramPost> getInstagramPosts(String id) {
        mInstagramPosts = JSONworker.getPosts(id);
        return mInstagramPosts;
    }

    public ArrayList<InstagramPost> returnInstagramPosts() {
        return mInstagramPosts;
    }

    public boolean getInstagramPostsStatus(String id) {
        boolean status = false;
        if (((sUserID == null) && (mInstagramPosts == null)) || ((sUserID != null) && !sUserID.equals(id))) {
            sUserID = id;
            status = false;
        } else if ((sUserID != null) && (mInstagramPosts != null) && (mInstagramPosts.size() == 0) && sUserID.equals(id)) {
            sUserID = id;
            status = false;
        }
        else if ((sUserID != null) && (mInstagramPosts != null) && sUserID.equals(id)) {
            status = true;
        }
//        Log.d(TAG,"Status"+status);
        return status;
    }

    public ArrayList<InstagramPost> getSortedForLikesInstagramPosts(ArrayList<InstagramPost> unsortedPosts) {
        Collections.sort(unsortedPosts);
        mInstagramPosts = unsortedPosts;
        return mInstagramPosts;
    }

    public InstagramPost getInstagramPost(String imageID) {
        for(InstagramPost post : mInstagramPosts) {
            if (post.getPostID().equals(imageID)) return post;
        }
        return null;
    }
}
