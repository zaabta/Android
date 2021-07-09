package com.example.a2dgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.a2dgame.gameView.screenRatioX;
import static com.example.a2dgame.gameView.screenRatioY;

public class Bullet {

    int x,y,bulletheight,bulletwidth;
    Bitmap bullet;

    Bullet(Resources res){

        bullet= BitmapFactory.decodeResource(res,R.drawable.bullet);

        bulletwidth=bullet.getWidth();   //get the height and the width of bullet;
        bulletheight=bullet.getHeight();

        bulletheight/=4;
        bulletwidth/=4;


        bulletwidth = (int) (bulletwidth * screenRatioX);
        bulletheight = (int) (bulletheight * screenRatioY);

        bullet=Bitmap.createScaledBitmap(bullet,bulletwidth,bulletheight,false);





    }

    Rect getCollisionShape(){

        return new Rect(x,y,x+bulletwidth,y+bulletheight);
    }


}
