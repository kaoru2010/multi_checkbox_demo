package com.example.kaoru.multicheckboxdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by kaoru on 2015/06/27.
 */
public class MyAdapter extends BaseAdapter {
    private final MyModel mMyModel;

    public MyAdapter(MyModel myModel) {
        mMyModel = myModel;
    }

    @Override
    public int getCount() {
        return mMyModel.getCount();
    }

    @Override
    public MyModel.Entity getItem(int position) {
        return mMyModel.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        if (convertView instanceof ListItemLayout) {
            configureView((ListItemLayout) convertView, getItem(position));
        }

        return convertView;
    }

    private void configureView(ListItemLayout layout, MyModel.Entity item) {
        layout.setText(item.getMessage());
        layout.setChecked(mMyModel.isChecked(item));
        layout.setOnCheckedChangeListener(new CheckBoxOnCheckedChangeListener(mMyModel, item));
    }

    private static class CheckBoxOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private final MyModel mMyModel;
        private final MyModel.Entity mItem;

        private CheckBoxOnCheckedChangeListener(MyModel myModel, MyModel.Entity item) {
            mMyModel = myModel;
            mItem = item;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked && mMyModel.isCheckable(mItem)) {
                cancelCheckedStateChanges(buttonView);
                return;
            }

            mMyModel.setChecked(mItem, isChecked);
        }

        private void cancelCheckedStateChanges(final CompoundButton buttonView) {
            buttonView.post(new Runnable() {
                @Override
                public void run() {
                    Context context = buttonView.getContext();
                    buttonView.setChecked(false);
                    Toast.makeText(context, context.getString(R.string.error_message_of_checkbox, MyModel.getMaxCheckedCount()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
