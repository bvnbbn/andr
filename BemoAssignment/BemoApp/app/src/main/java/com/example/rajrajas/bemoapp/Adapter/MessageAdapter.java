package com.example.rajrajas.bemoapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rajrajas.bemoapp.Data.Message;
import com.example.rajrajas.bemoapp.databinding.MessageListBinding;

import java.util.List;

/**
 * Created by rajrajas on 6/3/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>
{
    private Context context;
    private List<Message> data;
    private LayoutInflater inflater;
    private String current_user_name;


    public MessageAdapter(Context context, List<Message> data,String current_user_name)
    {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data=data;
        this.current_user_name=current_user_name;
    }


    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final MessageListBinding messageListBinding = MessageListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new MessageViewHolder(context, messageListBinding,current_user_name);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
