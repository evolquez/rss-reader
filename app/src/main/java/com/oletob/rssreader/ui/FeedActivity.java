package com.oletob.rssreader.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.oletob.rssreader.R;
import com.oletob.rssreader.model.FeedAdapter;
import com.oletob.rssreader.model.FeedHolder;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    private ProgressDialog progressDialog   = null;
    private ArrayList<FeedHolder> feedNews  = null;
    private int type = -1; // 0 => DL, 1 => LD, 2 => EN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        if(getIntent().hasExtra("key")){
            String title = "";
            switch(getIntent().getStringExtra("key")){
                case "dl":
                    type = 0;
                    title = "Noticias de Diario Libre";
                    break;
                case "ld":
                    type = 1;
                    title = "Noticias de Listin Diario";
                    break;
                case "en":
                    type = 2;
                    title = "Noticias de El Nacional";
                    break;
            }

            getSupportActionBar().setTitle(title);
            prepareFeed();
        }
    }

    private void prepareFeed(){
        String feedUrl = "";
        switch(type){
            case 0:
                feedUrl = getString(R.string.rss_diario_libre);
                break;
            case 1:
                feedUrl = getString(R.string.rss_listin_diario);
                break;
            case 2:
                feedUrl = getString(R.string.rss_el_nacional);
                break;
        }

        if(feedUrl != ""){
            Parser parser = new Parser();
            parser.onFinish(new Parser.OnTaskCompleted(){
                @Override
                public void onTaskCompleted(ArrayList<Article> list) {
                    int length = list.size();
                    feedNews = new ArrayList<>();
                    for (int x = 0; x < length; x++){
                        feedNews.add(new FeedHolder(list.get(x).getTitle(), list.get(x).getLink(), list.get(x).getPubDate(), list.get(x).getImage()));
                    }

                    final FeedAdapter adapter = new FeedAdapter(FeedActivity.this, feedNews);

                    ((ListView) findViewById(R.id.feedList)).setAdapter(adapter);
                    ((ListView)findViewById(R.id.feedList)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(feedNews.get(i).link)));
                        }
                    });
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {
                    Toast.makeText(FeedActivity.this, "Error al leer el RSS", Toast.LENGTH_LONG).show();
                }
            });
            progressDialog = ProgressDialog.show(this, getString(R.string.app_name), getString(R.string.please_wait));
            parser.execute(feedUrl);

        }else{
            Toast.makeText(FeedActivity.this, "No se estableció la ruta de RSS", Toast.LENGTH_LONG).show();
        }
    }
}
