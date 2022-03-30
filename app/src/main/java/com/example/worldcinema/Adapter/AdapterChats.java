package com.example.worldcinema.Adapter;

import static com.example.worldcinema.network.models.ChatResponse.LayoutOne;
import static com.example.worldcinema.network.models.ChatResponse.LayoutTwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.models.ChatListResponse;
import com.example.worldcinema.network.models.ChatResponse;

import java.util.ArrayList;
import java.util.List;


public class AdapterChats  extends RecyclerView.Adapter{

    private List<ChatResponse> chatResponses;

    public AdapterChats(List<ChatResponse> chatItems) {
        this.chatResponses = chatItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View viewOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_me, parent, false);
                return new LayoutOneViewHolder(viewOne);
            case LayoutTwo:
                View viewTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_opponent, parent, false);
                return new LayoutTwoViewHolder(viewTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (chatResponses.get(position).getViewType()){
            case LayoutOne:
                String text = chatResponses.get(position).getText();
                ((LayoutOneViewHolder)holder).setTxtMessage(text);
                ((LayoutOneViewHolder)holder).setTxtNamePerson(text);
                ((LayoutOneViewHolder)holder).imageView.setImageResource(R.drawable.poster_chat);
                break;
            case LayoutTwo:
                String text1 = chatResponses.get(position).getText();
                ((LayoutTwoViewHolder)holder).setTxtMessage(text1);
                ((LayoutTwoViewHolder)holder).setTxtNamePerson(chatResponses.get(position).getFirstName()+" "+chatResponses.get(position).getLastName() + " " +chatResponses.get(position).getCreationDateTime());
                ((LayoutTwoViewHolder)holder).imageView.setImageResource(R.drawable.avatar_oppo);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatResponses.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatResponses.size();
    }

    class LayoutOneViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        public ImageView imageView;

        public LayoutOneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_my);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_my);
            imageView = itemView.findViewById(R.id.image_my);
        }

        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }
    }
    class LayoutTwoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        public ImageView imageView;

        public LayoutTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_opponent);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_opponent);
            imageView = itemView.findViewById(R.id.image_opponent);
        }

        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }
    }
}
