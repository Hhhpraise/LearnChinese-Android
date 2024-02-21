package com.praise.Haneasy.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.praise.Haneasy.Screens.Login.VALID_EMAIL_ADDRESS_REGEX;
import static com.praise.Haneasy.Splash.APP_URL;

/**
 * Chinese Made Easy sign up page
 * requires username , email and password..
 */
public class Sign_Up extends AppCompatActivity {
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    TextView switcher;
    ProgressBar progressBar;
    EditText name, mail, password;
    CardView signupBtn;
    String msg;

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String pass) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(pass);
        return matcher.find();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String email = mail.getText().toString().trim();
                String pass = password.getText().toString();
                if (!n.isEmpty()) {
                    if (validate(email)) {
                        if (validatePassword(pass)) {
                            if(isNetworkConnected()){

                                    handleSignUp(n, email, pass);

                            }
                            else {
                                msg = "Internet connection required";
                            }

                        } else {
                            msg = "Password must include a capital letter, a number and at least 8 characters";
                        }
                    } else {
                        msg = "Enter valid email";
                    }
                } else {
                    msg = "Enter your name";
                }

                toastHandler(msg);
            }
        });
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });

    }



    private void toastHandler(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void handleSignUp(String name, String mail, String pass) {
        progressBar.setVisibility(View.VISIBLE);
        String[] field = new String[3];
        field[0] = "name";
        field[1] = "email";
        field[2] = "password";
        //Creating array for data
        String[] data = new String[3];
        data[0] = name;
        data[1] = mail;
        data[2] = pass;
        PutData putData = new PutData(APP_URL + "signup.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                try {
                    JSONObject jsonObject = new JSONObject(putData.getResult());
                    String data_val = jsonObject.getString("data");
                    if (data_val.equals("Successful")) {
                        msg = "Successful";
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private void initialize() {
        switcher = findViewById(R.id.switch_to_log_in);
        progressBar = findViewById(R.id.progress);
        name = findViewById(R.id.signup_name);
        mail = findViewById(R.id.signup_mail);
        password = findViewById(R.id.signup_password);
        signupBtn = findViewById(R.id.signup);
    }

    @Override
    public void onBackPressed() {

    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}