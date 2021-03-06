package com.codefororlando.orlandowalkingtours.managers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.codefororlando.orlandowalkingtours.deserializer.HistoricLandmarkDeserializer;
import com.codefororlando.orlandowalkingtours.models.AppConfig;
import com.codefororlando.orlandowalkingtours.models.HistoricLandmark;
import com.codefororlando.orlandowalkingtours.utilities.DevelopmentUtilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by MarkoPhillipMarkovic on 5/10/2016.
 */
//Main task for this class is to pull the historic landmark data from the API
public class HistoricLandmarkManager extends BaseRouteManager {
    public static final String PULL_SUCCESS = "com.codefororlando.orlandowalkingtours.landmarks";
    protected Gson mGson;
    private ArrayList<HistoricLandmark> mHistoricLandmarks;
    public HistoricLandmarkManager(Context context){
        mHistoricLandmarks = new ArrayList<>();
        mContext = context;
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(HistoricLandmark.class, new HistoricLandmarkDeserializer());
        mGson = gsonBuilder.create();
        switchState(IDLE);
    }

    public void pullHistoricLandmarks() {
        mHistoricLandmarks.clear();
        if(mState != IDLE)
        {
            return;
        }
        mState = PULLING;

        Ion.with(mContext)
                .load(getUrl())
                .noCache()
                .addHeader(AppConfig.getAppTokenHeader(), AppConfig.getAppToken())
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (e != null) {
                            DevelopmentUtilities.logE("Exception + " + e.getMessage());
                            switchState(FINISHED);
                            return;
                        }

                        if (result == null) {
                            switchState(FINISHED);
                            return;
                        }
                        for (JsonElement element : result) {
                            HistoricLandmark land = mGson.fromJson(element, HistoricLandmark.class);
                            DevelopmentUtilities.logV("Pulled landmark -> "
                                    + land.getName() + ", "
                                    + land.getAddress() + ", "
                                    + land.getLocation_location() +  ", "
                                    + land.getLocation_city()  + ", "
                                    + land.getLocation_state()  + ", "
                                    + land.getType() + ", "
                                    + land.getLocal().toString() + ", "
                                    + land.getLocation().getLatitude()  + ", "
                                    + land.getLocation().getLongitude() + ", "
                                    + land.getLocation().getType()
                            );
                            mHistoricLandmarks.add(land);
                        }

                        switchState(FINISHED);
                        return;
                    }
                });
    }

    @Override
    public void switchState(int newState) {
        mState = newState;
        switch (mState) {
            case IDLE:
                break;
            case PULLING:
                break;
            case FINISHED:
                LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(mContext);
                Intent pullSuccessBroadcastIntent = new Intent(PULL_SUCCESS);
                broadcastManager.sendBroadcast(pullSuccessBroadcastIntent);
                switchState(IDLE);
                break;
        }
    }

    @Override
    public String getUrl() {
        return "https://brigades.opendatanetwork.com/resource/aq56-mwpv.json";
    }

    public ArrayList<HistoricLandmark> getHistoricLandmarks(){
        return mHistoricLandmarks;
    }
}
