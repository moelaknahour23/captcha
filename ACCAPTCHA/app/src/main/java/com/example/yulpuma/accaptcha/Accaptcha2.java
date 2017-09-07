package com.example.yulpuma.accaptcha;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static com.example.yulpuma.accaptcha.R.id.button_1;
import static com.example.yulpuma.accaptcha.R.id.button_3;

public class Accaptcha2 extends AppCompatActivity implements OnClickListener{

    private int attempts;
    private int total_count = 0;
    private int count_right = 0;
    private int count_attempted = 0;
    private int right_button;

    Vibrator mVibrator;

    int dot = 100; // Length of a Morse Code "dot" in milliseconds
    int dash = 250; // Length of a Morse Code "dash" in milliseconds
    int short_gap = 100; // Length of Gap Between dots/dashes
    int medium_gap = 200; // Length of Gap Between Letters
    int long_gap = 500; // Length of Gap Between Words

    long[] pattern = { 0, // Start immediately
            dot, short_gap, dot, short_gap, dot, // s
            medium_gap, dash, short_gap, dash, short_gap, dash, // o
            medium_gap, dot, short_gap, dot, short_gap, dot, // s
            long_gap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accaptcha2);

        //set up widgets
        TextView textview = (TextView)findViewById(R.id.textView);
        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        Button button_2 = (Button)findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        Button button_3 = (Button)findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        Button button_4 = (Button)findViewById(R.id.button_4);
        button_4.setOnClickListener(this);





        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mpSound = MediaPlayer.create(this, R.raw.sound_1);


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpSound.start();
                amItheRightButton(R.id.button_1);
                mVibrator.vibrate(pattern,-1);

            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpSound.start();
                amItheRightButton(R.id.button_2);
                mVibrator.vibrate(pattern,-1);

            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpSound.start();
                amItheRightButton(R.id.button_3);
                mVibrator.vibrate(pattern,-1);

            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpSound.start();
                amItheRightButton(R.id.button_4);
                mVibrator.vibrate(pattern,-1);

            }
        });



        //simple data storage
        int[] button_array = {R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4};
        final String[] RandomText = {"ROSE","JASMINE","LILLY","ALABAMA","ALASKA","GEORGIA","ARIZONA","CALIFORNIA","COLORADO","CONNECTICUT","DELAWARE","FLORIDA","GEORGIA","HAWAII","IDAHO","ILLINOIS","INDIANA","IOWA","KANSAS","LOUISIANA","MARYLAND",
                "COW","ANT","ANTELOPE","APE","BEE","BEAR","PIG","BUFFALO","ZEBRA","PIGEON","DOVE","SNAKE","TIGER","LION","HOUSE","BAT","BUTTERFLY","CAMEL","KANGAROO","CHICKEN","CRAB","DEER","DINOSAUR","DOLPHIN","DONKEY","DRAGON","DUCK","EAGLE","FISH","CAR","VAN","BUS","TRAIN","PLANE","SHIP","PEN","DONUT","CUP",
                "PLATE","MOUSE","MUG","CHAIR","TABLE","PENCIL","CARROT","BEETROOT","BULL","BAG","LOVE","PAPER","MEAT","MILK","CAKE","FRUIT","FOX","MOON","SUN","MOM","DAD","DAY","NIGHT","SLEEP","SNOW","RED","GREEN","PINK","YELLOW","BLUE","BLACK","ORANGE","HAPPY","CRY","ANGRY","PAIN","CANDY","TREE",
                "TRUCK","PLANT","ICE","CHILD","ADULT","KID","HILL","ROAD","WATER","WEEK","ROOM","SCHOOL","JOB","FALL","EGG","KING","WORLD","WALL","QUEEN","EAT","ZOO","GYM","DOOR","DOLL","RAIN","WARM","WASH","WALK","RUN","ROCK","LAKE","OCEAN","SEA","DRESS","DRIVE","DRY","START","END"};

        //random functions to select from data
        int random1 = (int) (Math.random()*135);
        int random2 = (int) (Math.random()*135);
        while(random2 == random1){
            random2 = (int)(Math.random()*135);
        }
        int random3 = (int) (Math.random()*135);
        while(random3 == random1 && random3 == random2){
            random3 = (int)(Math.random()*135);
        }
        int random4 = (int) (Math.random()*135);
        while(random4 == random1 && random4 == random2 && random4 == random3){
            random4 = (int)(Math.random()*135);
        }
        String[] RandomText2 = new String[]{RandomText[random1], RandomText[random2], RandomText[random3], RandomText[random4]};
        int random_final = (int) (Math.random()*4);

        //set up global variables
        Intent intent = this.getIntent();
        attempts = intent.getExtras().getInt("ATTEMPTS");
        setTotalCount(randomNum(4));
        right_button = button_array[randomNum(4)-1];
        Button right_bttn = (Button)findViewById(right_button);

        //SetScreen
        button_1.setText(RandomText2[0]);
        button_1.setContentDescription(RandomText2[0]);
        button_2.setText(RandomText2[1]);
        button_2.setContentDescription(RandomText2[1]);
        button_3.setText(RandomText2[2]);
        button_3.setContentDescription(RandomText2[2]);
        button_4.setText(RandomText2[3]);
        button_4.setContentDescription(RandomText2[3]);
        textview.setText("Please push "+ right_bttn.getText() + " " + getTotalCount() +" Times");
        textview.setContentDescription("Please push "+ right_bttn.getText() + " " + getTotalCount() +" Times");
    }

    public void onClick(View v) {


        if (v.getId() == button_1) {
            amItheRightButton(button_1);

        }
        if (v.getId() == R.id.button_2) {
            amItheRightButton(R.id.button_2);
        }
        if (v.getId() == button_3) {
            amItheRightButton(button_3);
        }
        if (v.getId() == R.id.button_4) {
            amItheRightButton(R.id.button_4);
        }
    }


    //code to check if the right button is pressed
    public void amItheRightButton(int aButton){
        if (aButton == this.right_button){
            addToCountRight();
            Toast.makeText(getApplicationContext(),"Yay",Toast.LENGTH_LONG).show();
        }else{
            check_if_right();

            // show the error if is it wrong
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();

        }
    }

    public int getTotalCount(){
        return this.total_count;
    }

    private void setTotalCount(int number){
        this.total_count = number;
    }

    public int getCountRight(){
        return this.count_right;
    }

    public int getCountAttempted(){
        return this.count_attempted;
    }

    public void addCountAttempted(){
        if(count_attempted >= getTotalCount()){
            //return with false
            check_if_right();
        }else{
            this.count_attempted = this.count_attempted + 1;
        }
    }

    public void addToCountRight(){
        this.count_right = this.count_right + 1;
        if(getTotalCount() <= getCountRight()) {
            check_if_right();
        }
    }

    private void check_if_right(){


        final AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator);



        if(getTotalCount() == getCountRight()){


            Intent intent = new Intent(this, PaymentWebPage.class);
            intent.putExtra("PASS", true);
            intent.putExtra("ID", "accaptcha");
            intent.putExtra("ATTEMPTS",attempts);
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            set1.setTarget((Button) findViewById(this.right_button));
            set1.start();
            startActivity(intent);
            finish();





        }else{
            Intent intent = new Intent(this, PaymentWebPage.class);
            intent.putExtra("PASS", false);
            intent.putExtra("ID", "accaptcha");
            intent.putExtra("ATTEMPTS",attempts);
            startActivity(intent);
        }
    }

    //Random Function: Carlos
    public int randomNum(int max){
        Random r = new Random();
        int randNum = r.nextInt(max);
        randNum = randNum + 1;
        return randNum;
    }

}
