package com.rinekri.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class SharegramButton extends AppCompatButton {

	public SharegramButton(Context context) {
		super(context);
	}

	public SharegramButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}

	public SharegramButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}

	private void init(Context context, AttributeSet attrs) {
	    if (isInEditMode() != true) {
			setTypeface(FontChooser.parseAttributes(context, attrs)); 
	    }
	}
}
