package com.example.project;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.TreeMap;

public class ExpandableListDataItemsAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> expandableTitleList;
    TreeMap<String, List<String>> expandableDetailList;

    public ExpandableListDataItemsAdapter(Context context, List<String> expandableListTitle,
                                          TreeMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableTitleList = expandableListTitle;
        this.expandableDetailList = expandableListDetail;
    }



    @Override
    public int getGroupCount() {
        return this.expandableTitleList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableDetailList.get(this.expandableTitleList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableTitleList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableDetailList.get(this.expandableTitleList.get(groupPosition)).get(childPosition);    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    /*public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return convertView; NOTE:DOESN'T WORK

    }*/

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        String header = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, parent, false);
        }


        TextView listHeader = (TextView) convertView.findViewById(R.id.header);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(header);

/*        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);*/


        return convertView;
    }


    @Override
    /*public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView; NOTE:DOESN'T WORK
    } */

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String listItem = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView listItemTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        listItemTextView.setText(listItem);
        Button button = convertView.findViewById(R.id.remove);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ValueToRemove = (String) getChild(groupPosition,childPosition);
                expandableDetailList.get(expandableTitleList.get(groupPosition)).remove(childPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;


        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValueToRemove = (String) getChild(groupPosition,childPosition);

            }
        }); */


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
