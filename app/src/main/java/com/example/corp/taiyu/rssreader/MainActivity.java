package com.example.corp.taiyu.rssreader;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    List<HashMap<String,String>> l_title= new ArrayList<HashMap<String,String>>();

    List<Map<String,String>> posttime;
    List<Map<String,String>> content;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posttime = new ArrayList<Map<String,String>>();
        content = new ArrayList<Map<String,String>>();

        final String[] animalName={"AAAA","BBBBB","WTFFFF","?????"};
        ListView lv= (ListView)findViewById(android.R.id.list);


//        final int[] animalImages={R.drawable.blablabla};
//        String[] from={"title","img"};
//        int[] to = new int[] { R.id.for_text,R.id.for_img};

        String[] from={"title"};
        int[] to = new int[] { R.id.for_text};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for (int i=0;i<animalName.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("title",animalName[i]);
//            hashMap.put("img",animalImages[i]);
            fillMaps.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), fillMaps, R.layout.rss, from, to);
        lv.setAdapter(adapter);






//        try{
//            URL url = new URL("http://feeds.reuters.com/Reuters/worldNews");
//            new RSS_SAX(url, new ParserRSS() {
//                @Override
//                public void setTitle(final String title) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            Log.i("url",title);
//
//                        }
//                    });
//                }
//
//                @Override
//                public void setPosttime(String posttime) {
//
//                }
//
//                @Override
//                public void setContent(String content) {
//
//                }
//            }){
//
//            };
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
