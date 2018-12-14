package com.example.mani.realtim;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.mani.realtim.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class viewstatistics extends Activity implements OnItemSelectedListener{
    ArrayList<String> labels = new ArrayList<String>();
    ArrayList<BarEntry> entries = new ArrayList<>();
    StatisticsCalculationfirebase fi;
    int bre=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
fi=new StatisticsCalculationfirebase();
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
fi.firedatabaseinit(getApplicationContext());
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        List<String> categories = new ArrayList<String>();
        categories.add("Select ");
        categories.add("Province Wise");

        categories.add("Gender Wise");

       // categories.add("Help type");

        categories.add("Help type");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(position==0)
        {noth();}
        if(position==1)
        {bycity();}
if(position==2)
{bygen();}
        if(position==3)
        {
            byhelptype();
        }
        // Showing selected spinner itemtttttttttttt

    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void showmessage(String v) {
        Toast.makeText(this, v, Toast.LENGTH_SHORT).show();
    }
public void byhelptype()
{
bre=0;
    labels = new ArrayList<String>();
     entries = new ArrayList<>();
    BarChart chart = (BarChart) findViewById(R.id.chart);
    ArrayList<String> label=new ArrayList<>();
    label.add("Food");
    label.add("Book");
    label.add("Emergency");
    label.add("Clothes");
    label.add("Money");
    label.add("Fire");
    label.add("Blood");
    label.add("Service");



   addLabel(label);
    ArrayList<String> entrys=new ArrayList<>();


    entrys.add(fi.getfood());

    entrys.add(fi.getbook());
    entrys.add(fi.getemergency());
   entrys.add(fi.getclothes());
    entrys.add(fi.getmoney());
    entrys.add(fi.getfire());
    entrys.add(fi.getblood());
    entrys.add(fi.getservice());


    addvalue(entrys);

/*
    ArrayList<BarEntry> entries = new ArrayList<>();
    entries.add(new BarEntry(4f, 0));
    entries.add(new BarEntry(8f, 1));
    entries.add(new BarEntry(6f, 2));
    entries.add(new BarEntry(12f, 3));
    entries.add(new BarEntry(18f, 4));
    entries.add(new BarEntry(9f, 5));
    ArrayList<String> labels = new ArrayList<String>();
    labels.add("January");
    labels.add("February");
    labels.add("March");
    labels.add("April");
    labels.add("May");
    labels.add("June");
    */
    BarDataSet dataset = new BarDataSet(entries, "# of Calls");
    BarData data = new BarData(labels, dataset);
    chart.setData(data);
    chart.setDescription("Help type ");

}



    public void noth()
    {
    }
    public void addLabel(ArrayList<String> list)
    {

       for(String l:list)
       {
           labels.add(l);
       }
    }
    public void addvalue(ArrayList<String> list)
    {

        for(String l:list)
        {

            float f= Float.parseFloat(l+"f");
            entries.add(new BarEntry(f,bre));
            bre++;
        }
    }


    public void bygen()
    {
        bre=0;
        labels = new ArrayList<String>();
        entries = new ArrayList<>();
        BarChart chart = (BarChart) findViewById(R.id.chart);
        ArrayList<String> label=new ArrayList<>();
        label.add("Male");
        label.add("Female");



        addLabel(label);
        ArrayList<String> entrys=new ArrayList<>();
        entrys.add(fi.getmale());
        entrys.add(fi.getfemale());


        addvalue(entrys);
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Help type ");

    }
    public void bycity()
    {
        bre=0;
        labels = new ArrayList<String>();
        entries = new ArrayList<>();
        BarChart chart = (BarChart) findViewById(R.id.chart);
        ArrayList<String> label=new ArrayList<>();
        label.add("Punjab");
        label.add("Balochistan");
        label.add("Sindh");

        label.add("Kpk");

        addLabel(label);
        ArrayList<String> entrys=new ArrayList<>();
        entrys.add(fi.getpunjab());
        entrys.add(fi.getBalochistan());
        entrys.add(fi.getsindh());

        entrys.add(fi.getkpk());

        addvalue(entrys);
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("By province");
    }
}




















