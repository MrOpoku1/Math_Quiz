package com.example.mathquiztrivia;
import android.widget.ImageView;

public class Question {

private final  String question;

private final boolean answer;

private final ImageView image;
    public Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }
    public Question(String question, boolean answer, ImageView image){
        this.question=question;
        this.answer =  answer;
        this.image = image;
    }
    public void setImage(ImageView image){
        ((ImageView)findViewById(R.id.picture)).setImageResource(image);
    }
    public boolean getAnswer(){
        return answer;
    }
    public String getQuestion(){
        return question;
    }
    public ImageView getImage(){
        return image;
    }

}
