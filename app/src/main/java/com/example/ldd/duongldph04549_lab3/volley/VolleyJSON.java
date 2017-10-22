package com.example.ldd.duongldph04549_lab3.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ldd.duongldph04549_lab3.R;


public class VolleyJSON extends AppCompatActivity {
    private static String TAG = VolleyJSON.class.getSimpleName();
    private String urlJsonObj = "http://192.168.1.101/Android/contacts/person_object.json";
    private String urlJsonArr = "http://192.168.1.101/Android/contacts/person_array.json";

    private Button btnMakeObjectRequest, btnMakeArrayRequest;
    private TextView txtRespone;
    private ProgressDialog progressDialog;
    private String jsonRespone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrRequest);
        txtRespone = (TextView) findViewById(R.id.txtRespone);

        progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("please wait..");
        progressDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                makeJsonObjectRequest();
            }
        });
        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                makeJsonArrayRequest();
            }
        });
    }

    private void makeJsonObjectRequest() {
        showDialog();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj,null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, "Error: " + jsonObject.toString());
                try {
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    JSONObject phone = jsonObject.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");
                    jsonRespone = "";
                    jsonRespone += "Name : " + name + "\n\n";
                    jsonRespone += "Email : " + email + "\n\n";
                    jsonRespone += "Home : " + home + "\n\n";
                    jsonRespone += "Mobile : " + mobile + "\n\n";
                    txtRespone.setText(jsonRespone);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                hideDialog();
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error : " + volleyError.getMessage());
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    private void makeJsonArrayRequest() {
        showDialog();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(urlJsonArr, new Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.d(TAG, jsonArray.toString());
                try {
                    jsonRespone = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject person = jsonArray.getJSONObject(i);
                        String name = person.getString("name");
                        String email = person.getString("email");
                        JSONObject phone = person.getJSONObject("phone");
                        String home = phone.getString("home");
                        String mobile = phone.getString("mobile");

                        jsonRespone += "Name : " + name + "\n\n";
                        jsonRespone += "Email : " + email + "\n\n";
                        jsonRespone += "Home : " + home + "\n\n";
                        jsonRespone += "Mobile : " + mobile + "\n\n";
                    }
                    txtRespone.setText(jsonRespone);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                hideDialog();
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error " + volleyError.getMessage());
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
