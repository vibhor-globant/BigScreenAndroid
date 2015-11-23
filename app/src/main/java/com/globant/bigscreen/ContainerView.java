package com.globant.bigscreen;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

/**
 * TODO: document your custom view class.
 */
public class ContainerView extends View {

    private VideoView mVideoView;

    public ContainerView(Context context) {
        super(context);
        init(context);
    }

    public ContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private GestureDetector mGestureDetector;

    private static Uri ResourceToUri (Context context,int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID));
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, new CustomGestureDetector());
//        mVideoView = (VideoView)rootView.findViewById(R.id.videoView);

//        Uri uri = Uri.parse("android.resource://com.globant.bigscreen/raw/big_buck_bunny_720p_surround.mp4");
//        Log.v("BigScreen", "mVideoView = " + mVideoView);
//        mVideoView.setVideoURI(uri);
//        mVideoView.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private float mX, mY;
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            setTranslationX(e2.getRawX() - mX);
            setTranslationY(e2.getRawY() - mY);
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            mX = e.getRawX() - getTranslationX();
            mY = e.getRawY() - getTranslationY();
            return true;
        }

    }
}
