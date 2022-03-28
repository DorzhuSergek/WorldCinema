package com.example.worldcinema.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.models.ChatListResponse;

import java.util.ArrayList;


public class AdapterChats  extends RecyclerView.Adapter<AdapterChats.ViewHolder>{
    private ArrayList<ChatListResponse> chatResponses;
    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public AdapterChats(ArrayList<ChatListResponse> chatResponses, Context context,OnItemClickListener onItemClickListener) {
        this.chatResponses=chatResponses;
        this.context=context;
        this.onItemClickListener =  onItemClickListener;
    }

    @NonNull
    @Override
    public AdapterChats.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new AdapterChats.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterChats.ViewHolder holder, int position) {
        ChatListResponse chatResponse = chatResponses.get(position);
        holder.setTextPoster(chatResponse.getName());

    }

    @Override
    public int getItemCount() {
        return chatResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final private TextView nameChat;
        final private TextView message;
        final private LinearLayout wrapper;
        private ViewHolder(View view) {
            super(view);
            this.nameChat=view.findViewById(R.id.nameChat);
            this.message=view.findViewById(R.id.messageChat);
            this.wrapper = view.findViewById(R.id.wrapper_item);
            this.wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick();
                }
            });
        }

        public void setTextPoster(String text) {
            this.nameChat.setText(text);
        }
    }
}
