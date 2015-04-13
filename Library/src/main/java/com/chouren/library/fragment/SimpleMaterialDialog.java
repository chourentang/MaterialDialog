package com.chouren.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.chouren.library.base.BaseDialogCreater;
import com.chouren.library.base.BaseDialogFragment;

public class SimpleMaterialDialog extends BaseDialogFragment {

    @Override
    protected void buildDialog() {
        Bundle data = getArguments();

        String title = data.getString(BaseDialogCreater.ARG_TITLE);
        String message = data.getString(BaseDialogCreater.ARG_MESSAGE);
        String positiveText = data.getString(BaseDialogCreater.ARG_POSITIVE_TEXT);
        String negativeText = data.getString(BaseDialogCreater.ARG_NEGATIVE_TEXT);

        setTitle(title);
        setMessage(message);
        setPositiveButton(positiveText);
        setNegativeButton(negativeText);
    }

    public static SimpleDialogCreater getCreater(Context context, FragmentManager fm) {
        return new SimpleDialogCreater(context, fm, SimpleMaterialDialog.class);
    }

    public static class SimpleDialogCreater extends BaseDialogCreater {
        private String mTitle;
        private String mMessage;

        public SimpleDialogCreater(Context context, FragmentManager fragmentManager,
                                   Class<? extends BaseDialogFragment> clazz) {
            super(context, fragmentManager, clazz);
        }

        @Override
        protected Bundle prepareArguments() {
            Bundle data = new Bundle();
            data.putString(ARG_TITLE, mTitle);
            data.putString(ARG_MESSAGE, mMessage);
            data.putString(ARG_POSITIVE_TEXT, mPositiveText);
            data.putString(ARG_NEGATIVE_TEXT, mNegativeText);
            return data;
        }

        @Override
        protected SimpleDialogCreater self() {
            return this;
        }

        public SimpleDialogCreater setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public SimpleDialogCreater setMessage(String message) {
            this.mMessage = message;
            return this;
        }
    }
}
