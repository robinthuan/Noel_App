package com.example.administrator.noelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    public static final int MIN_DISTANCE= 100;
    int i=1;
    int x1=0,x2=0;
    ImageView imageView;
   ArrayList<Integer> listImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView)findViewById(R.id.background);
        listImage=new ArrayList<>();
        listImage=listImage();
        Toast.makeText(this,String.valueOf(listImage.size()),Toast.LENGTH_LONG).show();
        imageView.setImageResource(listImage.get(1));
    }
   public ArrayList<Integer> listImage(){
        ArrayList<Integer> list=new ArrayList<>();
       Field[] fields=R.drawable.class.getFields();
       for(Field field:fields){
           if(field.getName().contains("pic")){
               list.add(getResources().getIdentifier(field.getName(),"drawable",getPackageName()));
           }
       }
    return list;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=(int)event.getX();
                break;
            case MotionEvent.ACTION_UP:
                 x2=(int) event.getX();

                 if(x2>x1){
                     if(x2-x1>MIN_DISTANCE){

                        if(i<listImage.size()-1){
                            i++;
                            imageView.setImageResource(listImage.get(i));
                        }
                     }
                 }
                 else {
                     if((x1-x2)>MIN_DISTANCE){
                         if(i>1){
                             i--;
                             imageView.setImageResource(listImage.get(i));
                         }
                     }
                 }
                break;
                 default:break;

        }
        return true;
    }
}
