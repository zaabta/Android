package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.math.BigInteger;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText txdecimal,txbinary,txoctal,txhexdecimal;
    EditText edinput;
    Button btcov;
    ImageButton change_language;
    Spinner spinner;
    String dil;
    ArrayAdapter<String> adapter;
    String resultofbinary,resultofoctal,resultofhexadecimal;
    int resultofdecimal;
    String input;
    int dec;


    private void showlanguagedialoge(){

        final String lang[]={"عربي","Türkç","English"};
        AlertDialog.Builder mybuild=new AlertDialog.Builder(MainActivity.this);
        mybuild.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    setLocale("ar");
                    recreate();
                }

                else if (which==1)
                {
                    setLocale("tr");
                    recreate();
                }
                else
                {
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();

            }
        });
        AlertDialog mydialog=mybuild.create();
        mydialog.show();

    }

    private void setLocale(String lang) {
        dil=lang;
        Locale locale=new Locale(lang);
        locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;

        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("my_lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("my_lang","");
        setLocale(language);

    }



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadLocale();

        setContentView(R.layout.activity_main);

        change_language=(ImageButton) findViewById(R.id.lang);
        txbinary=(EditText) findViewById(R.id.binery);
        txdecimal=(EditText) findViewById(R.id.decimal);
        txoctal=(EditText) findViewById(R.id.octal);
        txhexdecimal=(EditText) findViewById(R.id.hexadecimal);
        btcov=(Button)findViewById(R.id.covert);
        spinner=(Spinner)findViewById(R.id.spner);
        edinput=(EditText)findViewById(R.id.input);
        String arry []={"Input Type","Decimal","Binary","Octal","Hexadecimal"};
        String arryar[]={"نظام العد","نظام العد العشري","نظام العد الثنائي","نظام العد الثماني","نظام العد السداسي عشر"};
        String arrytr[]={"sayi sistemi","Onlu sayi","Ikili sayi","Sekizli sayi","Onaltılı sayi"};

        if(dil=="ar") {
            ((EditText)txhexdecimal).setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arryar);
            spinner.setAdapter(adapter);
        }
        else if(dil=="tr"){
            adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrytr);
            spinner.setAdapter(adapter);
        }
        else {
            adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arry);
            spinner.setAdapter(adapter);

        }

change_language.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        showlanguagedialoge();

    }
});

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        edinput.setEnabled(false);
                        edinput.setInputType(InputType.TYPE_NULL);

                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                    case 1:
                        edinput.setEnabled(true);
                        edinput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                        break;
                    case 2:
                        edinput.setEnabled(true);
                        edinput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                        break;
                    case 3:
                        edinput.setEnabled(true);
                        edinput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                        break;
                    case 4:
                        edinput.setEnabled(true);
                        edinput.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btcov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input=edinput.getText().toString();
                //input
                String str=spinner.getSelectedItem().toString();



                if(str == "Decimal"||str=="نظام العد العشري"||str=="Onlu sayi") {

                    if(input.matches(""))
                    {
                        try {
                            edinput.setText("");
                            Toast.makeText(getApplicationContext(), "Value of Decimal must be contained number",
                                    Toast.LENGTH_SHORT).show();
                            txbinary.setText("");
                            txdecimal.setText("");
                            txoctal.setText("");
                            txhexdecimal.setText("");
                        }
                        catch (NumberFormatException e){

                        }

                    }
                    else {
                        try {
                            dec=Integer.parseInt(input);
                            resultofdecimal=dec;
                            resultofbinary=Integer.toBinaryString(dec);
                            resultofoctal = Integer.toOctalString(dec);
                            resultofhexadecimal=Integer.toHexString(dec);


                            txbinary.setText(resultofbinary);
                            txdecimal.setText(String.valueOf(resultofdecimal));
                            txoctal.setText(resultofoctal);
                            txhexdecimal.setText(resultofhexadecimal);
                        }
                        catch (NumberFormatException e){

                        }

                    }
                }
                else if(str=="Binary"||str=="نظام العد الثنائي"||str=="Ikili sayi"){

                    if (input.matches("[01]+")) {
                        try {
                            resultofbinary = input;
                            int binary = Integer.parseInt(input);
                            int decimal = 0;
                            int n = 0;
                            while (true) {
                                if (binary == 0) {
                                    break;
                                } else {
                                    int temp = binary % 10;
                                    decimal += temp * Math.pow(2, n);
                                    binary = binary / 10;
                                    n++;
                                }
                            }
                            resultofdecimal = decimal;
                            resultofoctal = Integer.toOctalString(Integer.parseInt(input, 2));
                            resultofhexadecimal = new BigInteger(input, 2).toString(16);
                        } catch (NumberFormatException e) {
                            //Log it if needed
                            //default fallback value;
                        }

                        txbinary.setText(resultofbinary);
                        txdecimal.setText(String.valueOf(resultofdecimal));
                        txoctal.setText(resultofoctal);
                        txhexdecimal.setText(resultofhexadecimal);

                    }
                    else{
                        edinput.setText("");
                        Toast.makeText(getApplicationContext(), "Value of binary must be contained 0 or 1",
                                Toast.LENGTH_SHORT).show();
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                    }

                }
                else  if(str =="Octal"|| str=="Sekizli sayi" || str=="نظام العد الثماني")
                {
                    if(input.matches( "[0-7]*$" )){
                        try {
                            resultofoctal=input;
                            resultofdecimal=Integer.parseInt(input,8);
                            int i= Integer.parseInt(input,8);
                            resultofbinary=Integer.toBinaryString(i);
                            int decnum = Integer.parseInt(input, 8);
                            resultofhexadecimal = Integer.toHexString(decnum);
                        }
                        catch (NumberFormatException e){

                        }
                        txbinary.setText(resultofbinary);
                        txdecimal.setText(String.valueOf(resultofdecimal));
                        txoctal.setText(resultofoctal);
                        txhexdecimal.setText(resultofhexadecimal);
                    }
                    else{
                        edinput.setText("");
                        Toast.makeText(getApplicationContext(), "Value of Octal must be 0 to 7",
                                Toast.LENGTH_SHORT).show();
                        txbinary.setText("");
                        txdecimal.setText("");
                        txoctal.setText("");
                        txhexdecimal.setText("");
                    }

                    }
                else if (str=="Hexadecimal"||str=="Onaltılı sayi"||str=="نظام العد السداسي عشر")
                {
                    if(input.matches("^[0-9A-Fa-f]+$")){
                        try {
                            //errooro****
                            resultofhexadecimal=input;
                            resultofdecimal=Integer.parseInt(input,16);
                            int i = Integer.parseInt(input);
                            resultofbinary  = Integer.toBinaryString(i);
                            resultofoctal = Integer.toOctalString(Integer.parseInt(input,16));

                        }
                        catch (NumberFormatException e){

                        }
                        txdecimal.setText(String.valueOf(resultofdecimal));
                        txbinary.setText(resultofbinary);
                        txoctal.setText(resultofoctal);
                        txhexdecimal.setText(resultofhexadecimal);

                    }
                    else {
                            edinput.setText("");
                            Toast.makeText(getApplicationContext(), "Value of Hexadecimal must be A to F",
                                    Toast.LENGTH_SHORT).show();
                            txbinary.setText("");
                            txdecimal.setText("");
                            txoctal.setText("");
                            txhexdecimal.setText("");

                    }

                }
            }
        });
    }

}

