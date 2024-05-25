package com.rinekri.collagetion;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import com.rinekri.collagetion.R;
import com.rinekri.collagetion.R.attr;
import com.rinekri.collagetion.R.drawable;
import com.rinekri.collagetion.R.id;
import com.rinekri.collagetion.R.layout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class SingleFragmentActivity extends AppCompatActivity {
	private static final String TAG = "BaseActivity";

	protected abstract Fragment createFragment();
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(android.R.attr.colorPrimaryDark, typedValue, true);
            int color = typedValue.data;

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(null, bm, color);

            setTaskDescription(td);
            bm.recycle();
		}
		
	}
}


