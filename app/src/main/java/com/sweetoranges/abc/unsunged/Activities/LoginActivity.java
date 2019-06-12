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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.ChallengeFragment.ChallengeFragment;
import com.sweetoranges.abc.unsunged.MainActivity;

import com.sweetoranges.abc.unsunged.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    //"Unsunged is Music"+"Redefined", "Reimagined", "Experience Redesigned","Fresh","Upcoming"

    AppCompatImageView BackScreen;
    AppCompatButton OkB,verify;
    private SignInButton Loginpage;
    private TextView Status,PhoneVerify;
    private ImageView imgProfilePic;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    private ProgressDialog mProgressDialog;
    private ProgressBar progress;
    private AppCompatEditText phoneNumber;
    int i=0;
//    public static final int RC_SIGN_IN = 001;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private String verificationid;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BackScreen=(AppCompatImageView)findViewById(R.id.loginScreen);
        Loginpage=(SignInButton)findViewById(R.id.loginButton);
        progress=(ProgressBar)findViewById(R.id.progress);
        Status=(TextView)findViewById(R.id.statustext);
        PhoneVerify=(TextView)findViewById(R.id.phoneVerify);
        imgProfilePic = (ImageView) findViewById(R.id.ProfileImage);
        OkB=(AppCompatButton)findViewById(R.id.ok);
        verify=(AppCompatButton)findViewById(R.id.verify);
        phoneNumber=(AppCompatEditText)findViewById(R.id.phoneNumber);
        phoneNumber.setVisibility(View.GONE);
        OkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePhoneNumberAndCode()) {
                    return;
                }
                startPhoneNumberVerification(phoneNumber.getText().toString());
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateSMSCode()) {
                    return;
                }

                verifyPhoneNumberWithCode(verificationid, phoneNumber.getText().toString());
            }
        });
        mAuth = FirebaseAuth.getInstance();

        showText();
        startColorFade(BackScreen);

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        Loginpage.setOnClickListener(v -> {
            progress.setVisibility(View.VISIBLE);
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SIGN_IN);
        });
        PhoneVerify.setOnClickListener(v -> {
            if(Loginpage.getVisibility()==View.VISIBLE){
            Loginpage.setVisibility(View.GONE);
            phoneNumber.setVisibility(View.VISIBLE);
            PhoneVerify.setText("Gmail Id ?");}
            else{
                Loginpage.setVisibility(View.VISIBLE);
                phoneNumber.setVisibility(View.GONE);
                PhoneVerify.setText("Phone Number ?");
            }
        });
    }
    private void showText(){
    Handler handler = new Handler();
    final Runnable r = new Runnable() {
        public void run() {
            switch (i) {
                case 0:
                    Status.setText("Let's Redefine Music");i++;
                case 1:
                    Status.setText("Login To Another Space Of Music");i++;
                case 2:
                    Status.setText("Originals Have New Meaning Now");
                case 4:
                    Status.setText("Failed To Connect");i++;
                case 6:
                    Status.setText("Check Your Network");i++;
                case 7:
                    Status.setText("And Try Again");i++;
                case 8:
                    Status.setText("Use Your Phone Number To Login");i++;
                case 9:
                    Status.setText("Unsunged: Let's Make Music for Every Audience");
                case 10:
                    Status.setText("Login Successful");i++;
                case 11:
                    Status.setText("Entering Another Space Of Music");
                default:
                    Status.setText("Login Using EmailId or Phone Number");
            }
            handler.postDelayed(this, 1500);
        }
    };

    handler.postDelayed(r, 1500);
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
    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { i=4;
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
            progress.setVisibility(View.GONE);
            if( acct.getDisplayName()!=null){
                SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                editor.putBoolean("logininfo", true);
                editor.putString("username",acct.getDisplayName());
                editor.putString("email",acct.getEmail());
                editor.apply();
            }
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl()
            SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
            if (!prefs.getBoolean("logininfo", false)) {
                // startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
        if (signedIn) {
            final Handler handler = new Handler();
            handler.postDelayed(() -> {i=10;
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);}, 2000);
        } else {
            Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show();
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
@Override
protected void onStart() {
    super.onStart();

    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }
    };
}

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                phoneNumber.setError("Invalid code.");
                            }
                        }
                    }
                });
    }

    private boolean validatePhoneNumberAndCode() {
        String code = phoneNumber.getText().toString();
        if (TextUtils.isEmpty(code)) {
            phoneNumber.setError("Enter verification Code.");
            return false;
        }
        return true;
    }

    private boolean validateSMSCode(){
        String code = phoneNumber.getText().toString();
        if (TextUtils.isEmpty(code)) {
            phoneNumber.setError("Enter verification Code.");
            return false;
        }
        return true;
    }

}
