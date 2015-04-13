package com.chouren.library.base;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chouren.library.R;
import com.chouren.library.comm.TypefaceHelper;

public abstract class BaseDialogFragment extends DialogFragment {
    protected CharSequence mTitle;
    protected CharSequence mMessage;
    private CharSequence mPositiveButtonText;
    private CharSequence mNegativeButtonText;
    private View mCustomView;
    private ListAdapter mListAdapter;
    private int mListCheckedItemIdx;
    private int mChoiceMode;
    private int[] mListCheckedItemMultipleIds;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.MaterialDialog_Dialog);
        Bundle data = getArguments();
        boolean cancelable = data.getBoolean(BaseDialogCreater.ARG_CANCELABLE);
        boolean cancelableTouch = data.getBoolean(BaseDialogCreater.ARG_CANCEL_ON_TOUCH_OUTSIDE);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelableTouch);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        buildDialog();

        View view = inflater.inflate(R.layout.fragment_dialog_base, container, false);
        TextView vTitle = (TextView) view.findViewById(R.id.title);
        TextView vMessage = (TextView) view.findViewById(R.id.message);
        FrameLayout vCustomView = (FrameLayout) view.findViewById(R.id.custom_view);
        Button vPositiveButton = (Button) view.findViewById(R.id.positive);
        Button vNegativeButton = (Button) view.findViewById(R.id.negative);
        View vButtonsLayout = view.findViewById(R.id.button_layout);
        ListView vList = (ListView) view.findViewById(R.id.list);

        Typeface regularFont = TypefaceHelper.get(getActivity(), "Roboto-Regular");
        Typeface mediumFont = TypefaceHelper.get(getActivity(), "Roboto-Medium");

        beautifyText(vTitle, mTitle, mediumFont);
        beautifyText(vMessage, mMessage, regularFont);

        if(mCustomView != null) vCustomView.addView(mCustomView);

        if (mListAdapter != null) {
            vList.setAdapter(mListAdapter);
            vList.setOnItemClickListener(mOnItemClickListener);
            if (mListCheckedItemIdx != -1) {
                vList.setSelection(mListCheckedItemIdx);
            }
            if (mListCheckedItemMultipleIds != null) {
                vList.setChoiceMode(mChoiceMode);
                for (int i : mListCheckedItemMultipleIds) {
                    vList.setItemChecked(i, true);
                }
            }
        }

        beautifyText(vPositiveButton, mPositiveButtonText, mediumFont);
        beautifyText(vNegativeButton, mNegativeButtonText, mediumFont);

        return view;
    }

    protected abstract void buildDialog();

    public void setTitle(CharSequence title) {
        this.mTitle = title;
    }

    public void setPositiveButton(CharSequence text) {
        mPositiveButtonText = text;
    }

    public void setNegativeButton(CharSequence text) {
        mNegativeButtonText = text;
    }

    public void setMessage(CharSequence message) {
        mMessage = message;
    }

    public void setItems(ListAdapter listAdapter, int[] checkedItemIds, int choiceMode, final AdapterView.OnItemClickListener listener) {
        mListAdapter = listAdapter;
        mListCheckedItemMultipleIds = checkedItemIds;
        mOnItemClickListener = listener;
        mChoiceMode = choiceMode;
        mListCheckedItemIdx = -1;
    }

    public void setItems(ListAdapter listAdapter, int checkedItemIdx,
                            final AdapterView.OnItemClickListener listener) {
        mListAdapter = listAdapter;
        mOnItemClickListener = listener;
        mListCheckedItemIdx = checkedItemIdx;
        mChoiceMode = AbsListView.CHOICE_MODE_NONE;
    }

    public void setView(View view) {
        mCustomView = view;
    }

    public void beautifyText(TextView textView, CharSequence title, Typeface tf) {
        if(!TextUtils.isEmpty(title)) {
            textView.setText(title);
            textView.setTypeface(tf);
        }else {
            textView.setVisibility(View.GONE);
        }
    }

}
