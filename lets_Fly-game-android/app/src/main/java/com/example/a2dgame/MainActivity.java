package com.example.a2dgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent=new Intent(MainActivity.this,GameActivity.class);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        TextView hightScore=findViewById(R.id.high_score);
        final SharedPreferences prefs=getSharedPreferences("game",MODE_PRIVATE);
        hightScore.setText("High Score :"+prefs.getInt("highscore",0));

        isMute = prefs.getBoolean("isMute",false);


        final ImageView volumectrl=findViewById(R.id.volumectrl);

        if(isMute)
            volumectrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
        else
            volumectrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumectrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute =! isMute;


                if(isMute)
                    volumectrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
                else
                    volumectrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

                SharedPreferences.Editor editor=prefs.edit();     //To keep all the setting
                editor.putBoolean("isMute",isMute);
                editor.apply();

            }
        });



    }
}
