package com.example.taskit.Extras;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskit.R;
import com.example.taskit.SampleActivity;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<com.example.taskit.Extras.HomeAdapter.HomeView> {

    List<Integer> imageList ;
    List<String> imageDescriptionList;
    Context mContext;

    public HomeAdapter(List<Integer> imageList, List<String> imageDescriptionList) {
        this.imageList = imageList;
        this.imageDescriptionList = imageDescriptionList;
    }
    //before passing anything create a view class
    //finally implement methods
    @NonNull
    @Override
    public com.example.taskit.Extras.HomeAdapter.HomeView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //pass the layout resource file ie row_home
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home,parent,false);
        mContext = parent.getContext();
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home,parent,false);
        return  new com.example.taskit.Extras.HomeAdapter.HomeView(view);//pass the object we created
        //We have adapter ready so lets keep the data ready ie in main activity page
        //OR
        //HomeView holder = new HomeView(view);
        // return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.taskit.Extras.HomeAdapter.HomeView holder, int position) {

        //passing values
        holder.image.setImageResource(imageList.get(position));
        holder.imageDescr.setText(imageDescriptionList.get(position));

        holder.hCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String page = imageDescriptionList.get(position);
                if(page.equals(mContext.getResources().getString(R.string.laundry))){
                    //Toast.makeText(mContext, "laundry", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.laundry).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.electrician))){
                    //Toast.makeText(mContext, "electrician", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.electrician).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.plumber))){
                    // Toast.makeText(mContext, "plumber", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.plumber).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.painting))){
                    //Toast.makeText(mContext, "painting", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.painting).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.movers))){
                    // Toast.makeText(mContext, "movers", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.movers).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.cobbler))){
                    // Toast.makeText(mContext, "cobbler", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.cobbler).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.delivery))){
                    //Toast.makeText(mContext, "delivery", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.delivery).toLowerCase() );
                    mContext.startActivity(sample);
                }
                if(page.equals(mContext.getResources().getString(R.string.mechanics))){
                    // Toast.makeText(mContext, "mechanics", Toast.LENGTH_SHORT).show();
                    Intent sample = new Intent(mContext, SampleActivity.class);
                    sample.putExtra("service",mContext.getResources().getString(R.string.mechanics).toLowerCase() );
                    mContext.startActivity(sample);
                }
            }
        });
        //finally run
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    //before passing anything create a view class
    public class HomeView extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageDescr;
        CardView hCV;
        //create constructor of that view
        public HomeView(@NonNull View itemView) {
            super(itemView);

            imageDescr = (TextView)itemView.findViewById(R.id.imageDescription);
            image = (ImageView)itemView.findViewById(R.id.image);
            hCV = itemView.findViewById(R.id.homeCardV);
        }
    }
}
