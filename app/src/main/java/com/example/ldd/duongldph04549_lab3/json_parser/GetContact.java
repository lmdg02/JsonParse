package com.example.ldd.duongldph04549_lab3.json_parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class GetContact extends AsyncTask<Void, Void, Void>{
	private String TAG = Bai1.class.getSimpleName();
	
	public static String url = "https://api.androidhive.info/contacts/";
	
	ArrayList<Contact> contactList;
	private ProgressDialog progressDialog;
	private ListView lv;
	Context context;
	ContactAdapter adapter;
	
	public GetContact(Context context , ListView lv){
		this.context = context;
		this.lv = lv;
		contactList = new ArrayList<Contact>();
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("please wait...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	@Override
	protected Void doInBackground(Void... params) {
		HttpHandler handler = new HttpHandler();
		
		String jsonStr = handler.makeServiceCall(url);
		Log.e(TAG,"Response from url "+jsonStr);
		if (jsonStr != null) {
			try {
				JSONObject jsonObject = new JSONObject(jsonStr);
				JSONArray jsonArray = jsonObject.getJSONArray("contacts");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject c = jsonArray.getJSONObject(i);
					String id = c.getString("id");
					String name = c.getString("name");
					String email = c.getString("email");
					String address = c.getString("address");
					String gender = c.getString("gender");
					
					JSONObject phone = c.getJSONObject("phone");
					String mobile = phone.getString("mobile");
					String home = phone.getString("home");
					String office = phone.getString("office");
					
					Contact contact = new Contact(id, name, email, address, gender, mobile, home, office);
					contactList.add(contact);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG, "Json parsing error "+e.getMessage());
			}
		}else{
			Log.e(TAG, "couldn't get json from sever ");
		}
		
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		adapter = new ContactAdapter(context, contactList);
		lv.setAdapter(adapter);
	}
	
}
