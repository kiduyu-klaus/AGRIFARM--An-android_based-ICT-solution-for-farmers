package com.kiduyu.njugunaproject.agrifarm.UserFargments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kiduyu.njugunaproject.agrifarm.Adapter.ApplicationAdapter;
import com.kiduyu.njugunaproject.agrifarm.Adapter.SpecialistAdapter;
import com.kiduyu.njugunaproject.agrifarm.Constants.Constants;
import com.kiduyu.njugunaproject.agrifarm.Model.Application;
import com.kiduyu.njugunaproject.agrifarm.Model.Specialist;
import com.kiduyu.njugunaproject.agrifarm.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppointmentsFragment extends Fragment {
    private ApplicationAdapter applicationAdapter;
    public static boolean isRefreshed;
    RequestQueue mRequestQueue;
    RecyclerView recycler;
    EditText search;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Application> applicationArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.appointments_fragment, container, false);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        EditText editText = layout.findViewById(R.id.search_editText_appointments);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        recycler = layout.findViewById(R.id.recyclerview_appointments);

        swipeRefreshLayout = layout.findViewById(R.id.appointment_refresh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setFocusable(false);

        fetchData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                fetchData();
                swipeRefreshLayout.setRefreshing(false);
                FancyToast.makeText(getActivity(), "Data refreshed!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
            }
        });


        return layout;
    }

    private void filter(String text) {

        ArrayList<Application> filteredList = new ArrayList<>();
        for (Application item : applicationArrayList) {
            if (item.getUsername().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        applicationAdapter = new ApplicationAdapter(getActivity(), filteredList);
        recycler.setAdapter(applicationAdapter);
        applicationAdapter.notifyDataSetChanged();
    }

    private void fetchData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Fetching Appointments");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String urlForJsonObject = Constants.BASE_API + Constants.SPECIALIST_LIST;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                urlForJsonObject,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("application");

                            if (isRefreshed) {
                               applicationArrayList.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String sname = consultant.getString("sname");
                                    String uname = consultant.getString("uname");
                                    String uphone = consultant.getString("uphone");
                                    String sphone = consultant.getString("sphone");
                                    String datevalue = consultant.getString("datevalue");
                                    String timevalue = consultant.getString("timevalue");

                                    Log.d("TAG", "onResponse: " + sname + " " + uname + " " + uphone + " " + sphone + " " + datevalue + " " + timevalue);
                                    //Loading.showProgressDialog(getActivity(),false);
                                   applicationArrayList.add(new Application(sname,uname,uphone,sphone,datevalue,timevalue));
                                    //tipArrayList.add(new Tip(title, description ,image));

                                }

                            } else {
                                applicationArrayList.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String sname = consultant.getString("sname");
                                    String uname = consultant.getString("uname");
                                    String uphone = consultant.getString("uphone");
                                    String sphone = consultant.getString("sphone");
                                    String datevalue = consultant.getString("datevalue");
                                    String timevalue = consultant.getString("timevalue");

                                    Log.d("TAG", "onResponse: " + sname + " " + uname + " " + uphone + " " + sphone + " " + datevalue + " " + timevalue);
                                    //Loading.showProgressDialog(getActivity(),false);
                                    applicationArrayList.add(new Application(sname,uname,uphone,sphone,datevalue,timevalue));

                                }
                            }

                            progressDialog.dismiss();

                            applicationAdapter = new ApplicationAdapter(getActivity(), applicationArrayList);
                         recycler.setAdapter(applicationAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                progressDialog.dismiss();
            }
        });
        mRequestQueue.add(request);
    }

}
