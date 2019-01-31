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
public class AddExpensesFragment extends Fragment {
    Button btn;
    EditText description,expensesDate,expensesBy,rupees;

    public AddExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expenses, container, false);
        btn = view.findViewById(R.id.save_expenses);
        description = view.findViewById(R.id.description);
        expensesDate = view.findViewById(R.id.expenses_date);
        expensesBy = view.findViewById(R.id.expenses_by);
        rupees = view.findViewById(R.id.rupee);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Description = description.getText().toString();
                String LastDate = expensesDate.getText().toString();
                String ExpensesBy = expensesBy.getText().toString();
                String Rupees = rupees.getText().toString();
                File file=null;
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                    String reg_url = "http://rajeshkumargupta.000webhostapp.com/addExpenses.php";
                    try{
                        URL url= new URL(reg_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                        String data = URLEncoder.encode("description","UTF-8") +"="+URLEncoder.encode(Description,"UTF-8")+"&"+
                                URLEncoder.encode("expensesDate","UTF-8") +"="+URLEncoder.encode(LastDate,"UTF-8")+"&"+
                                URLEncoder.encode("expensesBy","UTF-8") +"="+URLEncoder.encode(ExpensesBy,"UTF-8")+"&"+
                                URLEncoder.encode("rupees","UTF-8") +"="+URLEncoder.encode(Rupees,"UTF-8");
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
