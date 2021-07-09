package com.example.a2dgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class backGround {

    int X=0 ,Y=0;
    Bitmap background;

    backGround(int screenX ,int screenY, Resources res){

        background= BitmapFactory.decodeResource(res,R.drawable.background);
        background=Bitmap.createScaledBitmap(background,screenX,screenY,false);

    }

}
