package com.testes.activity;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.testes.android.R;
import com.testes.utils.VolleyCallback;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class VolleyActivity extends FragmentActivity{

	protected static final String TAG = "VolleyActivity";

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);

		setContentView(R.layout.margin_layout);
		
		sendParams("id", "Save", new VolleyCallback() {
			
			@Override
			public void onSuccess(JSONObject response) {
				Log.i(TAG,  "OK"+ response.toString());
				
			}
			
			@Override
			public void onInit() {
				
				Log.i(TAG,  "INIT");
			}
			
			@Override
			public void onFail(String message) {
				Log.e(TAG,  "FAIL "+ message);
				
			}
		});
		
	}

	public void sendParams(String id,String stringSave, final VolleyCallback vc) {

		final String URL = "http://maps.b1.finki.ukim.mk/MapController/save";
		JSONObject params = new JSONObject();
		try {
			params.put("cId", id);

			params.put("positions", stringSave);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		RequestQueue rq = Volley.newRequestQueue(this);
		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST,URL,params,
				new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				vc.onSuccess(response);

				Log.i("Response ",response.toString());
				String string=response.optString("positions");
				Log.i("Positions",string);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				volleyError.printStackTrace();
				Log.e(TAG, volleyError.getMessage());
			}
		});

		rq.add(jr);
	}

}
