package com.example.sudokusolverinjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
class DataModal{
    int[][][] finala;
    public DataModal(int[][][] name) {
        this.finala = name;
    }
}

public class Loading extends AppCompatActivity {
    Intent intent;
    Bitmap message;
    final int PIXEL_WIDTH = 28;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading2);
        intent = getIntent();
        message = intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        postdata(message);
    }


    private void postdata(Bitmap message) {
        int[][][] finala=onDetectClicked();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(name, job);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                jobEdt.setText("");
                nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();

                // below line we are setting our
                // string to our text view.
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}

    }


    private int[][][] onDetectClicked() {
        TextView res = findViewById(R.id.digit1);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(message, PIXEL_WIDTH, PIXEL_WIDTH, false);
        int[] pixels = getImageData(scaledBitmap);
        int w = pixels[0];
        int h = pixels[1];
        int[][] red = new int[w][h];
        int[][] green = new int[w][h];
        int[][] blue = new int[w][h];

        for (int i = 2; i < pixels.length; i++) {
            int j = i - 2;
            red[j / w][j % h] = (int) ((pixels[i] >> 16) & 0xFF);
            green[j / w][j % h] = (int) ((pixels[i] >> 8) & 0xFF);
            blue[j / w][j % h] = (int) ((pixels[i] >> 0) & 0xFF);
        }
        int[][][] finala = new int[3][w][h];
        finala[0] = red;
        finala[1] = green;
        finala[2] = blue;

        return finala;
        //int digit = mnistClassifier.classify(scaledBitmap);
        //res.setText(Integer.toString(red[0][0]));
    }

    protected int[] getImageData(Bitmap img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] data = new int[w * h];
        img.getPixels(data, 0, w, 0, 0, w, h);
        int[] result = new int[w * h + 2];
        for (int i = 2; i < w * h + 2; i++) {
            result[i] = data[i - 2];
        }
        result[0] = w;
        result[1] = h;
        return result;
    }
}