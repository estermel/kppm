package com.ester.kppm.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ester.kppm.R;
import com.ester.kppm.RestApi;
import com.ester.kppm.model.BandaraHotel;
import com.ester.kppm.model.RegistrasiModel;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    @BindView(R.id.rb_bersama_istri)
    RadioButton rb_bersama_istri;
    @BindView(R.id.rb_tidak_bersama_istri)
    RadioButton rb_tidak_bersama_istri;
    @BindView(R.id.et_nama_istri)
    EditText et_nama_istri;
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
    @BindView(R.id.v_jam_keberangkatan)
    TextView v_jam_keberangkatan;
    @BindView(R.id.v_tgl_kepulangan)
    TextView v_tgl_kepulangan;
    @BindView(R.id.v_jam_kepulangan)
    TextView v_jam_kepulangan;
    @BindView(R.id.tv_bandara_hotel)
    TextView tv_bandara_hotel;
    @BindView(R.id.rb_diurusPanitia_bandara_hotel)
    RadioButton rb_diurusPanitia_bandara_hotel;
    @BindView(R.id.rb_tidakDiurusPanitia_bandara_hotel)
    RadioButton rb_tidakDiurusPanitia_bandara_hotel;
    @BindView(R.id.et_bandara_hotel_waktuBerangkat)
    EditText et_bandara_hotel_waktuBerangkat;
    @BindView(R.id.et_bandara_hotel_waktuPulang)
    EditText et_bandara_hotel_waktuPulang;
    @BindView(R.id.et_keterangan_bandara_hotel)
    EditText et_keterangan_bandara_hotel;
    @BindView(R.id.rb_diurusPanitia_hotel_acara)
    RadioButton rb_diurusPanitia_hotel_acara;
    @BindView(R.id.rb_tidakDiurusPanitia_hotel_acara)
    RadioButton rb_tidakDiurusPanitia_hotel_acara;
    @BindView(R.id.spinner_jenis_transport)
    MaterialBetterSpinner spinner_jenis_transport;
    @BindView(R.id.et_keterangan_hotel_acara)
    EditText et_keterangan_hotel_acara;
    @BindView(R.id.spinner_hotel)
    MaterialBetterSpinner spinner_hotel;
    @BindView(R.id.spinner_tipe_kamar)
    MaterialBetterSpinner spinner_tipe_kamar;
    @BindView(R.id.spinner_bed)
    MaterialBetterSpinner spinner_bed;
    @BindView(R.id.cb_konsumsiD1)
    CheckBox cb_konsumsiD1;
    @BindView(R.id.cb_konsumsiD2)
    CheckBox cb_konsumsiD2;
    @BindView(R.id.cb_konsumsiD3)
    CheckBox cb_konsumsiD3;
    @BindView(R.id.v_total_harga)
    TextView v_total_harga;
    @BindView(R.id.btn_register)
    Button btn_register;
    Calendar calendar;
    String[] JABATAN = {"Pendeta", "Pendeta Muda", "Pendeta Pembantu", "Evangelist"};
    String nama, password, role, jabatan, gerejaorg, ktp, nohp,
            namaistri, provinsi, kota, alamat, tgl_keberangkatan, tgl_kepulangan,
            waktudatang, waktupulang, keteranganBandaraHotel, jenisTransportBandaraHotel,
            jenisTransportHotelAcara, keteranganHotelAcara, hotel, tipeKamar;
    int umur, kasur;
    boolean denganistri=false, isDiurusHotelAcara=false, isDiurusBandaraHotel=false, isKonsumsi1=false, isKonsumsi2=false,
            isKonsumsi3=false;
    float totalHarga;
    RegistrasiModel registrasiModel;
    BandaraHotel bandaraHotel;

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

//        Jabatan
        ArrayAdapter<String> jabatanAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, JABATAN);
        spinner_jabatan.setAdapter(jabatanAdapter);

//        Istri
        if (rb_bersama_istri.isSelected()){
            denganistri = true;
            et_nama_istri.setVisibility(View.VISIBLE);
        }

//        Transportasi Bandara - Hotel
        if(rb_diurusPanitia_bandara_hotel.isSelected()){
            isDiurusBandaraHotel = true;
            et_bandara_hotel_waktuBerangkat.setVisibility(View.VISIBLE);
            et_bandara_hotel_waktuPulang.setVisibility(View.VISIBLE);
            et_keterangan_bandara_hotel.setVisibility(View.VISIBLE);
        }

//        Transportasi Hotel - Acara
        if(rb_diurusPanitia_hotel_acara.isSelected()){
            isDiurusHotelAcara = true;
            spinner_jenis_transport.setVisibility(View.VISIBLE);
            et_keterangan_hotel_acara.setVisibility(View.VISIBLE);
        }

