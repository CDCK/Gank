package com.cdck.androidplan.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by xlk on 2019/3/9.
 */
public class BmpUtil {
    private static final String TAG = "BmpUtil-->";

    /**
     * 等比缩放图片
     *
     * @param bm   原图
     * @param newW 新的宽度
     * @param newH 新的高度
     * @return
     */
    public static Bitmap zoomBmp(Bitmap bm, int newW, int newH) {
        int width = bm.getWidth();
        int height = bm.getHeight();
//        Log.e(TAG, "BmpUtil.zoomBmp :   --> 原图大小：" + width + "," + height);
        float scaleW = ((float) newW) / width;
        float scaleH = ((float) newH) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    /**
     * 根据宽度自适应等比缩放图片
     *
     * @param bm   原图
     * @param newW 新的宽度
     * @return
     */
    public static Bitmap zoomBmpByWidth(Bitmap bm, int newW) {
        int width = bm.getWidth();
        int height = bm.getHeight();
//        Log.e(TAG, "BmpUtil.zoomBmp :   --> 原图大小：" + width + "," + height + ",新的宽度:" + newW);
        float scaleW = ((float) newW) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleW);
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    /**
     * 根据高度自适应等比缩放图片
     *
     * @param bm   原图
     * @param newH 新的高度
     * @return
     */
    public static Bitmap zoomBmpByHeight(Bitmap bm, int newH) {
        int width = bm.getWidth();
        int height = bm.getHeight();
//        Log.e(TAG, "BmpUtil.zoomBmp :   --> 原图大小：" + width + "," + height + ",新的高度：" + newH);
        float scaleH = ((float) newH) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleH, scaleH);
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return bitmap;
    }
}
