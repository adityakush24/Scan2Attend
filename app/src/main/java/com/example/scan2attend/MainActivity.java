package com.example.scan2attend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    Button loginButton;
    TextView register,forgotPass,getStarted;
    Animation slideup;
    EditText email,password,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        register = (TextView)findViewById(R.id.register);
        confirm_password = (EditText)findViewById(R.id.confirm_password);
        forgotPass = (TextView)findViewById(R.id.forgot_password);
        slideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slideup.setAnimationListener(this);
        RippleBackground rippleBackground= findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        getStarted = findViewById(R.id.getStarted);
        register.setText("Don't have an account? Sign Up.");
        loginButton = (Button)findViewById(R.id.loginbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(register.getText().equals("Don't have an account? Sign Up.")) {
                    Animation move = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, -150);
                    Animation mov = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, -150);
                    mov.setDuration(1000);
                    mov.setFillAfter(true);
                    move.setDuration(1000);
                    move.setFillAfter(true);
                    email.startAnimation(move);
                    password.startAnimation(mov);
                    confirm_password.setVisibility(View.VISIBLE);
                    confirm_password.startAnimation(slideup);
                    getStarted.setVisibility(View.GONE);
                    forgotPass.setVisibility(View.GONE);
                    loginButton.setBackgroundResource(R.drawable.sign_up_btn);
                    register.setText("Already have an account? Sign In.");
                }
                else {
                    Animation move = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, 0);
                    move.setDuration(1500);
                    move.setFillAfter(true);
                    email.startAnimation(move);
                    password.startAnimation(move);
                    confirm_password.setVisibility(View.GONE);
                    forgotPass.setVisibility(View.VISIBLE);
                    loginButton.setBackgroundResource(R.drawable.loginbtn);
                    register.setText("Don't have an account? Sign Up.");
                }
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public void btnAction(View v)
    {
        if(register.getText().equals("Don't have an account? Sign Up.")) {
            Toast.makeText(this, "Sign In", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            intent.putExtra("email",email.getText().toString());
            intent.putExtra("pass",password.getText().toString());
            intent.putExtra("cnfrm_pass",confirm_password.getText().toString());
            startActivity(intent);
        }
    }
}