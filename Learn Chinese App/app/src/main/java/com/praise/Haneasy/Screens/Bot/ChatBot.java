package com.praise.Haneasy.Screens.Bot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Bot.Modal.ChatAdapter;
import com.praise.Haneasy.Screens.Bot.Modal.ChatModal;
import com.praise.Haneasy.Screens.Bot.Modal.MsgModal;
import com.praise.Haneasy.Screens.Bot.Modal.RetrofitApi;
import com.praise.Haneasy.Screens.Learning.LearningPage;
import com.praise.Haneasy.Screens.MyClickInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This activity collects user message
 * sends a request to the bot api
 * and gets response...
 * Make sure you register and replace with your API Key
 */
public class ChatBot extends AppCompatActivity implements MyClickInterface {
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    RecyclerView recyclerView;
    EditText editText;
    ImageView imageView,back;
    ChatAdapter chatAdapter;
    TextToSpeech textToSpeech;
    private ArrayList<ChatModal> chatModals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        initialize();
        chatModals = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, chatModals,this::onItemClick);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(chatAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(editText.getText().toString());
                editText.setText("");
                recyclerView.scrollToPosition(chatModals.size()-1);
            }
        });
    }

    private void getResponse(String msg) {
        chatModals.add(new ChatModal(msg,USER_KEY,getCurrentTime()));
        chatAdapter.notifyDataSetChanged();
        //include your own api key from the wevsite
        String url ="http://api.brainshop.ai/get?bid=160167&key= =[uid]&msg="+msg;
        String BASE_URL = "http://api.brainshop.ai/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<MsgModal> call  = retrofitApi.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful()){
                    MsgModal modal = response.body();
                    chatModals.add(new ChatModal(modal.getCnt(),BOT_KEY,getCurrentTime()));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(chatModals.size()-1);
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatModals.add(new ChatModal("oops!!!, didn't get that",BOT_KEY,getCurrentTime()));
                chatAdapter.notifyDataSetChanged();
            }
        });
    }


    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        Date date = new Date();
        return formatter.format(date).toString();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.msg_recycler);
        editText = findViewById(R.id.comment_input);
        imageView = findViewById(R.id.send_msg);
        back =findViewById(R.id.back);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.CHINESE);
                }
            }
        });
    }

    @Override
    public void onItemClick(int positionOfMessage) {
        /**
         * String to be read..
         */
        String output = chatModals.get(positionOfMessage).getMessage();
        textToSpeech.speak(output, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LearningPage.class));
      overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}