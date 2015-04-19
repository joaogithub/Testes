package com.testes.utils;

import org.json.JSONObject;

/**
 * Created by oscarrodriguez on 6/11/14.
 */
public interface VolleyCallback {

    public void onInit();
    public void onFail(String message);
    public void onSuccess(JSONObject response);
}