//        Konsumsi
        if(cb_konsumsiD1.isChecked()){
            isKonsumsi1 = true;
        } if(cb_konsumsiD2.isChecked()){
            isKonsumsi2 = true;
        } if(cb_konsumsiD3.isChecked()){
            isKonsumsi3 = true;
        }
    }

    @OnClick(R.id.v_tgl_keberangkatan) void showDatePickerBerangkat(){
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
        updateLabelBerangkat();
    }

    @OnClick(R.id.v_tgl_kepulangan) void showDatePickerPulang(){
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
        updateLabelPulang();
    }

    private void updateLabelBerangkat() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        v_tgl_keberangkatan.setText(sdf.format(calendar.getTime()));
    }

    private void updateLabelPulang() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        v_tgl_kepulangan.setText(sdf.format(calendar.getTime()));
    }

    @OnClick(R.id.v_jam_keberangkatan) void showTimePickerBerangkat(){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                v_jam_keberangkatan.setText(i + ":" + i1);
            }
        }, hour, minute,false);
        timePickerDialog.show();
    }

    @OnClick(R.id.v_jam_kepulangan) void showTimePickerPulang(){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                v_jam_kepulangan.setText(i + ":" + i1);
            }
        }, hour, minute,false);
        timePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        jabatan = item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @OnClick(R.id.btn_register) void register(){
        validateData();
        getAllData();
        registrasiModel = new RegistrasiModel();
        bandaraHotel = new BandaraHotel();
        bandaraHotel.setWaktudatang(waktudatang);
        bandaraHotel.setWaktupulang(waktupulang);
        registrasiModel.setNama(nama);
        registrasiModel.setRole(role);
        registrasiModel.setGerejaorg(gerejaorg);
        registrasiModel.setJabatan(jabatan);
        registrasiModel.setKtp(ktp);
        registrasiModel.setNohp(nohp);
        registrasiModel.setUmur(umur);
        registrasiModel.setProvinsi(provinsi);
        registrasiModel.setKota(kota);
        registrasiModel.setAlamat(alamat);
        registrasiModel.setNamaistri(namaistri);
        registrasiModel.setDenganistri(denganistri);
        registrasiModel.setHotel(hotel);
        registrasiModel.setBandaraHotel(bandaraHotel);
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<RegistrasiModel> call = restApi.register(registrasiModel);
        call.enqueue(new Callback<RegistrasiModel>() {
            @Override
            public void onResponse(Call<RegistrasiModel> call, Response<RegistrasiModel> response) {

            }

            @Override
            public void onFailure(Call<RegistrasiModel> call, Throwable t) {

            }
        });
    }

    private void validateData() {
        if(TextUtils.isEmpty(nama)){
            et_fullname.setError(getString(R.string.fullname_required));
        } if(TextUtils.isEmpty(jabatan)){
            spinner_jabatan.setError(getString(R.string.jabatan_required));
        } if(TextUtils.isEmpty(gerejaorg)){
            et_org_gereja.setError(getString(R.string.org_gereja_required));
        } if(TextUtils.isEmpty(ktp)){
            et_no_ktp.setError(getString(R.string.ktp_required));
        } if(TextUtils.isEmpty(nohp)){
            et_no_telepon.setError(getString(R.string.nohp_required));
        } if(TextUtils.isEmpty(String.valueOf(umur))){
            et_umur.setError(getString(R.string.umur_required));
        } if(TextUtils.isEmpty(provinsi)){
            et_provinsi.setError(getString(R.string.provinsi_required));
        } if(TextUtils.isEmpty(kota)){
            et_kabupaten_kota.setError(getString(R.string.kabupaten_kota_required));
        } if(TextUtils.isEmpty(alamat)){
            et_alamat.setError(getString(R.string.alamat_required));
        }
    }

    private void getAllData() {
        nama = et_fullname.getText().toString();
        role = "";
        jabatan = spinner_jabatan.getText().toString();
        gerejaorg = et_org_gereja.getText().toString();
        ktp = et_no_ktp.getText().toString();
        nohp = et_no_telepon.getText().toString();
//        umur = Integer.parseInt(et_umur.getText().toString());
        provinsi = et_provinsi.getText().toString();
        kota = et_kabupaten_kota.getText().toString();
        alamat = et_alamat.getText().toString();
        namaistri = et_nama_istri.getText().toString();
        waktudatang = v_tgl_keberangkatan.getText().toString() + " " + v_jam_keberangkatan.getText().toString();
        waktupulang = v_tgl_kepulangan.getText().toString() + " " + v_jam_kepulangan.getText().toString();
        keteranganBandaraHotel = et_keterangan_bandara_hotel.getText().toString();
        jenisTransportHotelAcara = spinner_jenis_transport.getText().toString();
        keteranganHotelAcara = et_keterangan_hotel_acara.getText().toString();
        totalHarga = 1000000;

        Log.d("Register", "nama: " + nama);
        Log.d("Register", "role: " + role);
        Log.d("Register", "jabatan: " + jabatan);
        Log.d("Register", "gerejaorg: " + gerejaorg);
        Log.d("Register", "ktp: " + ktp);
        Log.d("Register", "nohp: " + nohp);
        Log.d("Register", "umur: " + umur);
        Log.d("Register", "provinsi: " + provinsi);
        Log.d("Register", "kota: " + kota);
        Log.d("Register", "alamat: " + alamat);
        Log.d("Register", "namaistri: " + namaistri);
        Log.d("Register", "waktudatang: " + waktudatang);
        Log.d("Register", "waktupulang: " + waktupulang);
        Log.d("Register", "keteranganBandaraHotel: " + keteranganBandaraHotel);
        Log.d("Register", "jenisTransportHotelAcara: " + jenisTransportHotelAcara);
        Log.d("Register", "keteranganHotelAcara: " + keteranganHotelAcara);
        Log.d("Register", "isKonsumsi1: " + isKonsumsi1);
        Log.d("Register", "isKonsumsi2: " + isKonsumsi2);
        Log.d("Register", "isKonsumsi3: " + isKonsumsi3);
        Log.d("Register", "denganistri: " + denganistri);
        Log.d("Register", "isDiurusBandaraHotel: " + isDiurusBandaraHotel);
        Log.d("Register", "isDiurusHotelAcara: " + isDiurusHotelAcara);
        Log.d("Register", "totalHarga: " + totalHarga);
    }
}
