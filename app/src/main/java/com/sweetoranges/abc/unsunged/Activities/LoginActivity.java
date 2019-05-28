package com.sweetoranges.abc.unsunged.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.io.InputStream;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
AppCompatImageView BackScreen;
    private SignInButton Loginpage;
    private TextView Status;
    private ImageView imgProfilePic;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    private ProgressDialog mProgressDialog;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BackScreen=(AppCompatImageView)findViewById(R.id.loginScreen);
        Loginpage=(SignInButton)findViewById(R.id.loginButton);
        progress=(ProgressBar)findViewById(R.id.progress);
        Status=(TextView)findViewById(R.id.statustext);
        imgProfilePic = (ImageView) findViewById(R.id.ProfileImage);

//        Glide.with(getApplicationContext()).load(R.drawable.musica).into(BackScreen);
        Loginpage.setOnClickListener(v -> {
        progress.setVisibility(View.VISIBLE);
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

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfolly, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Status.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            imgProfilePic.setImageResource(R.drawable.imgview);
            if( acct.getDisplayName()!=null){
                SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                editor.putBoolean("logininfo", true);
                editor.putString("username",acct.getDisplayName());
                editor.apply();
            }
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl()
            SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
            if (!prefs.getBoolean("logininfo", false)) {
                // startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(), "fasle", Toast.LENGTH_SHORT).show();
            }

            if(acct.getPhotoUrl() != null)
                new LoadProfileImage(imgProfilePic).execute(acct.getPhotoUrl().toString());

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    private void updateUI(boolean signedIn) {
        if (signedIn) {        progress.setVisibility(View.GONE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);}
            }, 2000);
        } else {
            Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show();
        }
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getApplicationContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }

    }

    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... uri) {
            String url = uri[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            if (result != null) {
                Bitmap resized = Bitmap.createScaledBitmap(result,200,200, true);
              //  bmImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(getApplicationContext(),resized,250,200,200, false, false, false, false));

            }
        }
    }
//    signOutButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(Status status) {
//                            updateUI(false);
//                        }
//                    });
//        }
//    });
}
