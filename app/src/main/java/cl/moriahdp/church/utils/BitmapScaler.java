package cl.moriahdp.church.utils;

import android.graphics.Bitmap;

/**
 * This class must be used when you need to fit a Bitmap with a specifics params, it has several
 * method that could keep or not aspect ratio
 *
 */
public class BitmapScaler
{
    /**
     * Scale a bitmap based on width value and keep aspect ratio
     *
     * @param b bitmap to be modified
     * @param width new width to be implemented
     * @return Bitmap scaled
     */
    public static Bitmap scaleToFitWidth(Bitmap b, int width)
    {
        float factor = width / (float) b.getWidth();
        return Bitmap.createScaledBitmap(b, width, (int) (b.getHeight() * factor), true);
    }

    /**
     * Scale a bitmap based on height value and keep aspect ratio
     *
     * @param b bitmap to be modified
     * @param height new height to be implemented
     * @return Bitmap scaled
     */
    public static Bitmap scaleToFitHeight(Bitmap b, int height)
    {
        float factor = height / (float) b.getHeight();
        return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factor), height, true);
    }


    /**
     * Scale a bitmap and keep aspect ratio
     *
     * @param b bitmap to be modified
     * @param width new width to be implemented
     * @param height new height to be implemented
     * @return Bitmap scaled
     */
    public static Bitmap scaleToFill(Bitmap b, int width, int height)
    {
        float factorH = height / (float) b.getHeight();
        float factorW = width / (float) b.getWidth();
        float factorToUse = (factorH > factorW) ? factorW : factorH;
        return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorToUse),
                (int) (b.getHeight() * factorToUse), true);
    }

    /**
     * Scale a bitmap and don't keep aspect ratio
     *
     * @param b bitmap to be modified
     * @param width new width to be implemented
     * @param height new height to be implemented
     * @return Bitmap stretched
     */
    public static Bitmap stretchToFill(Bitmap b, int width, int height)
    {
        float factorH = height / (float) b.getHeight();
        float factorW = width / (float) b.getWidth();
        return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorW),
                (int) (b.getHeight() * factorH), true);
    }
}