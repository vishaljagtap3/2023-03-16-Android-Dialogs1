package in.bitcode.dialogs1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnAlertDialog, btnDatePickerDialog, btnTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void setupListeners() {
        btnDatePickerDialog.setOnClickListener(new BtnDatePickerDialogClickListener());
        btnTimePickerDialog.setOnClickListener(new BtnTimePickerDialogListener());
        btnAlertDialog.setOnClickListener(new BtnAlertDialogClickListener());
    }

    private void initViews() {
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
    }


    private class BtnAlertDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("BitCode");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Do you want to study?");

            DialogInterface.OnClickListener onClickListener =
                    new AlertDialogButtonsListener();

            builder.setPositiveButton("Yes", onClickListener);
            builder.setNegativeButton("Off course Not", onClickListener);
            builder.setNeutralButton("Don't know", onClickListener);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private class AlertDialogButtonsListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           switch (which) {
               case DialogInterface.BUTTON_POSITIVE:
                   mt("Yes");
                   break;
               case DialogInterface.BUTTON_NEGATIVE:
                   mt("No");
                   break;
               case DialogInterface.BUTTON_NEUTRAL:
                   mt("Confused");
                   break;
           }
        }
    }


    private class BtnTimePickerDialogListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(
                            MainActivity.this,
                            new MyOnTimeSetListener(),
                            18,
                            55,
                            false
                    );
            timePickerDialog.show();
        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
            btnTimePickerDialog.setText(hours + " : " + minutes);
        }
    }

    private class BtnDatePickerDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                         MainActivity.this,
                         new MyOnDateSetListener(),
                         2023,
                         2,
                         25
                    );
            //datePickerDialog.setCancelable(false);
            datePickerDialog.setOnCancelListener(
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mt("DatePicker cancelled");
                        }
                    }
            );
            datePickerDialog.setOnDismissListener(
                    new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            mt("DatePicker dismissed");
                        }
                    }
            );

            datePickerDialog.show();
        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            btnDatePickerDialog.setText(dayOfMonth + "/" + (month+1) + "/" + year);
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}