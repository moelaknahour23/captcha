package com.example.yulpuma.accaptcha;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;

public class PaymentWebPage extends AppCompatActivity implements OnClickListener{
    private int attempts = 0;
    private boolean cameFromAccaptcha = false;
    private boolean can_pay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_page);
        Intent intent = this.getIntent();
        if("accaptcha".equals(intent.getExtras().getString("ID"))) {
            attempts = intent.getExtras().getInt("ATTEMPTS");
            cameFromAccaptcha = true;
        }

        //accaptcha button security
        Button accaptcha = (Button)findViewById(R.id.captcha);
        if(cameFromAccaptcha){
            if(intent.getExtras().getBoolean("PASS") == true){
                can_pay = true;
                accaptcha.setBackgroundColor(Color.rgb(0,192,0));//green if right
            }else{
                accaptcha.setBackgroundColor(Color.rgb(255, 0, 0));//red if wrong
                attempts = attempts + 1;
                if (attempts < 3) {
                    accaptcha.setText("captcha " + "(" + attemptsLeft() + ")");
                    accaptcha.setOnClickListener(this);
                } else {
                    accaptcha.setText("Try Again Later");
                }
            }
        }else{
            accaptcha.setOnClickListener(this);
        }

        Button paybutton = (Button)findViewById(R.id.pay_button);
        paybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.pay_button) {
            if(can_pay) {
                Intent intent = new Intent(PaymentWebPage.this, Confirmation.class);
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.captcha){
            Intent intent = new Intent(PaymentWebPage.this, Accaptcha2.class);
            intent.putExtra("ATTEMPTS", attempts);
            startActivity(intent);
        }
    }

    //helper function to show attemps left
    public String attemptsLeft(){
        Integer rtn = 3 - attempts;
        return rtn.toString();
    }
}
