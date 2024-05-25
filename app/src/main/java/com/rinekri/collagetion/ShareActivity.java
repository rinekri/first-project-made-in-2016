package com.rinekri.collagetion;

import android.support.v4.app.Fragment;

public class ShareActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new ShareFragment();
	}
}
