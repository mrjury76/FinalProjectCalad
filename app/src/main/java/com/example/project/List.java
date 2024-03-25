package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;
import java.util.TreeMap;


public class List extends Fragment {
    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    java.util.List<String> expandableTitleList;
    TreeMap<String, java.util.List<String>> expandableDetailList;

    private DBHandler dbHandler;

    private ArrayList<ReadModal> itemModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

            expandableListViewExample = (ExpandableListView) view.findViewById(R.id.expandableListViewSample);
            expandableDetailList = ExpandableListDataItems.getData();
            expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
            expandableListAdapter = new ExpandableListDataItemsAdapter(getActivity(), expandableTitleList, expandableDetailList);
            expandableListViewExample.setAdapter(expandableListAdapter);
        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());


        expandableListViewExample.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
//                    Toast.makeText(getActivity(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
                }
            });

            expandableListViewExample.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
//                    Toast.makeText(getActivity(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
                }
            });


            expandableListViewExample.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    Toast.makeText(getActivity(), expandableTitleList.get(groupPosition)
                            + " -> "
                            + expandableDetailList.get(
                            expandableTitleList.get(groupPosition)).get(
                            childPosition), Toast.LENGTH_SHORT
                    ).show();
                    return false;
                }
            });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        itemModalArrayList = dbHandler.readCourses();
        for (ReadModal readModal : itemModalArrayList) {
            if (readModal.date != null) {
                ExpandableListDataItems.Insert(readModal.date, readModal.amount);
            }
        }
        expandableDetailList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        expandableListAdapter = new ExpandableListDataItemsAdapter(getActivity(), expandableTitleList, expandableDetailList);
        expandableListViewExample.setAdapter(expandableListAdapter);
    }
}