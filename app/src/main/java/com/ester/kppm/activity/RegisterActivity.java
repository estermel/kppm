package com.ester.kppm.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ester.kppm.R;
import com.ester.kppm.RestApi;
import com.ester.kppm.fragment.RegisterFragment;
import com.ester.kppm.model.PesertaModel;
import com.ester.kppm.model.TipeKamarResponse;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// LAST DELIVERED SUNDAY, 4.20 PM

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_toolbar)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FragmentManager fragmentManager;
    String role = "";
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        dialog = new ProgressDialog(this);
        toolbarTitle.setText("Daftar KPPM 2017 Palembang");
        fragmentManager = getSupportFragmentManager();
    }

    @OnClick(R.id.cv_pu) void goToRegistrationPuForm(){
        role = "umum";
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

    @OnClick(R.id.cv_pk) void goToRegistrationPkForm(){
        role = "khusus";
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, new RegisterFragment())
                .addToBackStack(null).commit();
    }

//    NEXT DELIVERABLE: DOWNLOAD REPORT CSV
//    @OnClick(R.id.cv_download) void downloadReportCSV(){
//        dialog.setMessage("Mengambil data...");
//        dialog.setCancelable(false);
//        dialog.show();
//        RestApi restApi = RestApi.retrofit.create(RestApi.class);
//        Call<PesertaModel> call = restApi.exportCsv();
//        call.enqueue(new Callback<PesertaModel>() {
//            String message;
//            @Override
//            public void onResponse(Call<PesertaModel> call, Response<PesertaModel> response) {
//                message = response.message();
//                dialog.dismiss();
//                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<PesertaModel> call, Throwable t) {
//                Log.e("onfailure", t.getLocalizedMessage());
//                message = t.getMessage();
//                dialog.dismiss();
//                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public String prepareRole() {
        return role;
    }

}
