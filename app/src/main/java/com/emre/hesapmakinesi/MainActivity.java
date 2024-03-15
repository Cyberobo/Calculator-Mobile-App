package com.emre.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView output;
    MaterialButton cButton ,parantezButton,ustButton,dotButton,equalButton;
    MaterialButton zeroButton,oneButton,twoButton,threeButton,fourButton,fiveButton,sixButton,sevenButton,eightButton,nineButton;
    MaterialButton backspaceButton,carpmaButton,toplamaButton,cikarmaButton,bolmeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //uygulamayi fullscreen yapmayi saglar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.islem);
        output=findViewById(R.id.cevap);

        input.setShowSoftInputOnFocus(false);
        output.setShowSoftInputOnFocus(false);


        cButton=findViewById(R.id.c_button);
        ustButton=findViewById(R.id.ust_button);
        equalButton=findViewById(R.id.equal_button);
        parantezButton=findViewById(R.id.parantez_button);
        bolmeButton=findViewById(R.id.bolme_button);
        backspaceButton=findViewById(R.id.backspace_button);
        carpmaButton=findViewById(R.id.carpma_button);
        toplamaButton=findViewById(R.id.toplama_button);
        cikarmaButton=findViewById(R.id.cikarma_button);
        dotButton=findViewById(R.id.dot_button);
        zeroButton=findViewById(R.id.zero_button);
        oneButton=findViewById(R.id.one_button);
        twoButton=findViewById(R.id.two_button);
        threeButton=findViewById(R.id.three_button);
        fourButton=findViewById(R.id.four_button);
        fiveButton=findViewById(R.id.five_button);
        sixButton=findViewById(R.id.six_button);
        sevenButton=findViewById(R.id.seven_button);
        eightButton=findViewById(R.id.eight_button);
        nineButton=findViewById(R.id.nine_button);

    }


    public void updateText(String strToAdd){
        String oldstr=input.getText().toString();
        int cursorPos=input.getSelectionStart();
        String leftStr=oldstr.substring(0,cursorPos);
        String rigthStr=oldstr.substring(cursorPos);
        input.setText(String.format("%s%s%s",leftStr,strToAdd,rigthStr));
        input.setSelection(cursorPos+1);
    }

    //number buttons
    public void zeroButtonClick(View view){
        updateText("0");
    }

    public void oneButtonClick(View view){
        updateText("1");
    }

    public void twoButtonClick(View view){
        updateText("2");
    }

    public void threeButtonClick(View view){
        updateText("3");
    }

    public void fourButtonClick(View view){
        updateText("4");
    }

    public void fiveButtonClick(View view){
        updateText("5");
    }

    public void sixButtonClick(View view){
        updateText("6");
    }

    public void sevenButtonClick(View view){
        updateText("7");
    }

    public void eightButtonClick(View view){
        updateText("8");
    }


    public void nineButtonClick(View view){
        updateText("9");
    }


    //letter buttons
    public void cButtonClick(View view){
        input.setText("");
        output.setText("");
    }


    public void backspaceButtonClick(View view){
        //imlecin posizyonu
        int cursorPos=input.getSelectionStart();
        //inputa girdginimiz textin uzunlugu
        int textLen=input.getText().length();

        //imlecin pozisyonu ve textin uzunlugu sifirdan farkliysa
        if(cursorPos !=0 && textLen !=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) input.getText();
            //textin harfini bir azaltir
            selection.replace(cursorPos-1,cursorPos,"");
            input.setText(selection);
            //imleci gunceller
            input.setSelection(cursorPos-1);
        }
    }


    public void equalButtonClick(View view){
        String calculate = input.getText().toString();

        calculate=calculate.replaceAll("÷","/");
        calculate=calculate.replaceAll("×","*");

        Expression exp=new Expression(calculate);

        double result = exp.calculate();

        if (result == (int) result) {
            // Sonuç tam sayı ise, ondalık kısmı yok sayar
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');

            DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
            String formattedResult = decimalFormat.format(result);
            output.setText(formattedResult);
        } else {
            // Sonuç ondalık sayı ise, virgüllü olarak sayar
            output.setText(String.valueOf(result));
        }

        input.setText("");
    }

    public void parantezButtonClick(View view){
        //imlecin pozisyonu
        int cursorPos=input.getSelectionStart();
        int openPar=0;
        int closePar=0;
        int textLen=input.getText().length();

        for(int i=0;i<cursorPos;i++){
            if(input.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;
            }
            if(input.getText().toString().substring(i,i+1).equals(")")){
                closePar+=1;
            }
        }

        if(openPar == closePar || input.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
            input.setSelection(cursorPos+1);
        }

        else if(closePar < openPar && !input.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");

        }

        input.setSelection(cursorPos+1);
    }



    //calculate buttons
    public void toplamaButtonClick(View view){
        updateText("+");
    }

    public void cikarmaButtonClick(View view){
        updateText("-");
    }

    public void carpmaButtonClick(View view){
        updateText("×");
    }

    public void bolmeButtonClick(View view){
        updateText("÷");
    }

    public void ustButtonClick(View view){
        updateText("^");
    }

    public void dotButtonClick(View view){
        updateText(".");
    }




















}