package com.yan.phoneintercept.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yan.phoneintercept.view.entity.CheckedActionPrevider;
import com.yan.phoneintercept.R;
import com.yan.phoneintercept.view.fragment.InputNumber;
import com.yan.phoneintercept.view.fragment.NumberFromRecored;


public class ActivityNext extends AppCompatActivity implements View.OnClickListener {

    public static final int RESOULT_CODE = 100;//Activity返回码

    private Button btnAdd;
    private Button btnCancel;
    private int type;//从主activity传过来的操作类型
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        btnAdd = (Button) findViewById(R.id.btn_number_add);
        btnCancel = (Button) findViewById(R.id.btn_number_cancel);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 4);
        if (type == 3) {
            currentFragment = new InputNumber();
        } else if (type == 2) {
            currentFragment = NumberFromRecored.instance(2);
        } else if (type == 1) {
            currentFragment = NumberFromRecored.instance(1);
        }
        getSupportActionBar().setTitle("添加到黑名单");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().add(R.id.recored_fagment, currentFragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (type) {

            case 3:
                if (v.getId() == R.id.btn_number_add) {
                    if (currentFragment instanceof InputNumber) {
                        if (((InputNumber) currentFragment).addPhoneNumber()) {
                            setResult(RESOULT_CODE, new Intent());
                            this.finish();
                        }
                    }
                }
                break;
            default:
                if (v.getId() == R.id.btn_number_add) {
                    if (currentFragment instanceof NumberFromRecored) {
                        if (((NumberFromRecored) currentFragment).addNumberIntercept()) {
                            setResult(RESOULT_CODE, new Intent());
                            this.finish();
                        }
                    }
                }
                break;
        }
        if (v.getId() == R.id.btn_number_cancel) {
            setResult(RESOULT_CODE, new Intent());
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (type != 3) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.checked_box, menu);
            MenuItem item = menu.findItem(R.id.menu_checked_box);
            CheckedActionPrevider checkedActionPrevider = (CheckedActionPrevider) MenuItemCompat.getActionProvider(item);
            checkedActionPrevider.setOnCheckBoxChecked(new CheckedActionPrevider.OnCheckBoxChecked() {
                @Override
                public void onChecked(boolean ischeked) {
                    ((NumberFromRecored) currentFragment).setChecked(ischeked);
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
