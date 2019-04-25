package org.brohede.marcus.sqliteapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadImage extends AsyncTask<String, Void, Bitmap>  {
    private ImageView imageView;

    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            InputStream in = new URL(urls[0]).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
