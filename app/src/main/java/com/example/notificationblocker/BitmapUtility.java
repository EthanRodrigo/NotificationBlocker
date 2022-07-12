package com.example.notificationblocker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

public class BitmapUtility {
    /***
     * @param img: image or a drawable
     * @return a byte array of @param
     */
    public static byte[] getBytes(Drawable img) {
        Bitmap bitmap = drawable2Bitmap(img);
        return bitmap2Bytes(bitmap);
    }

    /***
     * drawable2Bitmap: A helper function to convert a drawable to bitmap
     * @param draw: the drawable
     * @return a Bitmap of the @param
     */
    private static Bitmap drawable2Bitmap(Drawable draw){
        Bitmap bitmap = Bitmap.createBitmap(
                draw.getIntrinsicWidth(),
                draw.getIntrinsicHeight(),
                draw.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565
        );
        Canvas canva = new Canvas(bitmap);
        draw.setBounds(0, 0, draw.getIntrinsicWidth(), draw.getIntrinsicHeight());
        draw.draw(canva);

        return bitmap;
    }

    /***
     * bitmap2Bytes: A helper function to convert a Bitmap to a Byte array
     * @param bm: the Bitmap
     * @return a byte array of @param
     */
    private static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);

        return stream.toByteArray();
    }

    /***
     * @param image: The byte array of the image
     * @return a Drawable of @param
     */
    public static Drawable getImage(byte[] image){ // pass `this` as the context
        Bitmap bitmap = bytes2Bitmap(image);
        return bitmap2Drawable(bitmap);
    }

    /***
     * bytes2Bitmap: A helper function to convert a byte array to a Bitmap
     * @param btArr: the byte array to be converted
     * @return a Bitmap of @param
     */
    private static Bitmap bytes2Bitmap(byte[] btArr){
        if(btArr.length != 0){
            return BitmapFactory.decodeByteArray(btArr, 0, btArr.length);
        }
        return null;
    }

    /***
     * bitmap2Drawable: A helper function to convert a Bitmap to a Drawable
     * @param btmp: the Bitmap
     * @return the Drawable of the @param
     */
    private static Drawable bitmap2Drawable(Bitmap btmp){
        @SuppressWarnings("deprecation")
        BitmapDrawable btDrawable = new BitmapDrawable(btmp);
        Drawable drawable = (Drawable) btDrawable;

        return drawable;
    }
}
