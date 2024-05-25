package com.rinekri.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class SharegramEditText extends AppCompatEditText {

	public SharegramEditText(Context context) {
		super(context);
	}

	public SharegramEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}

	public SharegramEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}

	
	private void init(Context context, AttributeSet attrs) {
	    if (isInEditMode() != true) {
			setTypeface(FontChooser.parseAttributes(context, attrs)); 
	    }
	}

}
