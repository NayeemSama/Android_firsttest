package com.xpressy.firsttest.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.xpressy.firsttest.Database.Tbl_User;
import com.xpressy.firsttest.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class FormActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String[] country = { "Select","India", "USA", "China", "Japan", "Other"};
    RatingBar ratingBar;
    EditText date, time, name, age, email, phone, password;
    Calendar c = Calendar.getInstance();
    RadioButton male,female;
    RadioGroup group;
    Button buttonRGSTR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setupActionBar("Form", true);


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        male = findViewById(R.id.rbtnMale);
        female = findViewById(R.id.rbtnFemale);
        group = findViewById(R.id.rdGrp);
        buttonRGSTR = findViewById(R.id.btnFORM);
        buttonRGSTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().clearFocus();
                Log.d("ID:2131231231 MALE --- 2131231230", String.valueOf(group.getCheckedRadioButtonId()));
                if (isValid()){
                    long id = new Tbl_User(FormActivity.this).setUser(name.getText().toString(), password.getText().toString(),
                                                                            email.getText().toString(), Long.parseLong(phone.getText().toString()),
                                                                            Integer.parseInt(age.getText().toString()), spinner.getSelectedItem().toString(),
                                                                            date.getText().toString(), time.getText().toString(),
                                                                            ratingBar.getRating(), group.getCheckedRadioButtonId());
                    Toast.makeText(FormActivity.this, "Register Successfully--> " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


        ratingBar = findViewById(R.id.rateBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(FormActivity.this, String.valueOf(v), Toast.LENGTH_SHORT).show();
            }
        });



        spinner = findViewById(R.id.spnr);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        date = findViewById(R.id.Date);
        DatePickerDialog.OnDateSetListener currentDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                upDateEdittext();
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormActivity.this,currentDate,Calendar.getInstance().get(Calendar.YEAR)-18,Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time = findViewById(R.id.Time);
        TimePickerDialog.OnTimeSetListener currentTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Log.d("Time", String.valueOf(i)+" : "+String.valueOf(i1));
                updateTimetext(i, i1);
            }
        };
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(FormActivity.this, currentTime, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false).show();
            }
        });
    }

    private void updateTimetext(int hour, int min) {
        time.setText(hour + ":"+ min);
    }

    private void upDateEdittext() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(c.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean isValid() {
        boolean isValid = true;

        if (TextUtils.isEmpty(name.getText().toString())){
            isValid = false;
            name.setError("Enter Name First!!");
        }
        else if (!name.getText().toString().matches("[A-Z]{1}[a-z]{1,}")){
            isValid = false;
            name.setError("Username first letter capital!!");
        }

        if (TextUtils.isEmpty(email.getText().toString())){
            isValid = false;
            email.setError("Enter Email First!!");
        }
        else if (!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            isValid = false;
            email.setError("Enter valid Email!!");
        }

        if (TextUtils.isEmpty(phone.getText().toString())){
            isValid = false;
            phone.setError("Enter Phone No. First!!");
        }
        else if (phone.getText().toString().length() < 10){
            isValid = false;
            phone.setError("Enter valid number!!");
        }

        if (TextUtils.isEmpty(age.getText().toString())){
            isValid = false;
            age.setError("Enter Age First!!");
        }
        else if (Integer.parseInt(age.getText().toString()) < 18){
            isValid = false;
            age.setError("Under 18 not allowed!!");
        }

        if (group.getCheckedRadioButtonId()==-1){
            isValid = false;
            Toast.makeText(FormActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password.getText().toString())){
            isValid = false;
            password.setError("Enter Password First!!");
        }
        else if (password.getText().toString().length() < 6){
            isValid = false;
            password.setError("Enter 6 digit number!!");
        }

        if (ratingBar.getRating()<1){
            isValid = false;
            Toast.makeText(FormActivity.this, "Rate yourself atleast 1 star!", Toast.LENGTH_SHORT).show();
        }

        if (spinner.getSelectedItem().toString() == "Select"){
            isValid = false;
            TextView errorText = (TextView)spinner.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Select atleast one country");
        }

        String[] split = date.getText().toString().split("/");
        date.setError(null);
        if (TextUtils.isEmpty(date.getText().toString())){
            isValid = false;
            date.setError("Enter your birthdate");
        }
        else if (Integer.parseInt(split[2]) > Calendar.getInstance().get(Calendar.YEAR)-2018){
            isValid = false;
            date.setError("Under 18 not allowed");
        }

        time.setError(null);
        if (TextUtils.isEmpty(time.getText().toString())){
            isValid = false;
            time.setError("Enter time");
        }


        return isValid;
    }
}
