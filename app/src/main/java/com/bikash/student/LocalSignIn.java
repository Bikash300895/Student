package com.bikash.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class LocalSignIn extends AppCompatActivity {

    private Button welcomeButton;
    private EditText instituteSelector;
    private EditText deparemtntSelector;
    private EditText batchSelector;
    private EditText nametext;

    private String institute;
    private String department;
    private String batch;
    private String userInfo;
    private String name;
    SharedPreferences sharedPreferences;

    Firebase firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_local_sign_in);

        firebase = new Firebase("https://student-eaf3d.firebaseio.com/");

        //Getting the edit views by their ids
        instituteSelector = (EditText) findViewById(R.id.InstituteSelectorEditText);
        deparemtntSelector = (EditText) findViewById(R.id.DeparementSelectorEditText);
        batchSelector = (EditText) findViewById(R.id.BatchSelectorEditText);
        nametext = (EditText) findViewById(R.id.NameSelectorEditText);


        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);



        //Geetting the butto with its id
        welcomeButton = (Button) findViewById(R.id.WelcomeContinueButton);

        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Getting the user input
                 * It is inside try catch
                 * because user may keep any field blank
                 *
                 */

                try{

                    name = nametext.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    institute = instituteSelector.getText().toString().toLowerCase();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    department = deparemtntSelector.getText().toString().toLowerCase();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    batch = batchSelector.getText().toString().toLowerCase();

                }catch (Exception e){
                    e.printStackTrace();
                }

                userInfo = institute+department+batch;

                Log.i("NAME", userInfo);
                Log.i("NAME", name);


                HomeActivity.userGroup = userInfo;
                HomeActivity.mUsername = name;
                sharedPreferences.edit().putString("userGroup", userInfo).apply();
                sharedPreferences.edit().putString("userName", name).apply();
                Toast.makeText(getBaseContext(), "Your are logged into group " + userInfo, Toast.LENGTH_LONG).show();

                User u = new User(HomeActivity.mUsername, HomeActivity.userEmail, HomeActivity.userPassword);
                Log.i("NAME", HomeActivity.mUsername);
                Log.i("NAME", HomeActivity.userEmail);
                Log.i("NAME", HomeActivity.userPassword);

                firebase.child("users").child(userInfo).child(HomeActivity.mUsername).setValue(u);
                String userlog = HomeActivity.mUsername + " has joined group" + userInfo;
                firebase.child("log").child(userInfo).push().setValue(userlog);

                String formatedMail = EmailProcess.ProcessEmail(HomeActivity.userEmail);
                firebase.child("email").child(formatedMail).setValue(userInfo);


                finish();

            }
        });

    }


}
