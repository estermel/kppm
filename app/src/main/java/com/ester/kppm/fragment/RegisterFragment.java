package com.ester.kppm.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.ester.kppm.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.R.id.parent;

/**
 * Created by mel on 7/8/17.
 */

public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    View view;
    FragmentManager fragmentManager;
    @BindView(R.id.tv_full_name)
    TextView tv_full_name;
    @BindView(R.id.et_fullname)
    EditText et_fullname;
    @BindView(R.id.tv_org_gereja)
    TextView tv_org_gereja;
    @BindView(R.id.et_org_gereja)
    EditText et_org_gereja;
    @BindView(R.id.spinner_jabatan)
    MaterialBetterSpinner spinner_jabatan;
    @BindView(R.id.tv_umur)
    TextView tv_umur;
    @BindView(R.id.et_umur)
    EditText et_umur;
    @BindView(R.id.tv_no_ktp)
    TextView tv_no_ktp;
    @BindView(R.id.et_no_ktp)
    EditText et_no_ktp;
    @BindView(R.id.tv_bersama_istri)
    TextView tv_bersama_istri;
    @BindView(R.id.cb_bersama_istri)
    CheckBox cb_bersama_istri;
    @BindView(R.id.cb_tidak_bersama_istri)
    CheckBox cb_tidak_bersama_istri;
    @BindView(R.id.tv_alamat)
    TextView tv_alamat;
    @BindView(R.id.et_alamat)
    EditText et_alamat;
    @BindView(R.id.et_provinsi)
    EditText et_provinsi;
    @BindView(R.id.et_kabupaten_kota)
    EditText et_kabupaten_kota;
    @BindView(R.id.tv_no_telepon)
    TextView tv_no_telepon;
    @BindView(R.id.et_no_telepon)
    EditText et_no_telepon;
    @BindView(R.id.tv_tgl_keberangkatan)
    TextView tv_tgl_keberangkatan;
    @BindView(R.id.v_tgl_keberangkatan)
    TextView v_tgl_keberangkatan;
    @BindView(R.id.tv_bandara_hotel)
    TextView tv_bandara_hotel;
    @BindView(R.id.cb_diurusPanitia_bandara_hotel)
    CheckBox cb_diurusPanitia_bandara_hotel;
    @BindView(R.id.cb_tidakDiurusPanitia_bandara_hotel)
    CheckBox cb_tidakDiurusPanitia_bandara_hotel;
    @BindView(R.id.et_bandara_hotel_waktuBerangkat)
    EditText et_bandara_hotel_waktuBerangkat;
    @BindView(R.id.et_bandara_hotel_waktuPulang)
    EditText et_bandara_hotel_waktuPulang;
    @BindView(R.id.et_keterangan_bandara_hotel)
    EditText et_keterangan_bandara_hotel;
    Calendar calendar;
    String[] JABATAN = {"Pendeta", "Pendeta Muda", "Pendeta Pembantu", "Evangelist"};
    String nama, password, role, jabatan, gerejaorg, ktp, nohp,
            namaistri, provinsi, kota, alamat, tgl_keberangkatan;
    int umur;
    boolean denganistri, diurus;

    public RegisterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_register_participant, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        calendar = Calendar.getInstance();

        ArrayAdapter<String> jabatanAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, JABATAN);
        spinner_jabatan.setAdapter(jabatanAdapter);
    }

    @OnClick(R.id.v_tgl_keberangkatan) void showDatePicker(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
        new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        updateLabel();
    }

    private void updateLabel() {
        String birthDateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(birthDateFormat, Locale.UK);
        v_tgl_keberangkatan.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        jabatan = item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
