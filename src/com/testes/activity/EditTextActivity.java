package com.testes.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.testes.android.R;

public class EditTextActivity extends ActionBarActivity{

	String buttonText= "";
	private EditText numberEdit, doubleEdit;
	TextView angleText;
	private boolean atualizando = false;
	public static final String TAG = "EditTextActivity";
	View line;

	float degreesSoItIsParallelToTheGround=0f;
	String phoneRotationDegrees="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_edittext);

		String [] degreesValues = new String [20];

		for(int i= 0;i<20;i++){
			degreesValues[i] = String.valueOf(i)+ (char) 0x00B0;
		}

		doubleEdit =  (EditText) findViewById(R.id.doubleEditText);
		final EditText editText = (EditText) findViewById(R.id.editTextAct);

		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.toString().contains(",")){
					Log.i(TAG, "Contains");
				}
				if(s.length()>3){
					Log.i(TAG,"Content: " + s.toString().replace("'", "''"));
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(s.length()>0)
					Log.i(TAG, "some text");
			}
		});

		doubleEdit.addTextChangedListener(filterTextWatcherTelefone);
		
	}

	private TextWatcher filterTextWatcherTelefone = new TextWatcher() {
        public void afterTextChanged(Editable s) {


        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                int count) {
            try {
                if (atualizando) {
                    atualizando = false;
                    return;
                }

                String resultado = limparFormatacaoNumero(s.toString());

//                if (isNumero(resultado)) {

                    if (resultado.length() <= 14) {
                        resultado = adicionarFormatacaoTelefone(resultado);

                    } else {
                        resultado = resultado.substring(0, 14);
                        resultado = adicionarFormatacaoTelefone(resultado);
                    }
                    atualizando = true;
                    doubleEdit.setText(resultado);
                    doubleEdit.setSelection(doubleEdit.getText().length());

//                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    };

	@Override
	protected void onPause() {

		super.onPause();

	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	public void whenbuttonisclicked(View view){
		String edittext1str = doubleEdit.getText().toString();
		double editext1dob = 0;
		if(!TextUtils.isEmpty(edittext1str))
			editext1dob = Double.parseDouble(edittext1str);

		String edittext2str = "2";
		double editext2dob = Double.parseDouble(edittext2str);
		double add = (editext1dob + editext2dob);
		String yourDoubleString = String.valueOf(add);
		Log.i(TAG, "result:"+yourDoubleString);   
	}

	public String adicionarFormatacaoTelefone(String string){
		return "444+"+string;
	}

	public String limparFormatacaoNumero(String s){
		return s.trim();
	}
	
}
