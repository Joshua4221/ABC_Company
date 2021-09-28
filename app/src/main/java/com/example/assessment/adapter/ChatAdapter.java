package com.example.assessment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assessment.Model.ModelChat;
import com.example.assessment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder>{

//    private static final int MSG_TYPE_LEFT = 0;
//    private static final int MSG_TYPE_RIGHT = 1;
    private Context context;
    private List<ModelChat> chatList;
    private String imageUrl;

    private FirebaseUser fUser;

    public ChatAdapter(Context context, List<ModelChat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    public List<ModelChat> getChatList() {
        return chatList;
    }

    public void setChatList(List<ModelChat> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_message, parent, false);
        return  new ChatAdapter.MyHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyHolder holder, int position) {

        ModelChat chat = chatList.get(position);

        String title = chat.getTitle();
        String description = chat.getDescription();

        holder.title.setText(title);
        holder.description.setText(description);

    }

    @Override
    public int getItemCount() {
        return chatList ==  null ? 0 : chatList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView profileTv;
        TextView title, description;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//
//        fUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(chatList.get(position).getSender().equals(fUser.getUid())){
//            return MSG_TYPE_RIGHT;
//        } else {
//            return MSG;
//        }
//    }
}
