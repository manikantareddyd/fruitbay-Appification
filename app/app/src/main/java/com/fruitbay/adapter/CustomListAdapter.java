package com.fruitbay.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.fruitbay.model.FruitsClass;
import com.fruitbay.volleycustomlistview.AppController;
import com.fruitbay.volleycustomlistview.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<FruitsClass> fruitsItems;

    public CustomListAdapter(Activity activity, List<FruitsClass> fruitsItems) {
        this.activity = activity;
        this.fruitsItems = fruitsItems;
    }

    @Override
    public int getCount() {
        return fruitsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return fruitsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView stock = (TextView) convertView.findViewById(R.id.stock);

        FruitsClass m = fruitsItems.get(position);

        name.setText(m.getName());

        price.setText("Price: " + String.valueOf(m.getPrice()));

        stock.setText("Stock: "+ String.valueOf(m.getStock()));


        return convertView;
    }

}
