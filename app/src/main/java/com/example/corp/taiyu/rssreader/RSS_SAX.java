package com.example.corp.taiyu.rssreader;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RSS_SAX extends DefaultHandler {
    Boolean titleFound;//有無找到
    Boolean posttimeFound;
    Boolean contentFound;
    ParserRSS delegate;

    private String preTag;//分辨讀到哪一個
    private Map<String,String> tmpMap;//暫時存
    private List<Map<String,String>> result;//最終儲存


    public RSS_SAX(final URL url, ParserRSS parserRSS) {
        delegate = parserRSS;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    InputStream in = url.openStream();
                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = r.readLine()) != null) {
                        Log.i("input",line);
                    }
                    SAXParserFactory factory=SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    parser.parse(in,RSS_SAX.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (localName.equals("li")){
            titleFound = true;//有找到
            posttimeFound = true;
            contentFound = true;
            tmpMap = new HashMap<String, String>();
        }
        preTag = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equals(preTag) && titleFound && posttimeFound && contentFound){
            result.add(tmpMap);
            tmpMap = null;//清空
            titleFound = false;
            posttimeFound = false;
            contentFound = false;
        }

        preTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        String data = new String(ch,start,length);//char轉String
        switch (preTag)
        {
            case "h4" :
                tmpMap.put("title",data);
                result.add(tmpMap);
                break;
            case "h5":
                tmpMap.put("posttime",data);
                result.add(tmpMap);
                break;
            case "div":
                tmpMap.put("content",data);
                result.add(tmpMap);
                break;
        }
    }

}
