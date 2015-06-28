package com.example.kaoru.multicheckboxdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by kaoru on 2015/06/27.
 */
public class ListItemLayout extends ViewGroup {
    private final static float BASE_WIDTH = 640, BASE_HEIGHT = 120;
    private float mScaleFactor = 1;
    private CheckBox mCheckBox;

    public ListItemLayout(Context context) {
        super(context);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        mScaleFactor = w / BASE_WIDTH;
        int h = (int) (BASE_HEIGHT * mScaleFactor);

        setMeasuredDimension(w, h);

        measureCheckbox(mCheckBox, w, h, mScaleFactor);
    }

    private void measureCheckbox(View view, int w, int h, float scaleFactor) {
        view.measure(
                MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutCheckbox(mCheckBox);
    }

    private void layoutCheckbox(View view) {
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void toggle() {
        mCheckBox.toggle();
    }

    @ViewDebug.ExportedProperty
    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mCheckBox.setOnCheckedChangeListener(listener);
    }

    public void setText(CharSequence text) {
        mCheckBox.setText(text);
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getText() {
        return mCheckBox.getText();
    }
}
