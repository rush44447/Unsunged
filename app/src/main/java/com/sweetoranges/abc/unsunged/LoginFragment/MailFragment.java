package com.sweetoranges.abc.unsunged.LoginFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sweetoranges.abc.unsunged.Activities.LoginActivity;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;

import static android.content.Context.MODE_PRIVATE;

public class MailFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    private SignInButton Loginpage;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    private TextView Status;

    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mail,container,false);
        Loginpage=(SignInButton)rootView.findViewById(R.id.loginButton);
        Status=(TextView)rootView.findViewById(R.id.statustext);

        context=getActivity();
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(getActivity()).enableAutoManage(getActivity(),this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        Loginpage.setOnClickListener(v -> {
            ((LoginActivity)context).progress.setVisibility(View.VISIBLE);
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SIGN_IN);
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), String.valueOf(connectionResult), Toast.LENGTH_SHORT).show();}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            ((LoginActivity)context).handleSignInResult(result);
        }
    }

//    private void handleSignInResult(GoogleSignInResult result) {
//        if (result.isSuccess()) {
//            GoogleSignInAccount acct = result.getSignInAccount();
//            Status.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//         //   imgProfilePic.setImageResource(R.drawable.imgview);
//            progress.setVisibility(View.GONE);
//            if( acct.getDisplayName()!=null){
//                SharedPreferences.Editor editor = getActivity().getSharedPreferences("login", MODE_PRIVATE).edit();
//                editor.putBoolean("logininfo", true);
//                editor.putString("username",acct.getDisplayName());
//                editor.putString("email",acct.getEmail());
//                editor.apply();
//            }
//            SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
//            if (!prefs.getBoolean("logininfo", false)) {
//                // startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }         //    if profile image is avail abel                  imp
//           // if(acct.getPhotoUrl() != null) new LoginActivity.LoadProfileImage(imgProfilePic).execute(acct.getPhotoUrl().toString());
//
//            ((LoginActivity)context).updateUI(true);
//        } else {
//            // Signed out, show unauthenticated UI.
//            ((LoginActivity)context).updateUI(false);
//        }
//    }

}

