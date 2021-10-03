package com.example.sudokusolverinjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import static java.security.AccessController.getContext;

public interface RetrofitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("users")

    //on below line we are creating a method to post our data.
    Call<DataModal> createPost(@Body DataModal dataModal);
}
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.sudokuSolverinjava.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int REQUEST_IMAGE_CAPTURE = 100;
        Button btnCapture = findViewById(R.id.cameraBtn);
        ImageView ivShowImage = findViewById(R.id.displayImageView);
        EnableRuntimePermission();
        btnCapture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });
        Button solve = findViewById(R.id.solve);
    }


    public static final int RequestPermissionCode  = 1 ;




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView ivShowImage = findViewById(R.id.displayImageView);
            ivShowImage.setImageBitmap(bitmap);
            Button solve = findViewById(R.id.solve);
            solve.setVisibility(View.VISIBLE);
            solve.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getBaseContext(), displayactivity.class);
                    String message = "aa";
                    intent.putExtra(EXTRA_MESSAGE, bitmap);
                    startActivity(intent);

                }
            });
        }
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(MainActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        super.onRequestPermissionsResult(RC, per, PResult);
        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}

