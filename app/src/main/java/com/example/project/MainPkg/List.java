package com.example.project.MainPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.project.AddPkg.AddActivity;
import com.example.project.R;
import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;
import java.util.TreeMap;

import kotlin.Triple;


public class List extends Fragment {
    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    java.util.List<String> expandableTitleList;
    TreeMap<String, java.util.List<Triple<String, String, String>>> expandableDetailList;
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

            itemModalArrayList = dbHandler.readCourses();
            for (ReadModal readModal : itemModalArrayList) {
                if (readModal.date != null) {
                    ExpandableListDataItems.Insert(readModal.date, readModal.amount, readModal.name, readModal.item);
                }
            }
            expandableDetailList = ExpandableListDataItems.getData();
            expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
            expandableListAdapter = new ExpandableListDataItemsAdapter(getActivity(), expandableTitleList, expandableDetailList);
            expandableListViewExample.setAdapter(expandableListAdapter);
            AddActivity.update1 = false;


        expandableListViewExample.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListViewExample.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
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
        if (AddActivity.update1) {
            itemModalArrayList = dbHandler.readLast(AddActivity.count1);
            for (ReadModal readModal : itemModalArrayList) {
                if (readModal.date != null) {
                    ExpandableListDataItems.Insert(readModal.date, readModal.amount, readModal.name, readModal.item);
                }
            }
            expandableDetailList = ExpandableListDataItems.getData();
            expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
            expandableListAdapter = new ExpandableListDataItemsAdapter(getActivity(), expandableTitleList, expandableDetailList);
            expandableListViewExample.setAdapter(expandableListAdapter);
            AddActivity.update1 = false;
            AddActivity.count1 = 0;
        }
    }
}