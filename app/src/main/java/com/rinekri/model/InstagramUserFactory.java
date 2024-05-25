package com.rinekri.model;

import android.content.Context;
import com.rinekri.json.InstagramJSONWorker;
import com.rinekri.net.NetworkConnector;

public class InstagramUserFactory {

    public static InstagramUserFactory sInstagramUserFactory;
    public String sID;
    public String sNick;

    private Context mContext;
    private InstagramJSONWorker worker;

    private InstagramUserFactory(Context c) {
        mContext = c;
        worker = new InstagramJSONWorker(c);
    }

    public static InstagramUserFactory getFactory(Context c) {
        if (sInstagramUserFactory == null) {
            sInstagramUserFactory = new InstagramUserFactory(c);
        }

        return sInstagramUserFactory;
    }

    public String getID(String nick) {
        if ((sNick == null) || ((sNick != null) && !sNick.equals(nick))) {
            sNick = nick;
            sID = worker.getID(sNick);
        }

        return sID;
    }
}
