package com.example.rajrajas.bemoapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.rajrajas.bemoapp.Adapter.MessageAdapter;
import com.example.rajrajas.bemoapp.Data.Message;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.databinding.ChatlayoutBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rajrajas on 6/2/2017.
 */

public class ChatActivity extends ActionBarActivity {

    EditText mMessageEditText;
    String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    private DatabaseReference message_root;
    String movie_id, temp_key;
    private List<Message> message_data;
    private List<String> data;
    private Dialog dialog;


    ChatlayoutBinding chatlayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         chatlayoutBinding = DataBindingUtil.setContentView(this, R.layout.chatlayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("Chat Room");
        }

        mMessageEditText = (EditText) this.findViewById(R.id.message_text);

        dialog_Show();
        movie_id = getIntent().getStringExtra("movie_id");
        message_data = new ArrayList<>();
        name= getIntent().getStringExtra("user");

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChild(movie_id))
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(movie_id, movie_id);
                    root.updateChildren(map);
                }

                message_root = FirebaseDatabase.getInstance().getReference().child(movie_id);

                message_root.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                append_chat_conversation(dataSnapshot);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //handle databaseError
                            }
                        });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {

       data=new ArrayList<>();

        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

           // Iterator i = dataSnapshot.child(dataSnapshot.getKey()).getChildren().iterator();

            data.add(childDataSnapshot.getValue().toString());

                //message_data.add(new Message(chat_user_name, chat_msg));
        }
        initRecycler(chatlayoutBinding.itemList);
    }



    public void onSendButtonClick(View v)
    {
        View view = getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        Map<String, Object> map_1 = new HashMap<String, Object>();
        temp_key = message_root.push().getKey();
        message_root.updateChildren(map_1);

        Map<String, Object> map_key = new HashMap<String, Object>();

        map_key.put(temp_key, "");
        message_root.updateChildren(map_key);

        DatabaseReference message_root_1 = message_root.child(temp_key);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", name);
        map2.put("msg", mMessageEditText.getText().toString());
        message_root_1.updateChildren(map2);

        message_data.add(new Message(name, mMessageEditText.getText().toString()));
        chatlayoutBinding.itemList.getAdapter().notifyDataSetChanged();

        mMessageEditText.setText("");

        NestedScrollView sv = (NestedScrollView)findViewById(R.id.nested_view);
        sv.fullScroll(View.FOCUS_DOWN);

    }

    private void initRecycler(RecyclerView productsRecycler)
    {
        for(int i=0;i<data.size();i++)
        {
            String temp=data.get(i);
            //{name=

         try {

             String data_1[] = temp.split(",");
             String name[]=data_1[0].split("=");
             String mes[]=data_1[1].split("=");

             message_data.add(new Message(name[1], mes[1].substring(0,mes[1].length()-1)));
         }catch (Exception e)
         {
             message_data.add(new Message(name, e.getMessage()));

         }


        }

        productsRecycler.setHasFixedSize(true);
        productsRecycler.setAdapter(new MessageAdapter(ChatActivity.this, message_data, name));

        productsRecycler.getAdapter().notifyDataSetChanged();

        dialog.dismiss();
    }


    void dialog_Show() {
        dialog = new Dialog(ChatActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progressbar);
        dialog.setCancelable(false);

        dialog.show();
    }


}