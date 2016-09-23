package com.fruitbay.main;

import java.util.Comparator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fruitbay.adapter.CustomListAdapter;
import com.fruitbay.model.FruitsClass;
import com.fruitbay.model.CustomComparators;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "http://172.24.1.14:3000/getData";
    private static final String urlStats = "http://172.24.1.14:3000/getStats";
    private ProgressDialog pDialog;
    private List<FruitsClass> fruitsClassList = new ArrayList<FruitsClass>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, fruitsClassList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest fruitReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                FruitsClass fruitsClass = new FruitsClass();
                                fruitsClass.setName(obj.getString("name"));
                                fruitsClass.setStock(obj.getString("stock"));
                                fruitsClass.setPrice(obj.getString("price"));

                                // adding a fruit to fruitsClass array
                                fruitsClassList.add(fruitsClass);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

//                        UpdateStats(fruitsClassList);
                        Log.d(TAG,"HUMPTY DUMO");
                        UpdateStatsServer();
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(fruitReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public void UpdateStats(List<FruitsClass> fruitsClassList){
        //for Stock Based Comparison
        List<FruitsClass> forStockList=new ArrayList<FruitsClass>(fruitsClassList);
        Collections.sort(forStockList,new CustomComparators.stockComparatorAscending());

        TextView mostavailablefruittxtView = (TextView) findViewById(R.id.mostavailablefruit);
        mostavailablefruittxtView.setText(String.valueOf(forStockList.get(0).getName()));
        TextView leastavailablefruittxtView = (TextView) findViewById(R.id.leastavailablefruit);
        leastavailablefruittxtView.setText(String.valueOf(forStockList.get(forStockList.size()-1).getName()));

        // For Price based Comparison
        List<FruitsClass> forPriceList=new ArrayList<FruitsClass>(fruitsClassList);
        Collections.sort(forPriceList,new CustomComparators.PriceComparatorAscending());

        TextView costliestfruittxtview = (TextView) findViewById(R.id.costliestfruit);
        costliestfruittxtview.setText(String.valueOf(forPriceList.get(0).getName()));
        TextView cheapestfruittxtview = (TextView) findViewById(R.id.cheapestfruit);
        cheapestfruittxtview.setText(String.valueOf(forPriceList.get(forPriceList.size()-1).getName()));
    }

    public void UpdateStatsServer(){
        Log.d(TAG,"Humpty DUMO 2");
        JsonArrayRequest statReq = new JsonArrayRequest(urlStats,
            new Response.Listener<JSONArray>() {
                public void onResponse(JSONArray response) {
                    Log.d(TAG,"Humpty Dumo 3");
                    Log.d(TAG, response.toString());
                    hidePDialog();

                    // Parsing json

                        try {
                            JSONObject obj = response.getJSONObject(0);
                            TextView mostavailablefruittxtView = (TextView) findViewById(R.id.mostavailablefruit);
                            mostavailablefruittxtView.setText(String.valueOf(obj.getString("mostavailablefruit")));
                            TextView leastavailablefruittxtView = (TextView) findViewById(R.id.leastavailablefruit);
                            leastavailablefruittxtView.setText(String.valueOf(obj.getString("leastavailablefruit")));

                            TextView costliestfruittxtview = (TextView) findViewById(R.id.costliestfruit);
                            costliestfruittxtview.setText(String.valueOf(obj.getString("costliestfruit")));
                            TextView cheapestfruittxtview = (TextView) findViewById(R.id.cheapestfruit);
                            cheapestfruittxtview.setText(String.valueOf(obj.getString("cheapestfruit")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG,"Humpty Dumo 4");
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    VolleyLog.d(TAG,"Jindasascdscxd");
                    hidePDialog();
                }
            }
        );
        AppController.getInstance().addToRequestQueue(statReq);
    }

}

