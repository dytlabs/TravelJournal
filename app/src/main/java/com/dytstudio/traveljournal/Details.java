package com.dytstudio.traveljournal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dytstudio.traveljournal.adapter.ImageAdapter;
import com.dytstudio.traveljournal.customview.SpaceItemDecoration;
import com.dytstudio.traveljournal.model.ImageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Details extends BaseActivity {
    ImageAdapter imageAdapter;
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupToolbar(R.id.toolbar, "Bali Bird Park");
        View view = getWindow().getDecorView();
        setLightStatusBar(view, this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {
                    TextView title = (TextView) toolbar.findViewById(R.id.tv_title);
                    title.setVisibility(View.VISIBLE);
                    toolbar.getContext().setTheme(R.style.Theme_AppCompat_Light);
                }else{
                    TextView title = (TextView) toolbar.findViewById(R.id.tv_title);
                    title.setVisibility(View.GONE);
                    toolbar.getContext().setTheme(R.style.FullWhite);
                }
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        imageAdapter = new ImageAdapter(init(), this);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(imageAdapter);

        String s = "Nice share @mahisa. Very nice place to go, a lot of beautiful @birds from all over the world";

        Pattern pattern = Pattern.compile("@\\w+");

        Matcher matcher = pattern.matcher(s);
        List<String> result = new ArrayList<>();
        List<Integer> wordLength = new ArrayList<>();
        List<Integer> startPosition = new ArrayList<>();
        while (matcher.find())
        {
            result.add(matcher.group());
            wordLength.add(matcher.group().length());
            startPosition.add(matcher.start());
        }
        Spannable wordtoSpan = new SpannableString(s);

        for (int i = 0; i < result.size(); i++){
            wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#FF9FA4")), startPosition.get(i), startPosition.get(i)+wordLength.get(i), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        TextView comment = (TextView) findViewById(R.id.tv_comment);
        comment.setText(wordtoSpan);
    }

    String[] url = {"http://tekooo.com/wp-content/uploads/2015/10/Pengunjung-bebas-berfoto-bersama-burung-burung-disana.jpg"
            , "http://tekooo.com/wp-content/uploads/2015/10/Koleksi-burung-di-Bali-Bird-Park.png"
            , "http://tekooo.com/wp-content/uploads/2015/10/Pengunjung-bebas-berfoto-bersama-burung-burung-disana.jpg"
            , "http://tekooo.com/wp-content/uploads/2015/10/Koleksi-burung-di-Bali-Bird-Park.png"
            , "http://tekooo.com/wp-content/uploads/2015/10/Pengunjung-bebas-berfoto-bersama-burung-burung-disana.jpg"
            , "http://tekooo.com/wp-content/uploads/2015/10/Koleksi-burung-di-Bali-Bird-Park.png"};

    public List<ImageModel> init(){
        List<ImageModel> imageModelList = new ArrayList<>();
        for (int i=0; i<url.length; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setUrl(url[i]);
            imageModelList.add(imageModel);
        }
        return imageModelList;
    }

}
