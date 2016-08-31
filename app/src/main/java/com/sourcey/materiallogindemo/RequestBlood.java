package com.sourcey.materiallogindemo;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourcey.materiallogindemo.model.BloodModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Rahul Satish on 19-08-2016.
 */


public class RequestBlood extends AppCompatActivity {
    //  @Bind(R.id.button_loc) TextView _buttonloc;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    public ListView lV;

    BloodListAdapter adapter;
    List<BloodModel> posts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list);
        ButterKnife.bind(this);

//        _buttonloc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                double longitude = location.getLongitude();
//                double latitude = location.getLatitude();
//
//                Toast.makeText(getBaseContext(),Double.toString(longitude),Toast.LENGTH_LONG).show();
//            }
//        });
        lV = (ListView) findViewById(R.id.listview);
        getData();
    }

    public void getData() {
        String res;
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL("http://lifesaver.net23.net/getReq.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String line = "", res = "";
                    Integer i = 1;
                    while ((line = bufferedReader.readLine()) != null) {

                        res = res + line;

                    }
                    res = res.substring(0, res.indexOf("<"));


                    Log.d("response", res);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return res;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String te) {
                String myJSON = te;
                //t1.setText(myJSON);
                Log.d("tete", te);
                Toast.makeText(getApplicationContext(), te, Toast.LENGTH_LONG).show();

                Gson gson = new Gson();
            /*
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(te);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("result");
                    Log.d("te", jsonArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Type listType = new TypeToken<List<BloodModel>>() {
                }.getType();
                // Type collectionType = new TypeToken<List<NavItem>>() {

                //  List<NavItem> navigation = gson.fromJson(jsonString, collectionType);

              //  posts=(List<BloodModel>)gson.fromJson(jsonArray.toString(),listType);
                // t1.setText(te);

            }*/
                Type type = new TypeToken<List<BloodModel>>() {
                }.getType();
                List<BloodModel> yourList = gson.fromJson(te, type);

                adapter = new BloodListAdapter(getApplicationContext(), yourList);
                lV.setAdapter(adapter);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
            }
        }

