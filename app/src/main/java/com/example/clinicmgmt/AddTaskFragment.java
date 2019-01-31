package com.example.clinicmgmt;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {
Button btn;
EditText description,lastDate,priority;

    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_task, container, false);
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        btn = view.findViewById(R.id.Save_btn);
        description = view.findViewById(R.id.description);
        lastDate = view.findViewById(R.id.last_date);
        priority = view.findViewById(R.id.priority);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Description = description.getText().toString();
                String LastDate = lastDate.getText().toString();
                String Priority = priority.getText().toString();
                File file=null;
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                    String reg_url = "http://rajeshkumargupta.000webhostapp.com/addTask.php";
                    try{
                        URL url= new URL(reg_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                        String data = URLEncoder.encode("description","UTF-8") +"="+URLEncoder.encode(Description,"UTF-8")+"&"+
                                URLEncoder.encode("lastDate","UTF-8") +"="+URLEncoder.encode(LastDate,"UTF-8")+"&"+
                                URLEncoder.encode("priority","UTF-8") +"="+URLEncoder.encode(Priority,"UTF-8");
                        bufferedWriter.write(data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        os.close();
                        InputStream inputStream =  httpURLConnection.getInputStream();
                        inputStream.close();
                        Toast.makeText(getActivity(),"save successfully",Toast.LENGTH_SHORT).show();
                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
                //Toast.makeText(getActivity(),"save successfully",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
