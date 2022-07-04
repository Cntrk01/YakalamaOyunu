package com.example.yakalamaoyunujava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText=findViewById(R.id.scoretext);
        timeText=findViewById(R.id.timetext);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray= new ImageView[]{imageView1,imageView2,imageView3,imageView4
        ,imageView5,imageView6,imageView7,imageView8,imageView9};

        score=0;
        hideImage();
        counter();


    }

    public void increasescore(View view){
        score++; //fotoğrafa her tıklandığı an scoru arttır
        scoreText.setText("SCORE : "+score);
    }
    public void counter(){
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timeText.setText("TIME : "+l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("TIME FINISHED");
                handler.removeCallbacks(runnable);
                for(ImageView imageView: imageArray){
                    imageView.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game ?");
                alert.setMessage("Are You Sure To Restart Game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent(); //suanki aktivite sınıfını baz aldı yani maini
                        finish(); //bitirdi
                        startActivity(intent); //tekrar başlattı
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish
                        Toast.makeText(MainActivity.this,"Finished Game.Your Score :"+score,Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void hideImage(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                //resimlerin hepsini görünmez yaptı
                for(ImageView imageView: imageArray){
                    imageView.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                //her verilen random deger için 1 tane resmi görünür yapacak
                imageArray[i].setVisibility(View.VISIBLE);
                //buradaki delay ile resmin hızını ayarlayabiliriz.
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }
}