package com.example.yulpuma.accaptcha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bedroomDresserInfo(View view){
        Intent intent = new Intent(this,PaymentWebPage.class);
        intent.putExtra("ID","main");
        startActivity(intent);
    }



}
