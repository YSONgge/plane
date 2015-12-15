package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.yeye.plane.R;
import com.example.yeye.plane.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class PassengerActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar bar;
    private Button submit;
    private EditText name, cardNumber;
    private RadioGroup radioGroup;
    private CheckBox delay,safe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        submit = (Button) findViewById(R.id.btn_submit);
        name = (EditText) findViewById(R.id.edit_name);
        cardNumber = (EditText) findViewById(R.id.edit_id_card);
        radioGroup = (RadioGroup) findViewById(R.id.radio_level);
        delay = (CheckBox) findViewById(R.id.yanWu);
        safe = (CheckBox) findViewById(R.id.renShen);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()) {
                    Intent intent = getIntent();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", name.getText().toString());
                        jsonObject.put("idCard", cardNumber.getText().toString());
                        int level = 0;
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.jingjicang:
                                level = 1;
                                break;
                            case R.id.gongwucang:
                                level = 2;
                                break;
                            case R.id.toudengcang:
                                level = 3;
                                break;
                        }
                        jsonObject.put("level", level);
                        jsonObject.put("delay", delay.isChecked());
                        jsonObject.put("safe", safe.isChecked());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        LogUtil.e("PassengerActivity", e.getMessage());
                    }
                    intent.putExtra("passenger", jsonObject.toString());
                    PassengerActivity.this.setResult(0, intent);
                    PassengerActivity.this.finish();
                }
            }
        });

        // TODO: 2015/12/11 机票类型未完成

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("乘客信息");
    }

    private boolean check() {
        return false;
    }

    /*
   点击按钮结束当前界面
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }
}
