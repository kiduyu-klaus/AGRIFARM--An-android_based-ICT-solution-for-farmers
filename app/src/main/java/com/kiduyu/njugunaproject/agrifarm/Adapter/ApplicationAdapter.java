package com.kiduyu.njugunaproject.agrifarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiduyu.njugunaproject.agrifarm.Model.Application;
import com.kiduyu.njugunaproject.agrifarm.Model.News;
import com.kiduyu.njugunaproject.agrifarm.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {

    Context mcontext;
    private List<Application> applicationList;


    public ApplicationAdapter(Context context, List<Application> cList) {
        this.applicationList = cList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_appointment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Application application = applicationList.get(position);

        holder.single_application_timeago.setText("less than a day ago");
        holder.single_application_county.setText(application.getConsultant_name());
        holder.single_application_username.setText(application.getUsername());
        holder.single_application_school.setText("Tel : 071547425");
        holder.edt_myappications_descri11.setText("An Appointment to "+application.getConsultant_name()+" was set on "+application.getDateValue()+" , at "+application.getTime());
    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView single_application_username, single_application_county, amountreeerequest, single_application_school, single_application_timeago, edt_myappications_descri11;
        ImageView single_application_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            single_application_image = itemView.findViewById(R.id.single_application_user);
            single_application_username = itemView.findViewById(R.id.single_application_username);
            single_application_county = itemView.findViewById(R.id.single_application_county);
            single_application_school = itemView.findViewById(R.id.single_application_school);
            single_application_timeago = itemView.findViewById(R.id.single_application_timeago);
            edt_myappications_descri11 = itemView.findViewById(R.id.edt_myappications_descri11);
        }
    }
}
