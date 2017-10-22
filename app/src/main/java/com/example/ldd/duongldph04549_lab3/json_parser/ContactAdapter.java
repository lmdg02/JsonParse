package com.example.ldd.duongldph04549_lab3.json_parser;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ldd.duongldph04549_lab3.R;

public class ContactAdapter extends BaseAdapter{
	Context context;
	ArrayList<Contact> contactlist;
	
	public ContactAdapter(Context context, ArrayList<Contact> contactlist) {
		this.context = context;
		this.contactlist = contactlist;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contactlist.size();
	}

	@Override
	public Object getItem(int i) {
		// TODO Auto-generated method stub
		return contactlist.get(i);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static class ViewHolder{
		TextView txtName,txtEmail,txtMobile;
	}
	@Override
	public View getView(int i, View view, ViewGroup viewgroup) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder viewHolder ;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);
			viewHolder.txtName = (TextView)view.findViewById(R.id.txtName);
			viewHolder.txtEmail = (TextView)view.findViewById(R.id.txtEmail);
			viewHolder.txtMobile =  (TextView)view.findViewById(R.id.txtMobile);
			view.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)view.getTag();
		}
		Contact contact = contactlist.get(i);
		viewHolder.txtName.setText(contact.getName());
		viewHolder.txtEmail.setText(contact.getEmail());
		viewHolder.txtMobile.setText(contact.getMobile());
		return view;
	}
	
}
