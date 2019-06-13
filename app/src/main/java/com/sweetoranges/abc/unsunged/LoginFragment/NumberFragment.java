package com.sweetoranges.abc.unsunged.LoginFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Activities.LoginActivity;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;

public class NumberFragment extends Fragment {
    private AppCompatEditText phoneNumber,verificationCode;
    private AppCompatButton ok;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_number,container,false);
        phoneNumber=(AppCompatEditText)rootView.findViewById(R.id.phoneNumber);
        ok=(AppCompatButton)rootView.findViewById(R.id.ok);
        verificationCode=(AppCompatEditText)rootView.findViewById(R.id.verificationCode);

        ok.setOnClickListener(v -> {
           if(phoneNumber.getVisibility()==View.VISIBLE){
            if (phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().length() < 10) {
                phoneNumber.setError("Enter valid Number");
                phoneNumber.requestFocus();
            }else {
                ((LoginActivity)getActivity()).sendVerificationCode(phoneNumber.getText().toString());
                phoneNumber.setVisibility(View.GONE);
                verificationCode.setVisibility(View.VISIBLE);
            }
            phoneNumber.setVisibility(View.GONE);
            verificationCode.setVisibility(View.VISIBLE);}
           else{
             if (verificationCode.getText().toString().isEmpty() || verificationCode.getText().toString().length() < 6)  verificationCode.setError("Code Invalid");
             else ((LoginActivity)getActivity()).verifyVerificationCode(verificationCode.getText().toString());
           }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

