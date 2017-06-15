package com.example.vikas.where_i_am;

/**
 * Created by vikas on 7/4/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class HintActivity extends AppCompatActivity {
    private List<Hint_Adapter> hintList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Hint mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hints);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new Hint(hintList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareHintData();
    }

    private void prepareHintData()
    {
        Hint_Adapter hint = new Hint_Adapter("1).Earth,our home", "Have a look adjacent to owl");
        hintList.add(hint);

        hint = new Hint_Adapter("2).The sun that gives us warmth and life", "Have a look in extereme right");
        hintList.add(hint);

        hint = new Hint_Adapter("3).Two swooping owls", "Have a look adjacent to Earth");
        hintList.add(hint);

        hint = new Hint_Adapter("4).One shy monkey", "Have a look in the tree branches");
        hintList.add(hint);

        hint = new Hint_Adapter("5).Five exploring ants", "Have a look in the corners");
        hintList.add(hint);

        hint = new Hint_Adapter("6).A hungry caterpillar", "Have a look adjacent to owl");
        hintList.add(hint);


        hint = new Hint_Adapter("7).A butterfly wing", "Have a look in the bottom");
        hintList.add(hint);


        hint = new Hint_Adapter("8).Two cats waiting", "Try to search in the leaves and look around the girl");
        hintList.add(hint);


        hint = new Hint_Adapter("9).A pair of slippers", "Try to look around the girl");
        hintList.add(hint);

        hint = new Hint_Adapter("10).Four red Flowers", "Have a to the left side");
        hintList.add(hint);

        hint = new Hint_Adapter("11).A book", "Try to look around the girl");
        hintList.add(hint);

        hint = new Hint_Adapter("12).A kite", "Have a look at the top");
        hintList.add(hint);


        mAdapter.notifyDataSetChanged();
    }
}