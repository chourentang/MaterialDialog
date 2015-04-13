package com.chouren.library.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chouren.library.R;
import com.chouren.library.base.BaseDialogCreater;
import com.chouren.library.base.BaseDialogFragment;
import com.chouren.library.comm.TypefaceHelper;

public class ProgressMaterialDialog extends BaseDialogFragment {

    @Override
    protected void buildDialog() {
        Bundle data = getArguments();

        String title = data.getString(BaseDialogCreater.ARG_TITLE);
        String message = data.getString(BaseDialogCreater.ARG_MESSAGE);
        setTitle(title);
        setMessage(message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        buildDialog();

        View view = inflater.inflate(R.layout.fragment_dialog_progressbar, container, false);

        TextView vTitle = (TextView) view.findViewById(R.id.title);
        TextView vMessage = (TextView) view.findViewById(R.id.message);

        Typeface regularFont = TypefaceHelper.get(getActivity(), "Roboto-Regular");
        Typeface mediumFont = TypefaceHelper.get(getActivity(), "Roboto-Medium");

        beautifyText(vTitle, mTitle, mediumFont);
        beautifyText(vMessage, mMessage, regularFont);

        return view;
    }

    public static ProgressDialogCreater getCreater(Context context, FragmentManager fm) {
        return new ProgressDialogCreater(context, fm, ProgressMaterialDialog.class);
    }

    public static class ProgressDialogCreater extends BaseDialogCreater {
        private String mTitle;
        private String mMessage;


        public ProgressDialogCreater(Context context, FragmentManager fragmentManager,
                                   Class<? extends BaseDialogFragment> clazz) {
            super(context, fragmentManager, clazz);
        }

        @Override
        protected Bundle prepareArguments() {
            Bundle data = new Bundle();
            data.putString(ARG_TITLE, mTitle);
            data.putString(ARG_MESSAGE, mMessage);
            return data;
        }

        @Override
        protected ProgressDialogCreater self() {
            return this;
        }

        public ProgressDialogCreater setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public ProgressDialogCreater setMessage(String message) {
            this.mMessage = message;
            return this;
        }
    }
}
