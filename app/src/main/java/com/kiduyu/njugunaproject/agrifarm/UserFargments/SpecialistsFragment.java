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
import com.kiduyu.njugunaproject.agrifarm.Adapter.NewsAdapter;
import com.kiduyu.njugunaproject.agrifarm.Adapter.SpecialistAdapter;
import com.kiduyu.njugunaproject.agrifarm.Constants.Constants;
import com.kiduyu.njugunaproject.agrifarm.Model.News;
import com.kiduyu.njugunaproject.agrifarm.Model.Specialist;
import com.kiduyu.njugunaproject.agrifarm.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpecialistsFragment extends Fragment {
    private SpecialistAdapter specialistAdapter;
    public static boolean isRefreshed;
    RequestQueue mRequestQueue;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Specialist> specialistArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.specialist_fragment, container, false);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        EditText editText = layout.findViewById(R.id.search_editText_specialist);

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
        recycler = layout.findViewById(R.id.recyclerview_consultant);

        swipeRefreshLayout = layout.findViewById(R.id.consultant_refresh);
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
        ArrayList<Specialist> filteredList = new ArrayList<>();
        for (Specialist item : specialistArrayList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        specialistAdapter = new SpecialistAdapter(getActivity(), filteredList);
        recycler.setAdapter(specialistAdapter);
        specialistAdapter.notifyDataSetChanged();
    }

    private void fetchData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Fetching Specialists");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        String urlForJsonObject = Constants.BASE_API + Constants.SPECIALIST_API;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                urlForJsonObject,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("consultants");

                            if (isRefreshed) {
                                specialistArrayList.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String Name = consultant.getString("name");
                                    String phone = consultant.getString("phone");
                                    String idnumber = consultant.getString("id_number");
                                    String location = consultant.getString("location");
                                    String image = consultant.getString("image");
                                    String date = consultant.getString("date");

                                    Log.d("TAG", "onResponse: " + Name + " " + phone + " " + idnumber + " " + location + " " + image + " " + date);
                                    //Loading.showProgressDialog(getActivity(),false);
                                    specialistArrayList.add(new Specialist("",Name,phone,idnumber,location,image,date));
                                    //tipArrayList.add(new Tip(title, description ,image));

                                }

                            } else {
                                specialistArrayList.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String Name = consultant.getString("name");
                                    String phone = consultant.getString("phone");
                                    String idnumber = consultant.getString("id_number");
                                    String location = consultant.getString("location");
                                    String image = consultant.getString("image");
                                    String date = consultant.getString("date");

                                    Log.d("TAG", "onResponse: " + Name + " " + phone + " " + idnumber + " " + location + " " + image + " " + date);
                                    //Loading.showProgressDialog(getActivity(),false);

                                    //tipArrayList.add(new Tip(title, description ,image));
                                    specialistArrayList.add(new Specialist("",Name,phone,idnumber,location,image,date));

                                }
                            }


                            progressDialog.dismiss();
                            specialistAdapter = new SpecialistAdapter(getActivity(), specialistArrayList);
                            recycler.setAdapter(specialistAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
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
