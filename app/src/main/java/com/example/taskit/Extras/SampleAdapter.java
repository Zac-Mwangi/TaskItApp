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

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {
    private Context mCtx;
    private List<SampleModel> List;

    //onClickListener
    private OnItemClickListener mListener;
    //create interface
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    //END

    //constructor
    public SampleAdapter(Context mCtx, List<SampleModel> List) {
        this.mCtx = mCtx;
        this.List = List;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SampleModel sampleModel = List.get(position);
        holder.name_volley.setText(sampleModel.getFullname());
        holder.contact_volley.setText(sampleModel.getPhone());
        holder.location_volley.setText(sampleModel.getLocation_string());
    }
    @Override
    public int getItemCount() {
        return List.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_volley,contact_volley,location_volley;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_volley = itemView.findViewById(R.id.name_volley);
            contact_volley = itemView.findViewById(R.id.contact_volley);
            location_volley = itemView.findViewById(R.id.location_volley);

            itemView.setOnClickListener(v -> {
                if(mListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            });
        }
    }
}