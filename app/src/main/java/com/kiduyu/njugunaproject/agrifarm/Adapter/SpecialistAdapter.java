package com.kiduyu.njugunaproject.agrifarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kiduyu.njugunaproject.agrifarm.BookAppointment;
import com.kiduyu.njugunaproject.agrifarm.Constants.Constants;
import com.kiduyu.njugunaproject.agrifarm.Model.Specialist;
import com.kiduyu.njugunaproject.agrifarm.R;

import java.util.List;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.MyViewHolder> {

    Context mcontext;
    private List<Specialist> consultantList;

    public SpecialistAdapter(Context context, List<Specialist> cList) {
        this.consultantList = cList;
        this.mcontext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.consultant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Specialist consultant = consultantList.get(position);

        Glide.with(mcontext).load(Constants.Baseimageurl+consultant.getImage()).into(holder.cover);
        holder.title.setText(consultant.getName());
        holder.location.setText("Located in: "+consultant.getLocation());
        holder.phone.setText("Consultant Cell: "+consultant.getPhone());
        holder.date.setText("Joined our System on: "+consultant.getDate());

        holder.bookAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mcontext, BookAppointment.class);
                intent.putExtra("consultant",consultant.getName());
                intent.putExtra("consultant_phone",consultant.getPhone());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return consultantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, phone, date;
        Button bookAp;
        ImageView cover;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.consultant_title);
            location = itemView.findViewById(R.id.consultant_location);
            phone = itemView.findViewById(R.id.consultant_phone);
            date = itemView.findViewById(R.id.consultant_date);

            bookAp = itemView.findViewById(R.id.book_appointment);

            cover = itemView.findViewById(R.id.consultant_image);
        }
    }
}
