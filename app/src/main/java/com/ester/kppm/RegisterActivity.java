package com.ester.kppm;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ester.kppm.fragment.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_toolbar)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("Daftar KPPM 2017 Palembang");
        fragmentManager = getSupportFragmentManager();
    }

    @OnClick(R.id.cv_pu) void goToRegistrationFragment(){
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

    @OnClick(R.id.cv_pk) void goToRegistrationFrom(){
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

    @OnClick(R.id.cv_pg) void goToRegistrationF(){
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

}
