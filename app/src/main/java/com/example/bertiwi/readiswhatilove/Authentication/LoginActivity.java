package com.example.bertiwi.readiswhatilove.Authentication;

import android.Manifest;
import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.bertiwi.readiswhatilove.BuildConfig;
import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.activities.SplashScreenActivity;
import com.example.bertiwi.readiswhatilove.utilities.SharedPrefManager;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private GoogleSignInClient mGoogleSignInClient;

    private static final int RC_SIGN_IN = 3243;
    private ProgressBar mProgressBar;
    private GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        switch (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS)) {
            case PackageManager.PERMISSION_GRANTED:
                break;
        }

        mProgressBar = findViewById(R.id.login_progresbar);

        Scope SCOPE_BOOKS = new Scope("https://www.googleapis.com/auth/books");

        String clientId = getString(R.string.clientID);

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(clientId)
                .requestScopes(SCOPE_BOOKS)
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    public void updateUI(GoogleSignInAccount account){
        if (account != null){
            Intent intent = new Intent(this, SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button){
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            new GetContactsTask(this, new GetContactsTask.OnTaskCompleted() {
                @Override
                public void onTaskCompleted() {
                    updateUI(account);
                }
            },account.getAccount()).execute();


            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("YAYAYAYA", "signInResult:failed code=" + e.getStatusCode());
            Log.d("QUEMAL", e.getMessage());
            Snackbar.make(findViewById(R.id.login_layout_content), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

            updateUI(null);
        }
    }

    private static class GetContactsTask extends AsyncTask<Void, Void, Void> {

        public interface OnTaskCompleted{
            void onTaskCompleted();
        }

        OnTaskCompleted listener = null;

        WeakReference<Context> mContextReference;
        Account mAccount;

        public GetContactsTask(Context context, OnTaskCompleted listener, Account account) {
            mContextReference = new WeakReference<Context>(context);
            this.listener = listener;
            this.mAccount = account;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //try {
                GoogleAccountCredential credential =
                        GoogleAccountCredential.usingOAuth2(
                                mContextReference.get(),
                                Collections.singleton(
                                        "https://www.googleapis.com/auth/books")
                        );
                credential.setSelectedAccount(mAccount);
            try {
                SharedPrefManager.getInstance(mContextReference.get()).setToken(credential.getToken());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GoogleAuthException e) {
                e.printStackTrace();
            }

                /*People service = new People.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setApplicationName("REST API sample")
                        .build();
                ListConnectionsResponse connectionsResponse = service
                        .people()
                        .connections()
                        .list("people/me")
                        .execute();
                result = connectionsResponse.getConnections();
            } catch (UserRecoverableAuthIOException userRecoverableException) {
                // Explain to the user again why you need these OAuth permissions
                // And prompt the resolution to the user again:
                startActivityForResult(userRecoverableException.getIntent(),RC_REAUTHORIZE);
            } catch (IOException e) {
                // Other non-recoverable exceptions.
            }*/

            return null;
        }

        @Override
        protected void onCancelled() {

        }

        @Override
        protected void onPostExecute(Void connections) {
            if (mContextReference != null) {
                mContextReference.clear();
                mContextReference = null;
            }
            listener.onTaskCompleted();
        }
    }
}
