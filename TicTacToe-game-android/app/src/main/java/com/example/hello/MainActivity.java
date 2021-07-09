package com.example.hello;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

PointF pointa=new PointF(10,100);
PointF pointb=new PointF(500,400);


private ImageButton[][] buttons_select=new ImageButton[3][3];
boolean player_turn=true; //turn_of_players
    ArrayList<Integer>palyer_1=new ArrayList<Integer>(); //player_1
    ArrayList<Integer>palyer_2=new ArrayList<Integer>(); //player_2
    ArrayList<Integer>Checked_button=new ArrayList<Integer>(); //the_button_cliked
    public int Winner=0; //The_Winner
    int bu_select; //number_of_the_button


public   AlertDialog.Builder dialog ;
public   int index_i,index_j; //numberOfindex__button
 ImageButton Rest;
 TextView x,o,sp1,sp2;
 int p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x=(TextView)findViewById(R.id.x);
        o=(TextView)findViewById(R.id.o);
        sp1=(TextView)findViewById(R.id.paler_1_score);
        sp2=(TextView)findViewById(R.id.player_2_score);
        Rest=(ImageButton)findViewById(R.id.Rest);
        Rest.setOnClickListener(this);
        // define_the_buttons;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
            {
                String buttonid ="button_"+i+"."+j;
                int id = getResources().getIdentifier(buttonid, "id", getPackageName());
                buttons_select[i][j]=findViewById(id);
                buttons_select[i][j].setOnClickListener(this);
            }
        }
    }



    @Override
    public void onClick(View v) {
         bu_select=0;                             //number of Button selected
       int id_button=v.getId();
        switch (id_button){
            case R.id.button_0_0:
                index_i=0;index_j=0;
                bu_select=1;
                Checked_button.add(bu_select);
                break;
            case R.id.button_0_1:
                index_i=0;index_j=1;
                bu_select=2;
                Checked_button.add(bu_select);
                break;
            case R.id.button_0_2:
                index_i=0;index_j=2;
                bu_select=3;
                Checked_button.add(bu_select);
                break;
            case R.id.button_1_0:
                index_i=1;index_j=0;
                bu_select=4;
                Checked_button.add(bu_select);
                break;
            case R.id.button_1_1:
                index_i=1;index_j=1;
                bu_select=5;
                Checked_button.add(bu_select);
                break;
            case R.id.button_1_2:
                index_i=1;index_j=2;
                bu_select=6;
                Checked_button.add(bu_select);
                break;
            case R.id.button_2_0:
                index_i=2;index_j=0;
                bu_select=7;
                Checked_button.add(bu_select);
                break;
            case R.id.button_2_1:
                index_i=2;index_j=1;
                bu_select=8;
                Checked_button.add(bu_select);
                break;
            case R.id.button_2_2:
                index_i=2;index_j=2;
                bu_select=9;
                Checked_button.add(bu_select);
                break;
            case R.id.Rest:
                GameOver(-1);
                break;
        }
        Log.d("palyer:"," *palyer_1 : "+palyer_1.size()+" palyer_2 :"+palyer_2.size()+" player_turn"+String.valueOf(player_turn));
       palygame(bu_select,buttons_select[index_i][index_j]);
    }


    public void palygame(int bu_select,ImageButton buttons_select ){

    if(player_turn == true){
        x.setText("");
        o.setText("O");
       buttons_select.setImageResource(R.drawable.xx);
        buttons_select.setScaleType(ImageButton.ScaleType.CENTER_CROP);
        buttons_select.setBackgroundColor(Color.WHITE);
       palyer_1.add(bu_select);
       player_turn=false;
    }
        else {
            x.setText("X");
            o.setText("");
            buttons_select.setImageResource(R.drawable.ooo);
            buttons_select.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            buttons_select.setBackgroundColor(Color.WHITE);
            palyer_2.add(bu_select);
            player_turn=true;
        }
        buttons_select.setEnabled(false);
        //Check the winner
           Check_Winner();
    }

    public void Check_Winner(){

        if(Winner == 0) //check_the_Winner
        {
            if(palyer_1.contains(1)&& palyer_1.contains(2)&& palyer_1.contains(3))
                Winner=1;
            else if(palyer_2.contains(1)&& palyer_2.contains(2)&& palyer_2.contains(3))
                Winner=2;
            else if(palyer_1.contains(1)&& palyer_1.contains(4)&& palyer_1.contains(7))
                Winner=1;
            else if(palyer_2.contains(1)&& palyer_2.contains(4)&& palyer_2.contains(7))
                Winner=2;
            else if(palyer_1.contains(2)&& palyer_1.contains(5)&& palyer_1.contains(8))
                Winner=1;
            else if(palyer_2.contains(2)&& palyer_2.contains(5)&& palyer_2.contains(8))
                Winner=2;
            else if(palyer_1.contains(3)&& palyer_1.contains(6)&& palyer_1.contains(9))
                Winner=1;
            else if(palyer_2.contains(3)&& palyer_2.contains(6)&& palyer_2.contains(9))
                Winner=2;
            else if(palyer_1.contains(4)&& palyer_1.contains(5)&& palyer_1.contains(6))
                Winner=1;
            else if(palyer_2.contains(4)&& palyer_2.contains(5)&& palyer_2.contains(6))
                Winner=2;
            else if(palyer_1.contains(7)&& palyer_1.contains(8)&& palyer_1.contains(9))
                Winner=1;
            else if(palyer_2.contains(7)&& palyer_2.contains(8)&& palyer_2.contains(9))
                Winner=2;
            else if(palyer_1.contains(3)&& palyer_1.contains(5)&& palyer_1.contains(7))
                Winner=1;
            else if(palyer_2.contains(3)&& palyer_2.contains(5)&& palyer_2.contains(7))
                Winner=2;
            else if(palyer_1.contains(1)&& palyer_1.contains(5)&& palyer_1.contains(9))
                Winner=1;
            else if(palyer_2.contains(1)&& palyer_2.contains(5)&& palyer_2.contains(9))
                Winner=2;
            else if (Checked_button.size()==9)
                Winner=-1;
        }
            if (Winner == 1){
                Toast toast = Toast.makeText(getApplicationContext(),"The Winner is :"+Winner, Toast.LENGTH_LONG);
                toast.show();
                for(int i=0;i<3;i++){     //GAMEOVER ;Close all the button
                    for(int j=0;j<3;j++)
                    {
                        buttons_select[i][j].setEnabled(false);
                    }
                }
                GameOver(Winner);
                p1++;
            }
            else if(Winner ==2) {
                Toast toast = Toast.makeText(getApplicationContext(), "The Winner is :" + Winner, Toast.LENGTH_LONG);
                toast.show();
                for(int i=0;i<3;i++){     //GAMEOVER ;Close all the button
                    for(int j=0;j<3;j++)
                    {
                        buttons_select[i][j].setEnabled(false);
                    }

                }
                GameOver(Winner);
                p2++;
            }
            else if(Winner == -1) {
                Toast toast = Toast.makeText(getApplicationContext(),"No one is Winner ", Toast.LENGTH_LONG);
                toast.show();
                for(int i=0;i<3;i++){     //GAMEOVER ;Close all the button
                    for(int j=0;j<3;j++)
                    {
                        buttons_select[i][j].setEnabled(false);
                    }
                }
                GameOver(Winner);
            }
        sp1.setText(String.valueOf(p1));
        sp2.setText(String.valueOf(p2));
    }

    //Game_Over
    public void GameOver(int Who_Winner){
       dialog= new AlertDialog.Builder(MainActivity.this);
       if(Who_Winner!=-1 && Who_Winner!=0)
        dialog.setTitle("The Winner is player number :"+Who_Winner);
       else if(Who_Winner ==-1 && Winner !=0)
           dialog.setTitle("No one is Winner !");

        dialog.setMessage("Do want to play againe ?");
        dialog.setCancelable(true);
        dialog.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Continue with some operation
                        for(int i=0;i<3;i++){     //GAMEOVER ;Close all the button
                            for(int j=0;j<3;j++)
                            {
                                buttons_select[i][j].setImageResource(R.drawable.ffff);
                                buttons_select[i][j].setScaleType(ImageButton.ScaleType.CENTER);
                                buttons_select[i][j].setEnabled(true);
                            }
                        }
                        Winner=0;
                        palyer_1.clear();
                        palyer_2.clear();
                        Checked_button.clear();
                        dialog.cancel();

                    }
                });

        dialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });


        AlertDialog alert = dialog.create();
        alert.show();
    }
}
