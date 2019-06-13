package com.sweetoranges.abc.unsunged.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
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
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.tabs.TabLayout;
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.ChallengeFragment.ChallengeFragment;
import com.sweetoranges.abc.unsunged.LoginFragment.MailFragment;
import com.sweetoranges.abc.unsunged.LoginFragment.NumberFragment;
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

public class LoginActivity extends AppCompatActivity {
    //"Unsunged is Music"+"Redefined", "Reimagined", "Experience Redesigned","Fresh","Upcoming"

    AppCompatImageView BackScreen;
    private TextView Status;
    private ImageView imgProfilePic;
    private static final int RC_SIGN_IN = 1;
    private ProgressDialog mProgressDialog;
    public ProgressBar progress;
    private TabLayout tabLayout;
    int i=0;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private String verificationid;
    private FirebaseAuth mAuth;
    String contact="9869344447";
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    //    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BackScreen=(AppCompatImageView)findViewById(R.id.loginScreen);
        progress=(ProgressBar)findViewById(R.id.progress);
        Status=(TextView)findViewById(R.id.statustext);
        imgProfilePic = (ImageView) findViewById(R.id.ProfileImage);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Number"));
        tabLayout.addTab(tabLayout.newTab().setText("Email"));
        replaceFragment(new NumberFragment());
        mAuth = FirebaseAuth.getInstance();
        showText();
        startColorFade(BackScreen);

        String abc= GetCountryZipCode();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new NumberFragment());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new MailFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public String GetCountryZipCode(){
        String CountryID="";
        String CountryZipCode="";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        Toast.makeText(this, CountryID, Toast.LENGTH_SHORT).show();
        String[] rl=this.getResources().getStringArray(R.array.DialingCountryCode);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];
                break;
            }
        }
        return CountryZipCode;
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyVerificationCode(code);
                     //2nd
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
        }
    };

    public void verifyVerificationCode(String code) {     //3rd
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {                                         // 4th  step
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                            SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                            editor.putBoolean("logininfo", true);
                            editor.apply();
                        final Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            getIntent().putExtra("number",contact);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);}, 2000);
                    } else {
                        String message = "Somthing is wrong, we will fix it soon...";
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            message = "Invalid code entered...";
                        }
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private void showText(){i=0;
        Status.setText("Login To Another Space Of Music");

//        Handler handler = new Handler();
//    final Runnable r = new Runnable() {
//        public void run() {
//            for(int j=0;j<12;j++){
//                switch (j) {
//                    case 0:
//                        Status.setText("Let's Redefine Music");
//                    case 1:
//                        Status.setText("Login To Another Space Of Music");
//                    case 2:
//                        Status.setText("Originals Have New Meaning Now");
//                    case 4:
//                        Status.setText("Failed To Connect");
//                    case 6:
//                        Status.setText("Check Your Network");
//                    case 7:
//                        Status.setText("And Try Again");
//                    case 8:
//                        Status.setText("Use Your Phone Number To Login");
//                    case 9:
//                        Status.setText("Unsunged: Let's Make Music for Every Audience");
//                    case 10:
//                        Status.setText("Login Successful");
//                    case 11:
//                        Status.setText("Entering Another Space Of Music");
//                    default:
//                        Status.setText("Login Using EmailId or Phone Number");
//                }
//                handler.postDelayed(this, 1500);
//            }
//
//        }
//    };
//
//    handler.postDelayed(r, 1500);
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

    public void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
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




}
