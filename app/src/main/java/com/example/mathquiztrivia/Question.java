package com.example.mathquiztrivia;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Question extends AppCompatActivity {

private final  String question;

private final boolean answer;

private  Drawable image;
private ImageView picture;
    public Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }
    public Question(String question, boolean answer, Drawable image){
        this.question=question;
        this.answer =  answer;
        this.image = image;
    }
    public static void setImage(Drawable image, ImageView picture){
        picture.setImageDrawable(image);
    }
    public boolean getAnswer(){
        return answer;
    }
    public String getStatement(){
        return question;
    }
    public Drawable getImage(){
        return image;
    }

}
