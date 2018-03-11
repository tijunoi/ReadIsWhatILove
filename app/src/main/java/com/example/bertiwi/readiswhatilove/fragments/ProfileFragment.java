package com.example.bertiwi.readiswhatilove.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bertiwi.readiswhatilove.Authentication.LoginActivity;
import com.example.bertiwi.readiswhatilove.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import nilordonez.com.profileitem.ProfileSignout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileSignout.OnSignoutButtonClickedListener {


    private ProfileSignout mProfileSignout;
    private GoogleSignInAccount mLastSignedInAccount;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        mProfileSignout = v.findViewById(R.id.profile_item);
        mProfileSignout.setOnSignoutButtonClickedListener(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLastSignedInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        //Seteo nombre y email porque queda mejor
        mProfileSignout.setUserEmail(mLastSignedInAccount.getEmail());
    }

    @Override
    public void onSignoutButtonClicked(ProfileSignout source) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(getContext(), gso);
        client.signOut();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }
}
