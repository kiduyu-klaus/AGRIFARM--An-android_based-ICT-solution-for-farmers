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
import com.kiduyu.njugunaproject.agrifarm.Adapter.NewsAdapter;
import com.kiduyu.njugunaproject.agrifarm.Constants.Constants;
import com.kiduyu.njugunaproject.agrifarm.Model.Application;
import com.kiduyu.njugunaproject.agrifarm.Model.News;
import com.kiduyu.njugunaproject.agrifarm.Model.Specialist;
import com.kiduyu.njugunaproject.agrifarm.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    public static boolean isRefreshed;
    RequestQueue mRequestQueue;
    NewsAdapter newsAdapter;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<News> newsArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.news_fragment, container, false);
        recycler = layout.findViewById(R.id.recyclerview_tips);

        swipeRefreshLayout = layout.findViewById(R.id.tip_refresh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        EditText editText = layout.findViewById(R.id.search_editText_news);

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
        recycler.setLayoutManager(layoutManager);
        recycler.setFocusable(false);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                fetchData();
                swipeRefreshLayout.setRefreshing(false);
                FancyToast.makeText(getActivity(), "Data refreshed!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
            }
        });

        fetchData();

        return layout;
    }

    private void filter(String text) {

        ArrayList<News> filteredList = new ArrayList<>();
        for (News item : newsArrayList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        newsAdapter = new NewsAdapter(getActivity(), filteredList);
        recycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
    }

    private void fetchData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Fetching News");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        String urlForJsonObject = Constants.BASE_API + Constants.NEWS_API;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                urlForJsonObject,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("News");

                            if (isRefreshed) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String title = consultant.getString("title");
                                    String description = consultant.getString("description");
                                    String image = consultant.getString("image");
                                    String date = consultant.getString("date");

                                    Log.d("TAG", "onResponse: " + title + " " + description + " " + image + " " + date);
                                    //Loading.showProgressDialog(getActivity(),false);

                                    newsArrayList.add(new News("",title,description,image,date));


                                }

                            } else {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject consultant = jsonArray.getJSONObject(i);
                                    String title = consultant.getString("title");
                                    String description = consultant.getString("description");
                                    String image = consultant.getString("image");
                                    String date = consultant.getString("date");

                                    Log.d("TAG", "onResponse: " + title + " " + description + " " + image + " " + date);
                                    //Loading.showProgressDialog(getActivity(),false);

                                    newsArrayList.add(new News("",title,description,image,date));

                                }
                            }


                            progressDialog.dismiss();
                            newsAdapter = new NewsAdapter(getActivity(), newsArrayList);
                            recycler.setAdapter(newsAdapter);

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
