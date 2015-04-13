package com.chouren.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chouren.library.R;
import com.chouren.library.base.BaseDialogCreater;
import com.chouren.library.base.BaseDialogFragment;

public class ListMaterialDialog extends BaseDialogFragment {

    @Override
    protected void buildDialog() {
        Bundle data = getArguments();

        String title = data.getString(BaseDialogCreater.ARG_TITLE);
        String message = data.getString(BaseDialogCreater.ARG_MESSAGE);
        String positiveText = data.getString(BaseDialogCreater.ARG_POSITIVE_TEXT);
        String negativeText = data.getString(BaseDialogCreater.ARG_NEGATIVE_TEXT);
        String[] items = data.getStringArray(BaseDialogCreater.ARG_ITEMS);
        int[] itemsChecked = data.getIntArray(BaseDialogCreater.ARG_ITEMS_CHECKED);
        int checkedId = data.getInt(BaseDialogCreater.ARG_CHECKED_ID);
        int mode = data.getInt(BaseDialogCreater.ARG_MODE);

        setTitle(title);
        setMessage(message);
        setPositiveButton(positiveText);
        setNegativeButton(negativeText);

        if(mode == AbsListView.CHOICE_MODE_SINGLE) {
            setItems(createListAdapter(R.layout.fragment_dialog_list_radio, items),
                    new int[] {checkedId},
                    AbsListView.CHOICE_MODE_SINGLE,
                    null);
        }else if(mode == AbsListView.CHOICE_MODE_MULTIPLE) {
            setItems(createListAdapter(R.layout.fragment_dialog_list_mulit, items),
                    itemsChecked,
                    AbsListView.CHOICE_MODE_MULTIPLE,
                    null);
        }
    }

    private ListAdapter createListAdapter(final int resId, String[] items) {
        return new ArrayAdapter<String>(getActivity(),
                resId,
                R.id.item_text,
                items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
                }
                TextView t = (TextView)convertView.findViewById(R.id.item_text);
                if (t != null) {
                    t.setText(getItem(position));
                }
                return convertView;
            }
        };
    }

    public static ListDialogCreater create(Context context, FragmentManager fm) {
        return new ListDialogCreater(context, fm, ListMaterialDialog.class);
    }

    public static class ListDialogCreater extends BaseDialogCreater {
        private String mTitle;
        private String mMessage;
        private String[] mItems;
        private int[] mItemsChecked;
        private int mMode;
        private int mCheckedId;

        public ListDialogCreater(Context context, FragmentManager fragmentManager,
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
            data.putStringArray(ARG_ITEMS, mItems);
            data.putIntArray(ARG_ITEMS_CHECKED, mItemsChecked);
            data.putInt(ARG_CHECKED_ID, mCheckedId);
            data.putInt(ARG_MODE, mMode);
            return data;
        }

        @Override
        protected BaseDialogCreater self() {
            return this;
        }

        public ListDialogCreater setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public ListDialogCreater setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public ListDialogCreater setItems(String[] items) {
            this.mItems = items;
            return this;
        }

        public ListDialogCreater setItemsChecked(int[] itemsChecked) {
            this.mItemsChecked = itemsChecked;
            return this;
        }

        public ListDialogCreater setMode(int mode) {
            this.mMode = mode;
            return this;
        }

        public ListDialogCreater setCheckedId(int id) {
            this.mCheckedId = id;
            return this;
        }
    }
}
