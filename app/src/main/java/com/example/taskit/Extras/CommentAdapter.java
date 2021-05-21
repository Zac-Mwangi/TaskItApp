package com.example.taskit.Extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taskit.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mCtx;
    private List<commentsModel> List;
    //constructor
    public CommentAdapter(Context mCtx, List<commentsModel> List) {
        this.mCtx = mCtx;
        this.List = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.comment_list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        commentsModel commentsModel = List.get(position);
        holder.timeTV.setText(""+commentsModel.getDate_posted());
        holder.commentTV.setText(commentsModel.getFeedback_text());
    }
    @Override
    public int getItemCount() {
        return List.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView timeTV,commentTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTV = itemView.findViewById(R.id.timeTV);
            commentTV = itemView.findViewById(R.id.commentTV);
        }
    }
}