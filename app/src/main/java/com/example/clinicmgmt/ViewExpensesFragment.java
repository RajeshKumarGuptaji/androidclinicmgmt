package com.example.clinicmgmt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewExpensesFragment extends Fragment {
ListView listView;

    public ViewExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_expenses, container, false);

        /*Make data array*/
        // *************   First Way  *************
       /* ArrayList<String> data= new ArrayList<>();
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("b");
        data.add("b");
        data.add("b");
        data.add("b");
        data.add("b");
        data.add("b");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
         */
        //  ***************** Second Way   ****************
        //http://rajeshkumargupta.000webhostapp.com/userListApi.php
        String items[] = new String[]{"cat","bat","dat","cat","bat","dat","cat","bat","dat","cat","bat","dat"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,items);

        listView = view.findViewById(R.id.viewExpenses);
        listView.setAdapter(adapter);
        /*String data="";
        try {
            URL url = new  URL("http://rajeshkumargupta.000webhostapp.com/userListApi.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data +line;

            }
           // Log.d("myTag", "This is my message");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }*/

//URL url =new URL("http://rajeshkumargupta.000webhostapp.com/userListApi.php");

        return view;
    }
  //   ******************   Fetch data from Planets array (write into the values >> string.xml file)
   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //String items[] = new String[]{"cat","bat","dat"};
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Planets, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }*/

}
