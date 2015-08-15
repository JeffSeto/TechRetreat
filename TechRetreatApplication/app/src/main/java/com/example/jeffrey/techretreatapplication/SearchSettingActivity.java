package com.example.jeffrey.techretreatapplication;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class SearchSettingActivity extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    Intent nextActivity;

    private int healthiness;

    CheckBox chineseCheckBox;
    CheckBox indianCheckBox;
    CheckBox japaneseCheckBox;
    CheckBox koreanCheckBox;
    CheckBox greekCheckBox;
    CheckBox italianCheckBox;
    CheckBox mediterraneanCheckBox;
    CheckBox frenchCheckBox;
    CheckBox pubCheckBox;
    CheckBox seafoodCheckBox;
    CheckBox barbequeCheckBox;

    EditText distanceEditText;

    SeekBar healthinessSeekBar;

    TextView healthinessLabel;

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();

    private LocationRequest mLocationRequest;

    double lat,lng;
   // LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_setting);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        nextActivity = new Intent(this, RestuarantDisplayActivity.class);

        chineseCheckBox = (CheckBox) findViewById(R.id.chineseCheckBox);
        indianCheckBox = (CheckBox) findViewById(R.id.indianCheckBox);
        japaneseCheckBox = (CheckBox) findViewById(R.id.japaneseCheckBox);
        koreanCheckBox = (CheckBox) findViewById(R.id.koreanCheckBox);
        greekCheckBox = (CheckBox) findViewById(R.id.greekCheckBox);
        italianCheckBox = (CheckBox) findViewById(R.id.italianCheckBox);
        mediterraneanCheckBox = (CheckBox) findViewById(R.id.mediterraneanCheckBox);
        frenchCheckBox = (CheckBox) findViewById(R.id.frenchCheckBox);
        pubCheckBox = (CheckBox) findViewById(R.id.pubCheckBox);
        seafoodCheckBox = (CheckBox) findViewById(R.id.seafoodCheckBox);
        barbequeCheckBox = (CheckBox) findViewById(R.id.barbequeCheckBox);

        distanceEditText = (EditText) findViewById(R.id.distanceEditText);

        healthinessSeekBar = (SeekBar) findViewById(R.id.healthinessSeekBar);

        healthinessLabel = (TextView) findViewById(R.id.healthinessLabel);

        healthinessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                healthiness = progress;
                healthinessLabel.setText("Healthiness Rating: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    public void searchOnClick(View v){
        SearchSettings instance = SearchSettings.generateInstance();

        instance.setChinese(chineseCheckBox.isChecked());
        instance.setIndian(indianCheckBox.isChecked());
        instance.setJapanese(japaneseCheckBox.isChecked());
        instance.setKorean(koreanCheckBox.isChecked());
        instance.setGreek(greekCheckBox.isChecked());
        instance.setMediterranean(mediterraneanCheckBox.isChecked());
        instance.setItalian(italianCheckBox.isChecked());
        instance.setFrench(frenchCheckBox.isChecked());
        instance.setPub(chineseCheckBox.isChecked());
        instance.setSeafood(chineseCheckBox.isChecked());
        instance.setBarbeque(barbequeCheckBox.isChecked());
        int distance;
        try{
            distance = Integer.getInteger(distanceEditText.getText().toString());
        } catch (NullPointerException e){
            distance = 0;
        }
        instance.setDistance(distance);

        instance.setLat(lat);
        instance.setLng(lng);
        startActivity(nextActivity);

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(location);
        };
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void handleNewLocation(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        Log.d(TAG, location.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);

    }
}
