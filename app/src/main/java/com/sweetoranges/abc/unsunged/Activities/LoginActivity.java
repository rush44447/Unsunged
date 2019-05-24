package com.sweetoranges.abc.unsunged.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;
import com.google.android.gms.common.api.Status;
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
AppCompatImageView BackScreen;
    private SignInButton Loginpage;
    private TextView Status,mailId;
    private ImageView imgProfilePic;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BackScreen=(AppCompatImageView)findViewById(R.id.loginScreen);
        Loginpage=(SignInButton)findViewById(R.id.loginButton);
        Status=(TextView)findViewById(R.id.statustext);
        mailId=(TextView)findViewById(R.id.mailid);
        imgProfilePic = (ImageView) findViewById(R.id.ProfileImage);
//        Glide.with(getApplicationContext()).load(R.drawable.musica).into(BackScreen);
        Loginpage.setOnClickListener(v -> {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SIGN_IN);
        });
        startColorFade(BackScreen);
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    private void startColorFade(View v){
        int colorStart=0xFFFF007F;
        int colorEnd = 0x8806239f;
        ValueAnimator colorAnim= ObjectAnimator.ofInt(v,"backgroundColor",colorStart,colorEnd);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(10);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    @Override public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);}
    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { Status.setText("Failed to Connect");
        Toast.makeText(this, String.valueOf(connectionResult), Toast.LENGTH_SHORT).show();}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}