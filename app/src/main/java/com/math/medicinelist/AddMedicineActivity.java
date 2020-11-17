package com.math.medicinelist;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

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
        latestDoseEtId = R.id.dose;
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
        ConstraintSet cS = new ConstraintSet();
        EditText et = new EditText(this);
        ConstraintLayout.LayoutParams doseLop = (ConstraintLayout.LayoutParams) findViewById(R.id.dose).getLayoutParams();
        ConstraintLayout.LayoutParams lop = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        lop.leftMargin = doseLop.leftMargin;
        lop.rightMargin = doseLop.rightMargin;
        lop.topMargin = doseLop.topMargin;
        lop.height = doseLop.height;
        lop.width = doseLop.width;

        et.setHint(R.string.dose);
        et.setId(latestDoseEtId+1000);

        //et.setId(View.generateViewId()); Requires API level 17
        cLo.addView(et, lop);
        cS.clone(cLo);
        cS.connect(et.getId(),
                ConstraintSet.TOP,
                latestDoseEtId,
                ConstraintSet.BOTTOM, 8);
        cS.applyTo(cLo);
        latestDoseEtId = et.getId();
        /* android:id="@+id/dose"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="16dp"
        android:layout_marginRight="16dp"
        android:autofillHints="@string/dose"
        android:ems="10"
        android:hint="@string/dose"
        android:inputType="textPersonName"
        app:layout_constraintEnd_toStartOf="@+id/addDose"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/medicineStrength" */
    }

    public void cancel(View view) {
        super.finish();
    }
}