package com.chouren.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public abstract class BaseDialogCreater<T extends BaseDialogCreater> {
    public static final String ARG_TITLE = "arg_title";
    public static final String ARG_MESSAGE = "arg_message";
    public static final String ARG_POSITIVE_TEXT = "arg_positive_text";
    public static final String ARG_NEGATIVE_TEXT = "arg_negative_text";
    public static final String ARG_ITEMS = "arg_items";
    public static final String ARG_ITEMS_CHECKED = "arg_items_checked";
    public static final String ARG_CHECKED_ID = "ARG_CHECKED_ID";
    public static final String ARG_CANCELABLE = "arg_cancelable";
    public static final String ARG_AUTO_DISMISS = "arg_auto_dismiss";
    public static final String ARG_CANCEL_ON_TOUCH_OUTSIDE = "arg_cancel_on_touch_outside";
    public static final String ARG_MODE = "arg_mode";

    protected boolean mCancelable;
    protected boolean mAutoDismiss;
    protected boolean mCancelOnTouchOutside;
    protected String mPositiveText;
    protected String mNegativeText;
    private FragmentManager mFragmentManager;
    private Context mContext;
    private Class mClass;

    public BaseDialogCreater(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> clazz) {
        mFragmentManager = fragmentManager;
        mContext = context.getApplicationContext();
        mClass = clazz;
    }

    protected abstract Bundle prepareArguments ();
    protected abstract T self();

    public T setCancelable(boolean cancelable) {
        this.mCancelable = cancelable;
        return self();
    }

    public T setAutoDismiss(boolean autoDismiss) {
        this.mAutoDismiss = autoDismiss;
        return self();
    }

    public T setCancelOnTouchOutside(boolean cancelOnTouchOutside) {
        this.mCancelOnTouchOutside = cancelOnTouchOutside;
        return self();
    }

    public T setPositiveText(String positiveText) {
        this.mPositiveText = positiveText;
        return self();
    }

    public T setNegativeText(String negativeText) {
        this.mNegativeText = negativeText;
        return self();
    }

    private BaseDialogFragment create() {
        BaseDialogFragment fragment = (BaseDialogFragment) Fragment.instantiate(mContext,
                mClass.getName(), prepareArguments());
        fragment.setCancelable(mCancelable);
        return fragment;
    }

    public BaseDialogFragment show() {
        BaseDialogFragment fragment = create();
        fragment.show(mFragmentManager, null);
        return fragment;
    }

}
