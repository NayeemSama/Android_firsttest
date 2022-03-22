package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xpressy.firsttest.Model.XMLDataModel;
import com.xpressy.firsttest.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XMLActivity extends BaseActivity{

    RecyclerView recyclerView;
    FloatingActionButton fab;
    XmlPullParserFactory pullParserFactory;
    XmlPullParser parser;
    String URL = "http://restapi.adequateshop.com/api/Traveler?page=6";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparsing);
        recyclerView = findViewById(R.id.lvXML);
        fab = findViewById(R.id.fabXML);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData() {

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            parser = pullParserFactory.newPullParser();

            String input = getXMLInputStream();
            InputStream is = new ByteArrayInputStream(input.getBytes());


//            InputStream in_s = getApplicationContext().getAssets().open("sample.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            ArrayList<XMLDataModel> countries=  parseXML(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }

    private ArrayList<XMLDataModel> parseXML(XmlPullParser parser) {
        ArrayList<XMLDataModel> items = null;
        XMLDataModel model = null;

        try {
            Log.d("LOGGG", String.valueOf(parser.getEventType()));


            int eventType = parser.getEventType();
            if (eventType == XmlPullParser.START_DOCUMENT){
                try {
                    eventType = parser.next();
                    eventType = parser.next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d("EVENT", String.valueOf(eventType));
//            if (eventType==XmlPullParser){
//                Log.d("NAME", parser.getName());
//            }



//            while (eventType != XmlPullParser.END_DOCUMENT){
//                String name;
//                switch (eventType){
//                    case XmlPullParser.START_DOCUMENT:
//
//                        break;
//                    case XmlPullParser.START_TAG:
//                        name = parser.getName();
//                        if (name.equals("Travelerinformation")){
//                            items = new ArrayList();
////                            model.setId(Integer.parseInt(parser.getAttributeValue(null,"id")));
//                        }
//                        else if(name.equals("Travelerinformation")){
//                            model = new XMLDataModel();
//                        }
//                        else if (model != null){
//                            if (name.equals("name")){
//                                model.setName(parser.nextText());
//                            } else if (name.equals("capital")){
//                                model.setEmail(parser.nextText());
//                            }
//                            else if(name.equals("id")){
//                                model.setId(Integer.parseInt(parser.nextText()));
//                            }
//                        }
//                        break;
//                    case XmlPullParser.END_TAG:
//                        name = parser.getName();
//                        if (name.equalsIgnoreCase("model") && model != null){
//                            items.add(model);
//                        }
//                }
//                eventType = parser.next();
//            }


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return items;

    }

    private String getXMLInputStream() {
        final String[] responseData = {""};

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("RES", response.toString());
                       responseData[0] = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", error.toString());
                    }
                }

        );

        queue.add(stringRequest);



        return responseData[0];

    }
}
