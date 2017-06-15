package com.example.rajrajas.bemoapp.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;

import com.example.rajrajas.bemoapp.Data.Message;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.databinding.MessageListBinding;

/**
 * Created by rajrajas on 6/3/2017.
 */

 class MessageViewHolder extends RecyclerView.ViewHolder
{
    private MessageListBinding messageListBinding;
    private Context context;
    private String current_user_name;

     MessageViewHolder(Context context, MessageListBinding messageListBinding,String current_user_name)
    {
        super(messageListBinding.getRoot());
        this.context=context;
        this.messageListBinding=messageListBinding;
        this.current_user_name=current_user_name;
    }

    void bind(Message item_data)
    {
        messageListBinding.setItem(item_data);

        try {
            if (item_data.getName().equals(current_user_name)) {
                float d = context.getResources().getDisplayMetrics().density;

                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.FILL_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins((int) (70 * d), (int) (10 * d), (int) (10 * d), (int) (10 * d));
                messageListBinding.mainContainer.setLayoutParams(layoutParams);
                messageListBinding.mainContainer.setBackgroundResource(R.drawable.rounded_corner1);
                messageListBinding.nameText.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }
        }
        catch (Exception ignoredExecption)
        {

        }

    }


}
