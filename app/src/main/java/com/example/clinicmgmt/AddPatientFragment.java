package com.example.clinicmgmt;


import android.content.Context;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class AddPatientFragment extends Fragment {
EditText p_name,p_address,p_phone,p_email,p_gender,p_age;
Button btn;
//patient_age
// patient_phone
//patient_email
//patient_address
//patient_name
//patient_gender
    public AddPatientFragment() {// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_patient, container, false);
        btn = view.findViewById(R.id.save_btn);
        p_name = view.findViewById(R.id.patient_name);
        p_address = view.findViewById(R.id.patient_address);
        p_phone = view.findViewById(R.id.patient_phone);
        p_email = view.findViewById(R.id.patient_email);
        p_gender = view.findViewById(R.id.patient_gender);
        p_age = view.findViewById(R.id.patient_age);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String Patient_name = p_name.getText().toString();
                String Patient_address = p_address.getText().toString();
                String Patient_phone = p_phone.getText().toString();
                String Patient_email = p_email.getText().toString();
                String Patient_gender = p_gender.getText().toString();
                String Patient_age = p_age.getText().toString();
                File file=null;
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                    String reg_url = "http://rajeshkumargupta.000webhostapp.com/addPatientApi.php";
                    try{
                        URL url= new URL(reg_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                        String data = URLEncoder.encode("name","UTF-8") +"="+URLEncoder.encode(Patient_name,"UTF-8")+"&"+
                                URLEncoder.encode("address","UTF-8") +"="+URLEncoder.encode(Patient_address,"UTF-8")+"&"+
                                URLEncoder.encode("gender","UTF-8") +"="+URLEncoder.encode(Patient_gender,"UTF-8")+"&"+
                                URLEncoder.encode("email","UTF-8") +"="+URLEncoder.encode(Patient_email,"UTF-8")+"&"+
                                URLEncoder.encode("phoneNo","UTF-8") +"="+URLEncoder.encode(Patient_phone,"UTF-8")+"&"+
                                URLEncoder.encode("Dob","UTF-8") +"="+URLEncoder.encode(Patient_age,"UTF-8");
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
                try{
                    FileOutputStream fileOutputStream = getActivity().openFileOutput("patientData", Context.MODE_PRIVATE);
                    fileOutputStream.write(Patient_name.getBytes());
                    fileOutputStream.write(Patient_address.getBytes());
                    fileOutputStream.write(Patient_phone.getBytes());
                    fileOutputStream.write(Patient_email.getBytes());
                    fileOutputStream.write(Patient_gender.getBytes());
                    fileOutputStream.write(Patient_age.getBytes());
                    fileOutputStream.close();

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                }
            }
        });
        return view;
    }

}
