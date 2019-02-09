package com.example.clinicmgmt;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchTaskData extends AsyncTask<Void,Void,Void> {
    public fetchTaskData() {
    }
    String datas = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://rajeshkumargupta.000webhostapp.com/viewTask.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line= "";
            while (line != null){
                line = bufferedReader.readLine();
                datas = datas + line;
            }
            JSONArray JA = new JSONArray(datas);
            for(int i=0; i<JA.length(); i++){
                JSONObject JO= (JSONObject) JA.get(i);
                singleParsed = "Task Expire Date:" + JO.get("lastDate")+ "\n"+
                        "Task Description:" + JO.get("description")+ "\n"+
                        "Task Priority:" + JO.get("priority")+ "\n"+
                        "Task add Date:" + JO.get("createdDate")+ "\n";
                dataParsed = dataParsed +singleParsed +"\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ViewTaskFragment.datas.setText(this.dataParsed);
    }
}
