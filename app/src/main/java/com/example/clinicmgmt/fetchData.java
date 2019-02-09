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

public class fetchData extends AsyncTask<Void,Void,Void> {
    public fetchData() {
    }
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url= new URL("http://rajeshkumargupta.000webhostapp.com/viewExpenses.php");
            HttpURLConnection httpURLConnection;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line= "";
            while (line != null){
                line= bufferedReader.readLine();
                data = data +line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i=0; i<JA.length(); i++){
                JSONObject JO= (JSONObject) JA.get(i);
                singleParsed = "Expenses By:" + JO.get("expensesBy")+ "\n"+
                        "Rupees:" + JO.get("rupees")+ "\n"+
                        "Expenses Date:" + JO.get("expensesDate")+ "\n"+
                        "Description:" + JO.get("description")+ "\n";
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
        ViewExpensesFragment.data.setText(this.dataParsed);
    }
}
