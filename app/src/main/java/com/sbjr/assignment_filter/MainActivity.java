package com.sbjr.assignment_filter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button openDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews() {
        openDialogButton = findViewById(R.id.openDialogButton);
    }

   private void initListeners() {
        openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dailog_);

        SeekBar priceSeekBar = dialog.findViewById(R.id.SeekBar);
        TextView priceRangeText = dialog.findViewById(R.id.txttitle);
        CheckBox oneBhk = dialog.findViewById(R.id.OneBhk);
        CheckBox twoBhk = dialog.findViewById(R.id.twoBhk);
        CheckBox threeBhk = dialog.findViewById(R.id.threeBhk);
        Button buttonOk = dialog.findViewById(R.id.button_ok);

        priceSeekBar.setMax(0);
        priceSeekBar.setProgress(0);
        priceRangeText.setText("0");

        initCheckboxLogic(oneBhk, twoBhk, threeBhk);

        initDialogListeners(dialog, priceSeekBar, priceRangeText, oneBhk, twoBhk, threeBhk, buttonOk);

        dialog.show();
    }
    private void initCheckboxLogic(CheckBox oneBhk, CheckBox twoBhk, CheckBox threBhk) {
        oneBhk.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                twoBhk.setChecked(false);
                threBhk.setChecked(false);
            }
        });

        twoBhk.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                oneBhk.setChecked(false);
                threBhk.setChecked(false);
            }
        });

        threBhk.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                oneBhk.setChecked(false);
                twoBhk.setChecked(false);
            }
        });
    }

    private void initDialogListeners(Dialog dialog, SeekBar priceSeekBar, TextView priceRangeText, CheckBox oneBhk, CheckBox twoBhk, CheckBox threeBhk, Button buttonOk) {
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneBhk.isChecked()) {
                    // to 25 for 1 BHK
                    priceSeekBar.setProgress(25);
                    priceRangeText.setText("1BHK:20 Lacks");
                } else if (twoBhk.isChecked()) {
                    //  50 for 2 BHK
                    priceSeekBar.setProgress(50);
                    priceRangeText.setText("2BHK : 45 Lakh");
                } else if (threeBhk.isChecked()) {
                    // 100 for 3 BHK
                    priceSeekBar.setProgress(100);
                    priceRangeText.setText("3BHK: 1.5 Cr");
                }
            }
        });
    }
}