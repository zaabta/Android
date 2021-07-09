package com.example.a2dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Bird[] birds;
    private Random rnd;
    private SharedPreferences prefs;
    private Random random;
    private SoundPool soundPool;
    private List<Bullet> bullets;
    private int sound;
    private Flight flight;
    private GameActivity activity;
    private backGround background1, background2;
    private SharedPreferences sharedPreferences;

    public gameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound=soundPool.load(activity,R.raw.shoot,1);         //The sound of shoot

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new backGround(screenX, screenY, getResources());
        background2 = new backGround(screenX, screenY, getResources());

        flight = new Flight(this, screenY, getResources());

        rnd =new Random();


        bullets = new ArrayList<>();   //Initialize of Bullets

        birds=new Bird[4];            //Initialize of birds

        for(int i=0; i<birds.length ;i++){
            birds[i]=new Bird(getResources());
        }

        background2.X = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

    }

    @Override
    public void run() {
        while (isPlaying) {
            update ();
            draw ();
            sleep ();
        }
    }

    private void update () {

        background1.X -= 10 * screenRatioX;
        background2.X -= 10 * screenRatioX;

        if (background1.X + background1.background.getWidth() < 0) {
            background1.X = screenX;
        }

        if (background2.X + background2.background.getWidth() < 0) {
            background2.X = screenX;
        }

        if (flight.isGoingUp)
            flight.Y -= 30 * screenRatioY;
        else
            flight.Y += 30 * screenRatioY;

        if (flight.Y < 0)
            flight.Y = 0;

        if (flight.Y >= screenY - flight.height)
            flight.Y = screenY - flight.height;

        List<Bullet> trash = new ArrayList<>();

        for (Bullet bullet : bullets) {

            if (bullet.x > screenX)
                trash.add(bullet);

            bullet.x += 50 * screenRatioX;


            for(Bird bird:birds){

                if(Rect.intersects(bird.getCollisionShape(),
                        bullet.getCollisionShape())){

                    bird.x=-500;    //make bird apprear  agine from right ;
                    bullet.x=screenX+500;    //make the bullet disapprear agine
                    bird.wasShot=true;

                    score++;

                }


            }

        }

        for (Bullet bullet : trash)
            bullets.remove(bullet);

        for (Bird bird:birds){

            bird.x-=bird.speed;

            if(bird.x+bird.width<0){      //out of the screen : doesnt appeared yet

                if(!bird.wasShot){
                    isGameOver=true;
                    return;
                }

                int bound = (int) (30*screenRatioX);
                bird.speed=rnd.nextInt(bound);


                if(bird.speed<10 *screenRatioX)
                    bird.speed= (int) (10 *screenRatioX);

                bird.x=screenX;
                bird.y=rnd.nextInt(screenY-bird.height);    // make the birds appear randomly on screen

                bird.wasShot=false;
            }

            if(Rect.intersects(bird.getCollisionShape(),flight.getCollisionShape())){
                isGameOver=true;
                return;                       //* make game to work continual *//
            }

        }



    }





    private void draw () {   //Draw section

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.X, background1.Y, paint);
            canvas.drawBitmap(background2.background, background2.X, background2.Y, paint);


            canvas.drawText(score + "", screenX / 2f, 164, paint);

            //Birds
            for(Bird bird:birds){

                canvas.drawBitmap(bird.getBird(),bird.x,bird.y,paint);
            }


            if(isGameOver){
                isPlaying=false;
                canvas.drawBitmap(flight.getDead(),flight.X,flight.Y,paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveHightScore();
                waitBeforeExiting();

                return;
            }

            canvas.drawBitmap(flight.getFlight(), flight.X, flight.Y, paint);

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }


    private void waitBeforeExiting() {          //make it to wait for 3 second when game is over
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity,MainActivity.class));   //To star activity again when game be over
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //save hightscore

    private void saveHightScore() {
        if(prefs.getInt("highscore",0)<score){
            SharedPreferences.Editor editor=prefs.edit();
            editor.putInt("highscore",score);
            editor.apply();
        }


    }


    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }




    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toshoot++;
                break;
        }

        return true;
    }




    public void newBullet() {

        if (!prefs.getBoolean("isMute", false))
            soundPool.play(sound, 1, 1, 0, 0, 1);

        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.X + flight.width;
        bullet.y = flight.Y + (flight.height / 2);
        bullets.add(bullet);

    }
}