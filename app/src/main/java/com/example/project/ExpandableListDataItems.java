package com.example.project;

import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Arrays;
import java.util.TreeMap;
import java.time.LocalDate;


public class ExpandableListDataItems {

    private DBHandler dbHandler;
    private ArrayList<ReadModal> itemModalArrayList;


    static TreeMap<String, List<String>> expandableDetailList = new TreeMap<>(new DateComparator());

    public static TreeMap<String, List<String>> getData() {
        //  LinkedHashMap<String, List<String>> expandableDetailList = new LinkedHashMap<String, List<String>>();

        /*List<String> aprilEight = new ArrayList<String>();
        aprilEight.add("Apple");
        aprilEight.add("Orange");

        List<String> aprilNine = new ArrayList<String>();
        aprilNine.add("Tomato");
        aprilNine.add("Potato");

        List<String> aprilTen = new ArrayList<String>();
        aprilTen.add("Chicken");
        aprilTen.add("Lamb");

        List<String> aprilElven = new ArrayList<String>();
        aprilElven.add("Milk");
        aprilElven.add("Cheese");

        List<String> aprilTwelve = new ArrayList<String>();
        aprilTwelve.add("Cookie");
        aprilTwelve.add("Fish");

        List<String> aprilThirteen = new ArrayList<String>();
        aprilThirteen.add("Cake");
        aprilThirteen.add("Soda"); */


        /*expandableDetailList.put("8/3/24", aprilEight);
        expandableDetailList.put("9/3/24", aprilNine);
        expandableDetailList.put("10/3/24", aprilTen);
        expandableDetailList.put("11/3/24", aprilElven);
        expandableDetailList.put("9/2/24",aprilTwelve);
        expandableDetailList.put("11/4/22",aprilThirteen); */
        return expandableDetailList;


    }

    public static void Insert(String date, String food) {

      //  List<String> items = expandableDetailList.get(date);
        if (expandableDetailList.containsKey(date)) {
            //items.add(food);

        } else {
            List<String> newList = new ArrayList<>();
            newList.add(food);
            expandableDetailList.put(date, newList);

        }
    }

    static class DateComparator implements Comparator<String> {
        @Override
        public int compare(String date1, String date2) {

            String[] parts1 = date1.split("/");
            String[] parts2 = date2.split("/");

            int day1 = Integer.parseInt(parts1[0]);
            int month1 = Integer.parseInt(parts1[1]);
            int year1 = Integer.parseInt(parts1[2]);

            int day2 = Integer.parseInt(parts2[0]);
            int month2 = Integer.parseInt(parts2[1]);
            int year2 = Integer.parseInt(parts2[2]);

            if (year1 != year2) {
                return Integer.compare(year1, year2);
            }

            if (month1 != month2) {
                return Integer.compare(month1, month2);
            }

            return Integer.compare(day1, day2);

        }


    }

}