package com.kiduyu.njugunaproject.agrifarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kiduyu.njugunaproject.agrifarm.Constants.Constants;
import com.kiduyu.njugunaproject.agrifarm.Session.Prevalent;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BookAppointment extends AppCompatActivity implements View.OnClickListener {
    private String date, time = "", shift;
    private TextView selectDate;
    private Toolbar mToolbar;
    private Button mConfirm;
    private int flagChecked = 0;
    RequestQueue mRequestQueue;
    private LinearLayout morningLayout, eveningLayout;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private CardView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Book Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mRequestQueue = Volley.newRequestQueue(this);
        mConfirm = (Button) findViewById(R.id.confirm_appointment);
        morningLayout = (LinearLayout) findViewById(R.id.morning_shift);
        eveningLayout = (LinearLayout) findViewById(R.id.evening_shift);
        shift = "Morning";

        if (shift.equals("Morning")) {
            morningLayout.setVisibility(View.VISIBLE);
            eveningLayout.setVisibility(View.GONE);
        } else {
            eveningLayout.setVisibility(View.VISIBLE);
            morningLayout.setVisibility(View.GONE);
        }
        selectDate = (TextView) findViewById(R.id.bookAppointment_selectDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(BookAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        // Toast.makeText(Patient_BookAppointmentActivity.this, date , Toast.LENGTH_SHORT).show();
                        selectDate.setText(date);
                        onStart();


                    }
                }, day, month, year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateValue = selectDate.getText().toString().trim();

                if (!dateValue.isEmpty()) {

                    if (flagChecked == 0) {
                        FancyToast.makeText(BookAppointment.this, "Please Select time", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                    } else {
                        setTime(flagChecked);
                        String consultant = getIntent().getStringExtra("consultant").toString();
                        String consultant_phone = getIntent().getStringExtra("consultant").toString();
                        //String username = Prevalent.currentOnlineUser.getName();
                        String username = "klaus";
                        //String userphone = Prevalent.currentOnlineUser.getPhone();
                        String userphone = "0716541458";


                        SendToDb(consultant, username, userphone, consultant_phone, dateValue, time);
                    }
                } else {
                    selectDate.setError("Date Needed!");
                    FancyToast.makeText(BookAppointment.this, "Please Select Date", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }
        });

        c1 = (CardView) findViewById(R.id.time1);
        c2 = (CardView) findViewById(R.id.time2);
        c3 = (CardView) findViewById(R.id.time3);
        c4 = (CardView) findViewById(R.id.time4);
        c5 = (CardView) findViewById(R.id.time5);
        c6 = (CardView) findViewById(R.id.time6);
        c7 = (CardView) findViewById(R.id.time7);
        c8 = (CardView) findViewById(R.id.time8);
        c9 = (CardView) findViewById(R.id.time9);
        c10 = (CardView) findViewById(R.id.time10);
        c11 = (CardView) findViewById(R.id.time11);
        c12 = (CardView) findViewById(R.id.time12);
        c13 = (CardView) findViewById(R.id.time13);
        c14 = (CardView) findViewById(R.id.time14);
        c15 = (CardView) findViewById(R.id.time15);
        c16 = (CardView) findViewById(R.id.time16);
        c17 = (CardView) findViewById(R.id.time17);
        c18 = (CardView) findViewById(R.id.time18);
        c19 = (CardView) findViewById(R.id.time19);
        c20 = (CardView) findViewById(R.id.time20);
        c21 = (CardView) findViewById(R.id.time21);
        c22 = (CardView) findViewById(R.id.time22);
        c23 = (CardView) findViewById(R.id.time23);
        c24 = (CardView) findViewById(R.id.time24);
        c25 = (CardView) findViewById(R.id.time25);
        c26 = (CardView) findViewById(R.id.time26);
        c27 = (CardView) findViewById(R.id.time27);
        c28 = (CardView) findViewById(R.id.time28);
        c29 = (CardView) findViewById(R.id.time29);
        c30 = (CardView) findViewById(R.id.time30);


    }

    private void SendToDb(final String consultant, final String username, final String userphone, final String consultant_phone, final String dateValue, final String time) {
        //FancyToast.makeText(BookAppointment.this, "Done", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.BASE_API+Constants.CREATE_APPOINTMENT_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("TAG", "onResponsejson: " + jsonObject.getString("message"));
                           FancyToast.makeText(getApplicationContext(), jsonObject.getString("message"), FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.hide();
                        Log.d("TAG", "onResponsejsoneror: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), String.valueOf(error.getMessage()), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("sname", consultant);
                params.put("uname", username);
                params.put("uphone", userphone);
                params.put("sphone", consultant_phone);
                params.put("date", dateValue);
                params.put("time", time);
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
        // RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time1:
                //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                checkIsBooked(1);
                break;
            case R.id.time2:
                //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                checkIsBooked(2);
                break;
            case R.id.time3:
                //Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                checkIsBooked(3);
                break;
            case R.id.time4:
                //Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                checkIsBooked(4);
                break;
            case R.id.time5:
                //Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                checkIsBooked(5);
                break;
            case R.id.time6:
                //Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
                checkIsBooked(6);
                break;
            case R.id.time7:
                //Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();
                checkIsBooked(7);
                break;
            case R.id.time8:
                //Toast.makeText(this, "8", Toast.LENGTH_SHORT).show();
                checkIsBooked(8);
                break;
            case R.id.time9:
                //Toast.makeText(this, "9", Toast.LENGTH_SHORT).show();
                checkIsBooked(9);
                break;
            case R.id.time10:
                //Toast.makeText(this, "10", Toast.LENGTH_SHORT).show();
                checkIsBooked(10);
                break;
            case R.id.time11:
                //Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
                checkIsBooked(11);
                break;
            case R.id.time12:
                //Toast.makeText(this, "12", Toast.LENGTH_SHORT).show();
                checkIsBooked(12);
                break;
            case R.id.time13:
                //Toast.makeText(this, "13", Toast.LENGTH_SHORT).show();
                checkIsBooked(13);
                break;
            case R.id.time14:
                //Toast.makeText(this, "14", Toast.LENGTH_SHORT).show();
                checkIsBooked(14);
                break;
            case R.id.time15:
                //Toast.makeText(this, "15", Toast.LENGTH_SHORT).show();
                checkIsBooked(15);
                break;
            case R.id.time16:
                //Toast.makeText(this, "16", Toast.LENGTH_SHORT).show();
                checkIsBooked(16);
                break;
            case R.id.time17:
                //Toast.makeText(this, "17", Toast.LENGTH_SHORT).show();
                checkIsBooked(17);
                break;
            case R.id.time18:
                //Toast.makeText(this, "18", Toast.LENGTH_SHORT).show();
                checkIsBooked(18);
                break;
            case R.id.time19:
                //Toast.makeText(this, "19", Toast.LENGTH_SHORT).show();
                checkIsBooked(19);
                break;
            case R.id.time20:
                //Toast.makeText(this, "20", Toast.LENGTH_SHORT).show();
                checkIsBooked(20);
                break;
            case R.id.time21:
                //Toast.makeText(this, "21", Toast.LENGTH_SHORT).show();
                checkIsBooked(21);
                break;
            case R.id.time22:
                //Toast.makeText(this, "22", Toast.LENGTH_SHORT).show();
                checkIsBooked(22);
                break;
            case R.id.time23:
                //Toast.makeText(this, "23", Toast.LENGTH_SHORT).show();
                checkIsBooked(23);
                break;
            case R.id.time24:
                //Toast.makeText(this, "24", Toast.LENGTH_SHORT).show();
                checkIsBooked(24);
                break;
            case R.id.time25:
                //Toast.makeText(this, "25", Toast.LENGTH_SHORT).show();
                checkIsBooked(25);
                break;
            case R.id.time26:
                //Toast.makeText(this, "26", Toast.LENGTH_SHORT).show();
                checkIsBooked(26);
                break;
            case R.id.time27:
                //Toast.makeText(this, "27", Toast.LENGTH_SHORT).show();
                checkIsBooked(27);
                break;
            case R.id.time28:
                //Toast.makeText(this, "28", Toast.LENGTH_SHORT).show();
                checkIsBooked(28);
                break;
            case R.id.time29:
                //Toast.makeText(this, "29", Toast.LENGTH_SHORT).show();
                checkIsBooked(29);
                break;
            case R.id.time30:
                //Toast.makeText(this, "30", Toast.LENGTH_SHORT).show();
                checkIsBooked(30);
                break;

            default:
                break;
        }
    }

    private void checkIsBooked(int i) {


        if (flagChecked != 0) {
            setDefaultColor(flagChecked);
            flagChecked = i;
            setColorGreen(i);
        } else {
            flagChecked = i;
            setColorGreen(i);
        }


    }

    private void setColorRed(int i) {

        switch (i) {
            case 1:
                c1.setCardBackgroundColor(Color.RED);
                c1.setEnabled(false);
                break;
            case 2:
                c2.setCardBackgroundColor(Color.RED);
                c2.setEnabled(false);
                break;
            case 3:
                c3.setCardBackgroundColor(Color.RED);
                c3.setEnabled(false);
                break;
            case 4:
                c4.setCardBackgroundColor(Color.RED);
                c4.setEnabled(false);
                break;
            case 5:
                c5.setCardBackgroundColor(Color.RED);
                c5.setEnabled(false);
                break;
            case 6:
                c6.setCardBackgroundColor(Color.RED);
                c6.setEnabled(false);
                break;
            case 7:
                c7.setCardBackgroundColor(Color.RED);
                c7.setEnabled(false);
                break;
            case 8:
                c8.setCardBackgroundColor(Color.RED);
                c8.setEnabled(false);
                break;
            case 9:
                c9.setCardBackgroundColor(Color.RED);
                c9.setEnabled(false);
                break;
            case 10:
                c10.setCardBackgroundColor(Color.RED);
                c10.setEnabled(false);
                break;
            case 11:
                c11.setCardBackgroundColor(Color.RED);
                c11.setEnabled(false);
                break;
            case 12:
                c12.setCardBackgroundColor(Color.RED);
                c12.setEnabled(false);
                break;
            case 13:
                c13.setCardBackgroundColor(Color.RED);
                c13.setEnabled(false);
                break;
            case 14:
                c14.setCardBackgroundColor(Color.RED);
                c14.setEnabled(false);
                break;
            case 15:
                c15.setCardBackgroundColor(Color.RED);
                c15.setEnabled(false);
                break;
            case 16:
                c16.setCardBackgroundColor(Color.RED);
                c16.setEnabled(false);
                break;
            case 17:
                c17.setCardBackgroundColor(Color.RED);
                c17.setEnabled(false);
                break;
            case 18:
                c18.setCardBackgroundColor(Color.RED);
                c18.setEnabled(false);
                break;
            case 19:
                c19.setCardBackgroundColor(Color.RED);
                c19.setEnabled(false);
                break;
            case 20:
                c20.setCardBackgroundColor(Color.RED);
                c20.setEnabled(false);
                break;
            case 21:
                c21.setCardBackgroundColor(Color.RED);
                c21.setEnabled(false);
                break;
            case 22:
                c22.setCardBackgroundColor(Color.RED);
                c22.setEnabled(false);
                break;
            case 23:
                c23.setCardBackgroundColor(Color.RED);
                c23.setEnabled(false);
                break;
            case 24:
                c24.setCardBackgroundColor(Color.RED);
                c24.setEnabled(false);
                break;
            case 25:
                c25.setCardBackgroundColor(Color.RED);
                c25.setEnabled(false);
                break;
            case 26:
                c26.setCardBackgroundColor(Color.RED);
                c26.setEnabled(false);
                break;
            case 27:
                c27.setCardBackgroundColor(Color.RED);
                c27.setEnabled(false);
                break;
            case 28:
                c28.setCardBackgroundColor(Color.RED);
                c28.setEnabled(false);
                break;
            case 29:
                c29.setCardBackgroundColor(Color.RED);
                c29.setEnabled(false);
                break;
            case 30:
                c30.setCardBackgroundColor(Color.RED);
                c30.setEnabled(false);
                break;
            default:
                break;
        }
    }

    private void setColorGreen(int i) {

        switch (i) {
            case 1:
                c1.setCardBackgroundColor(Color.GREEN);
                break;
            case 2:
                c2.setCardBackgroundColor(Color.GREEN);
                break;
            case 3:
                c3.setCardBackgroundColor(Color.GREEN);
                break;
            case 4:
                c4.setCardBackgroundColor(Color.GREEN);
                break;
            case 5:
                c5.setCardBackgroundColor(Color.GREEN);
                break;
            case 6:
                c6.setCardBackgroundColor(Color.GREEN);
                break;
            case 7:
                c7.setCardBackgroundColor(Color.GREEN);
                break;
            case 8:
                c8.setCardBackgroundColor(Color.GREEN);
                break;
            case 9:
                c9.setCardBackgroundColor(Color.GREEN);
                break;
            case 10:
                c10.setCardBackgroundColor(Color.GREEN);
                break;
            case 11:
                c11.setCardBackgroundColor(Color.GREEN);
                break;
            case 12:
                c12.setCardBackgroundColor(Color.GREEN);
                break;
            case 13:
                c13.setCardBackgroundColor(Color.GREEN);
                break;
            case 14:
                c14.setCardBackgroundColor(Color.GREEN);
                break;
            case 15:
                c15.setCardBackgroundColor(Color.GREEN);
                break;
            case 16:
                c16.setCardBackgroundColor(Color.GREEN);
                break;
            case 17:
                c17.setCardBackgroundColor(Color.GREEN);
                break;
            case 18:
                c18.setCardBackgroundColor(Color.GREEN);
                break;
            case 19:
                c19.setCardBackgroundColor(Color.GREEN);
                break;
            case 20:
                c20.setCardBackgroundColor(Color.GREEN);
                break;
            case 21:
                c21.setCardBackgroundColor(Color.GREEN);
                break;
            case 22:
                c22.setCardBackgroundColor(Color.GREEN);
                break;
            case 23:
                c23.setCardBackgroundColor(Color.GREEN);
                break;
            case 24:
                c24.setCardBackgroundColor(Color.GREEN);
                break;
            case 25:
                c25.setCardBackgroundColor(Color.GREEN);
                break;
            case 26:
                c26.setCardBackgroundColor(Color.GREEN);
                break;
            case 27:
                c27.setCardBackgroundColor(Color.GREEN);
                break;
            case 28:
                c28.setCardBackgroundColor(Color.GREEN);
                break;
            case 29:
                c29.setCardBackgroundColor(Color.GREEN);
                break;
            case 30:
                c30.setCardBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    private void setDefaultColor(int i) {

        switch (i) {
            case 1:
                c1.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c1.setEnabled(true);
                break;
            case 2:
                c2.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c2.setEnabled(true);
                break;
            case 3:
                c3.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c3.setEnabled(true);
                break;
            case 4:
                c4.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c4.setEnabled(true);
                break;
            case 5:
                c5.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c5.setEnabled(true);
                break;
            case 6:
                c6.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c6.setEnabled(true);
                break;
            case 7:
                c7.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c7.setEnabled(true);
                break;
            case 8:
                c8.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c8.setEnabled(true);
                break;
            case 9:
                c9.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c9.setEnabled(true);
                break;
            case 10:
                c10.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c10.setEnabled(true);
                break;
            case 11:
                c11.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c11.setEnabled(true);
                break;
            case 12:
                c12.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c12.setEnabled(true);
                break;
            case 13:
                c13.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c13.setEnabled(true);
                break;
            case 14:
                c14.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c14.setEnabled(true);
                break;
            case 15:
                c15.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c15.setEnabled(true);
                break;
            case 16:
                c16.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c16.setEnabled(true);
                break;
            case 17:
                c17.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c17.setEnabled(true);
                break;
            case 18:
                c18.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c18.setEnabled(true);
                break;
            case 19:
                c19.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c19.setEnabled(true);
                break;
            case 20:
                c20.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c20.setEnabled(true);
                break;
            case 21:
                c21.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c21.setEnabled(true);
                break;
            case 22:
                c22.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c22.setEnabled(true);
                break;
            case 23:
                c23.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c23.setEnabled(true);
                break;
            case 24:
                c24.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c24.setEnabled(true);
                break;
            case 25:
                c25.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c25.setEnabled(true);
                break;
            case 26:
                c26.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c26.setEnabled(true);
                break;
            case 27:
                c27.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c27.setEnabled(true);
                break;
            case 28:
                c28.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c28.setEnabled(true);
                break;
            case 29:
                c29.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c29.setEnabled(true);
                break;
            case 30:
                c30.setCardBackgroundColor(getResources().getColor(R.color.skyBlue));
                c30.setEnabled(true);
                break;
            default:
                break;
        }
    }

    private void setTime(int i) {

        switch (i) {
            case 1:
                time = "08:00 AM";
                break;
            case 2:
                time = "08:20 AM";
                break;
            case 3:
                time = "08:40 AM";
                break;
            case 4:
                time = "09:00 AM";
                break;
            case 5:
                time = "09:20 AM";
                break;
            case 6:
                time = "09:40 AM";
                break;
            case 7:
                time = "10:00 AM";
                break;
            case 8:
                time = "10:20 AM";
                break;
            case 9:
                time = "10:40 AM";
                break;
            case 10:
                time = "11:00 AM";
                break;
            case 11:
                time = "11:20 AM";
                break;
            case 12:
                time = "11:40 AM";
                break;
            case 13:
                time = "02:00 PM";
                break;
            case 14:
                time = "02:20 PM";
                break;
            case 15:
                time = "02:40 PM";
                break;
            case 16:
                time = "03:00 PM";
                break;
            case 17:
                time = "03:20 PM";
                break;
            case 18:
                time = "03:40 PM";
                break;
            case 19:
                time = "04:00 PM";
                break;
            case 20:
                time = "04:20 PM";
                break;
            case 21:
                time = "04:40 PM";
                break;
            case 22:
                time = "05:00 PM";
                break;
            case 23:
                time = "05:20 PM";
                break;
            case 24:
                time = "05:40 PM";
                break;
            case 25:
                time = "06:00 PM";
                break;
            case 26:
                time = "06:20 PM";
                break;
            case 27:
                time = "06:40 PM";
                break;
            case 28:
                time = "09:00 PM";
                break;
            case 29:
                time = "09:20 PM";
                break;
            case 30:
                time = "09:40 PM";
                break;
            default:
                break;
        }
    }
}