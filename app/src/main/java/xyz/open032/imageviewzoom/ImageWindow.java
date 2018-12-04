package xyz.open032.imageviewzoom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

public class ImageWindow extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TouchImageView touchImageView = (TouchImageView) findViewById(R.id.touchImageView);


        switch(requestCode) {
            case GALLERY_REQUEST:

                if(resultCode == RESULT_OK){
                    touchImageView.setVisibility(View.VISIBLE);
                   //imageView.setVisibility(View.INVISIBLE);
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    touchImageView.setImageBitmap(bitmap);
                }
        }
    }
}
