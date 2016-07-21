package com.yan.phoneintercept.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.view.fragment.BlackMenu;
import com.yan.phoneintercept.view.fragment.Intercept;
import com.yan.phoneintercept.view.fragment.OnButtonClickListener;

public class MainActivity extends AppCompatActivity implements OnButtonClickListener {

    private Intercept intercept;
    private BlackMenu blackMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intercept = new Intercept();
        blackMenu = new BlackMenu();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, intercept, "Intercept");
        fragmentTransaction.commit();
    }

    @Override
    public void OnClick(int buttonId) {

        switch (buttonId) {
            case R.id.btn_intercept:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, intercept, "Intercept")
                        .commit();
                break;
            case R.id.btn_blalknume:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, blackMenu, "BlackMenu")
                        .commit();
                break;
            case R.id.btn_add_from_number:
                Intent intent1 = new Intent(this, ActivityNext.class);
                intent1.putExtra("TYPE", 3);
                startActivityForResult(intent1, ActivityNext.RESOULT_CODE);
                break;
            case R.id.btn_add_from_call:
                Intent intent2 = new Intent(this, ActivityNext.class);
                intent2.putExtra("TYPE", 2);
                startActivityForResult(intent2, ActivityNext.RESOULT_CODE);
                break;
            case R.id.btn_add_from_call_recoed:
                Intent intent3 = new Intent(this, ActivityNext.class);
                intent3.putExtra("TYPE", 1);
                startActivityForResult(intent3, ActivityNext.RESOULT_CODE);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(Intent.ACTION_MAIN);
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果是服务里调用，必须加入new task标识
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityNext.RESOULT_CODE) {
            blackMenu.readyData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
