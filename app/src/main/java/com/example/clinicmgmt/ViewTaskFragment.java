package com.example.clinicmgmt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTaskFragment extends Fragment {
    //ListView listView;
    public static TextView datas;
    public ViewTaskFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_task, container, false);
        datas = (TextView) view.findViewById(R.id.tasktext);
        fetchTaskData process = new fetchTaskData();
        process.execute();
        return view;
    }
}
