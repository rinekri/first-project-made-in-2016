package com.rinekri.widget;

import com.rinekri.collagetion.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;

public class FontChooser {
	public static final String TAG = "FontChooser";
	private final static int ROBOTO_LIGHT = 0;
	private final static int ROBOTO_REGULAR = 1;
	private final static int ROBOTO_MEDIUM = 2;
	private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(3);

	public static Typeface parseAttributes(Context context, AttributeSet attrs) {
	    TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.SharegramRobotoView);

	    int typefaceValue = values.getInt(R.styleable.SharegramRobotoView_fontCustom, 1);
	    values.recycle();

	    return obtainTypeface(context, typefaceValue);
	}
	
	public static Typeface obtainTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
	    Typeface typeface = mTypefaces.get(typefaceValue);
	    if (typeface == null) {
	        typeface = createTypeface(context, typefaceValue);
	        mTypefaces.put(typefaceValue, typeface);
	    }
	    return typeface;
	}

	private static Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
	    Typeface typeface = null;
	    switch (typefaceValue) {
	        case ROBOTO_LIGHT:
		        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
	            break;
	        case ROBOTO_REGULAR:
	            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
	            break;
	        case ROBOTO_MEDIUM:
	            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
	            break;
	        default:
	            throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
	    }
	    return typeface;
	}
}
