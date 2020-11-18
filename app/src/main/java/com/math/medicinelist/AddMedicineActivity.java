package com.math.medicinelist;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText startDateEt;
    private EditText endDateEt;
    private DatePickerDialog startDateDial;
    private DatePickerDialog endDateDial;
    private SimpleDateFormat dateFormatter;
    private int latestDoseEtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        latestDoseEtId = R.id.addDose;
    }

    private void findViewsById() {
        startDateEt = (EditText) findViewById(R.id.startDate);
        startDateEt.setInputType(InputType.TYPE_NULL);
        startDateEt.requestFocus();

        endDateEt = (EditText) findViewById(R.id.endDate);
        endDateEt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        startDateEt.setOnClickListener(this);
        endDateEt.setOnClickListener(this);

        Calendar cal = Calendar.getInstance();
        startDateDial = new DatePickerDialog(this, new OnDateSetListener() {//TODO replace with lambda
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                startDateEt.setText(dateFormatter.format(newDate.getTime()));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        endDateDial = new DatePickerDialog(this, new OnDateSetListener() {//TODO replace with lambda
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                endDateEt.setText(dateFormatter.format(newDate.getTime()));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == startDateEt) {
            startDateDial.show();
        } else if (view == endDateEt) {
            endDateDial.show();
        }
    }

    public void addDose(View view) {
        ConstraintLayout cLo = (ConstraintLayout) findViewById(R.id.scrollAddMedicineLo);
        EditText et = createEditText(R.string.dose);
        addViewBelow((ConstraintLayout) findViewById(R.id.scrollAddMedicineLo), new ConstraintSet(), et, latestDoseEtId);
        Button button = createButton();
        addViewToRight((ConstraintLayout) findViewById(R.id.scrollAddMedicineLo), new ConstraintSet(), button, et.getId());

        latestDoseEtId = et.getId();
        et.requestFocus();
    }

    private void addViewBelow(ConstraintLayout constLayout, ConstraintSet constSet, TextView view, int refView) {
        constLayout.addView(view, createConstLayoutParams((ConstraintLayout.LayoutParams) findViewById(R.id.addDose).getLayoutParams()));
        constSet.clone(constLayout);
        constSet.connect(view.getId(),
                ConstraintSet.TOP,
                refView,
                ConstraintSet.BOTTOM, 8);
        constSet.applyTo(constLayout);
    }

    private void addViewToRight(ConstraintLayout constLayout, ConstraintSet constSet, TextView view, int refView) {
        //TODO: Fix so placement is beside latest added view
        constLayout.addView(view, createConstLayoutParams((ConstraintLayout.LayoutParams) findViewById(R.id.addDose).getLayoutParams()));
        constSet.clone(constLayout);
        constSet.connect(view.getId(),
                ConstraintSet.LEFT,
                refView,
                ConstraintSet.RIGHT, 8);
        constSet.applyTo(constLayout);
    }

    public void cancel(View view) {
        super.finish();
    }

    private EditText createEditText(int hint) {
        EditText et = new EditText(this);
        et.setHint(R.string.dose);
        et.setId(View.generateViewId());
        return et;
    }

    private Button createButton() {
        Button b = new Button(this);
        b.setId(View.generateViewId());
        return b;
    }

    private ConstraintLayout.LayoutParams createConstLayoutParams(ConstraintLayout.LayoutParams params) {
        ConstraintLayout.LayoutParams lop = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        lop.leftMargin = params.leftMargin;
        lop.rightMargin = params.rightMargin;
        lop.topMargin = params.topMargin;
        lop.height = params.height;
        lop.width = params.width;
        return lop;
    }

}