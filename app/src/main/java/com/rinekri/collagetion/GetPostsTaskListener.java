package com.rinekri.collagetion;

import com.rinekri.model.InstagramPost;

import java.util.ArrayList;

public interface GetPostsTaskListener {

    void onGetPostsTaskStarted();

    void onGetPostsTaskFinished(ArrayList<InstagramPost> result);

}