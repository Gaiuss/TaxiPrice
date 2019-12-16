package com.gaius.taxi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText etTime;
    private AppCompatEditText etLength;
    private TextView tvBusiness;
    private TextView tvComfort;
    private TextView tvAudi;
    private Button btnCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTime = findViewById(R.id.et_time_main);
        etLength = findViewById(R.id.et_length_main);
        tvBusiness = findViewById(R.id.tv_show_business);
        tvComfort = findViewById(R.id.tv_show_comfort);
        tvAudi = findViewById(R.id.tv_show_audi);
        btnCount = findViewById(R.id.btn_count);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(imm).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                String time = String.valueOf(etTime.getText());
                String length = String.valueOf(etLength.getText());
                if (!time.isEmpty() && !length.isEmpty()) {
                    countResult(time, length);
                } else {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void countResult(String time, String length) {
        int ti = Integer.parseInt(time);
        int len = Integer.parseInt(length);

        if (len <= 2) {
            tvBusiness.setText("14元");

            if ((0 <= ti && ti <= 10) || (17 <= ti && ti <= 20) || (23 <= ti)) {
                tvComfort.setText("14.6元");
            } else {
                tvComfort.setText("13.6元");
            }

            tvAudi.setText("30元");
        } else {
            int tim = ti - 2;

            if ((0 <= ti && ti <= 6) || (23 <= ti)) {
                int c = (int) (Math.ceil(tim * 5.7) + 14);
                tvBusiness.setText(String.valueOf(c) + "元");
            } else {
                int c = (int) (Math.ceil(tim * 3.8) + 14);
                tvBusiness.setText(String.valueOf(c) + "元");
            }

            if ((0 <= ti && ti <= 6) || (23 <= ti)) {
                int c = (int) (Math.ceil(tim * 4.3 + 14.6));
                tvComfort.setText(String.valueOf(c) + "元");
            } else if ((7 <= ti && ti <= 10) || (20 <= ti && ti <= 22)) {
                int c = (int) (Math.ceil(tim * 3.1 + 14.6));
                tvComfort.setText(String.valueOf(c) + "元");
            } else {
                int c = (int) (Math.ceil(tim * 2.7 + 13.6));
                tvComfort.setText(String.valueOf(c) + "元");
            }

            if ((0 <= ti && ti <= 6) || (23 <= ti)) {
                int c = (int) (Math.ceil(tim * 8 + 30));
                tvComfort.setText(String.valueOf(c) + "元");
            } else {
                int c = (int) (Math.ceil(tim * 5 + 30));
                tvComfort.setText(String.valueOf(c) + "元");
            }
        }
    }

}
