package com.escodro.viewpagify;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class ViewPagify extends LinearLayout implements View.OnTouchListener {

    /**
     * Constant to represent the default width padding value in dip.
     */
    private static final int WIDTH_PADDING_DEFAULT = 44;

    /**
     * {@link ViewPager} reference.
     */
    private ViewPager mPager;

    /**
     * Width padding between two items items in the list.
     */
    private int mAttrHorizontalPadding;

    /**
     * Attribute to define if the option to click in the previous/next item to change it is enabled
     * or not.
     */
    private boolean mAttrChangeItemByClick;

    /**
     * {@link OnItemClickedListener} reference.
     */
    private OnItemClickedListener mListener;

    /**
     * {@link GestureDetector} reference.
     */
    private GestureDetector mDetector;

    /**
     * {@link ViewPagifyPageTransformer} reference.
     */
    private ViewPagifyPageTransformer mTransformer;

    /**
     * Create a new instance of {@link ViewPagify}.
     *
     * @param context {@link Context}
     */
    public ViewPagify(Context context) {
        this(context, null);
    }
    public ViewPagify(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateAttributeSetValues(context, attrs);
        init(context);
    }
    private void init(Context context) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.pagify_view, this);
        mPager = (ViewPager) view.findViewById(R.id.baseViewPager);
        mTransformer = new ZoomOutPageTransformer();
        mPager.setClipToPadding(false);
        mPager.setOffscreenPageLimit(3);
        mPager.setOnTouchListener(this);
        mDetector = new GestureDetector(getContext(), new PagifyGestureDetector());
        updatePadding();
    }
    private void updateAttributeSetValues(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                    .ViewPagify);
            mAttrHorizontalPadding = typedArray.getDimensionPixelSize(R.styleable
                    .ViewPagify_horizontalPadding, getDPI(WIDTH_PADDING_DEFAULT));
            mAttrChangeItemByClick = typedArray.getBoolean(R.styleable.
                    ViewPagify_changeItemByClick, false);
            typedArray.recycle();
        }
    }
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPagifyPageTransformer
            transformer) {
        mTransformer = transformer;
        mTransformer.setPadding(mAttrHorizontalPadding);
        mPager.setPageTransformer(reverseDrawingOrder, mTransformer);
    }
    public void setHorizontalPadding(int padding) {
        if (padding > 0) {
            mAttrHorizontalPadding = padding;
            updatePadding();
        }
    }

    public int getCurrentItemPosition() {
        return mPager.getCurrentItem();
    }
    public void setCurrentItemPosition(int position) {
        mPager.setCurrentItem(position);
    }
    private void updatePadding() {
        mPager.setPadding(mAttrHorizontalPadding, mPager.getTop(), mAttrHorizontalPadding,
                mPager.getBottom());
        setPageTransformer(true, mTransformer);
    }
    private int getDPI(int pixels) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, getContext()
                .getResources().getDisplayMetrics());
    }

    public void setOnItemClickListener(OnItemClickedListener listener) {
        mListener = listener;
    }

    public void setAdapter(PagerAdapter adapter) {
        mPager.setAdapter(adapter);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mPager.addOnPageChangeListener(listener);
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
    private class PagifyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            int position = mPager.getCurrentItem();
            final View view = ((Fragment) mPager.getAdapter().instantiateItem(mPager,
                    position)).getView();
            if (view != null) {
                final Rect myViewRect = new Rect();
                view.getGlobalVisibleRect(myViewRect);
                float positionRight = myViewRect.right;
                float positionLeft = myViewRect.left;

                if (e.getX() > positionRight) {
                    mPager.setCurrentItem(position + 1, true);
                } else if (e.getX() < positionLeft) {
                    mPager.setCurrentItem(position - 1, true);
                } else if (mAttrChangeItemByClick) {
                    mListener.onItemClick(mPager, view, position);
                }
            }
            return super.onSingleTapUp(e);
        }
    }


    public interface OnItemClickedListener {

        void onItemClick(ViewPager parent, View view, int position);
    }
}
