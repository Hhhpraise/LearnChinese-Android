package com.praise.Haneasy.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.praise.Haneasy.Splash.APP_URL;

/**
 * The app login page
 * requires user email and password...
 *
 */
public class Login extends AppCompatActivity {
    TextView switcher;
    CardView loginBtn;
    ProgressBar progressBar;
    EditText mail, password;
    private  static  String IS_LOGGED_IN = "isLogIn";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Sign_Up.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                if (validate(email)){
                    if(!pass.isEmpty()){
                        progressBar.setVisibility(View.VISIBLE);
                       if(isNetworkConnected()){

                               login(email,pass);


                       }
                        else {
                               Toast.makeText(getApplicationContext(),"Internet connection required",Toast.LENGTH_LONG).show();

                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter a valid email",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void login(String email, String pass) {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[2];
            field[0] = "email";
            field[1] = "password";
            //Creating array for data
            String[] data = new String[2];
            data[0] = email;
            data[1] = pass;

            PutData putData = new PutData(APP_URL+"login.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    try {
                        JSONObject jsonObject = new JSONObject(putData.getResult());
                        String data_val = jsonObject.getString("data");
                            Log.d("Login", data_val);
                            if(data_val.equals("Email or Password wrong")){
                                Toast.makeText(getApplicationContext(),"Wrong Email or Password ",Toast.LENGTH_SHORT).show();
                            }else {
                                JSONObject object = new JSONObject(data_val);
                                String test =object.getString("data");
                                String msg = object.getString("msg");

                                if(msg.equals("Successful")){
                                    Log.d("Login", test);
                                    JSONObject obj = new JSONObject(test);
                                    String name = obj.getString("name");
                                    String mail = obj.getString("email");
                                    Log.d("Login", name+" "+mail);
                                    startActivity(new Intent(getApplicationContext(), LearningPage.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                                    SharedPreferences.Editor editor = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE).edit();
                                    editor.putBoolean("isLog", true);
                                    editor.putString("user_name",name);
                                    editor.putString("user_mail",mail);
                                    editor.apply();

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"failed to log in",Toast.LENGTH_SHORT).show();
                                }

                            }






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    progressBar.setVisibility(View.GONE);
                    //End ProgressBar (Set visibility to GONE)
                }
            }
        }



    private void initialize() {
        switcher = findViewById(R.id.switch_to_sign_up);
        loginBtn = findViewById(R.id.login);
        mail = findViewById(R.id.login_email);
        password = findViewById(R.id.login_pass);
        progressBar = findViewById(R.id.progress);
    }
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void onBackPressed() {

    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("haneasy.site");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}