package com.testes.activity;

import java.util.ArrayList;

import com.testes.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AgendaDetailActivity extends Activity implements TextToSpeech.OnInitListener {

private TextToSpeech tts;
private Button btnspeak;
private TextView textbox;
int random_command;

ArrayList<String> commands = new ArrayList<String>();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.agenda_detail);

    String headline = "";
    String details = "";
    String pubDate = "";

    Intent intent = getIntent();
    if (null != intent) {
        headline = intent.getStringExtra("KEY_TITLE");
        details = intent.getStringExtra("KEY_DETAILS");
        pubDate = intent.getStringExtra("KEY_DATE");
    }

    TextView headlineTxt = (TextView) findViewById(R.id.titlecontent);
    headlineTxt.setText(headline);

    TextView pubdateTxt = (TextView) findViewById(R.id.timecontent);
    pubdateTxt.setText(pubDate);

    TextView descriptionTxt = (TextView) findViewById(R.id.descriptioncontent);
    descriptionTxt.setText(details);


    tts = new TextToSpeech(this, this);
    btnspeak = (Button) findViewById(R.id.volume);
    //buttontest = (Button) findViewById(R.id.volume);


    btnspeak.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            //do data collection
            //btnspeak.setText(commands.get(random_command));
            TextView tv = (TextView) findViewById(R.id.descriptioncontent);
            commands.add("Speak");
            tv.setText(commands.get(random_command));

            tts.speak(commands.get(random_command), TextToSpeech.QUEUE_FLUSH, null);


            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "it-IT");

        }
    });

}

@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	
} }
