package com.ester.kppm.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ester.kppm.R;
import com.ester.kppm.RestApi;
import com.ester.kppm.activity.RegisterActivity;
import com.ester.kppm.model.BandaraHotel;
import com.ester.kppm.model.HotelAcara;
import com.ester.kppm.model.HotelModel;
import com.ester.kppm.model.HotelResponse;
import com.ester.kppm.model.PesertaModel;
import com.ester.kppm.model.TipeKamarResponse;
import com.ester.kppm.model.TransportasiResponse;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.rg_bandara_hotel)
    RadioGroup rg_bandara_hotel;
    @BindView(R.id.tv_bandara_hotel)
    TextView tv_bandara_hotel;
    @BindView(R.id.rb_diurusPanitia_bandara_hotel)
    RadioButton rb_diurusPanitia_bandara_hotel;
    @BindView(R.id.rb_tidakDiurusPanitia_bandara_hotel)
    RadioButton rb_tidakDiurusPanitia_bandara_hotel;
    @BindView(R.id.spinner_jenis_transport_bandara_hotel)
    MaterialBetterSpinner spinner_jenis_transport_bandara_hotel;
    @BindView(R.id.et_keterangan_bandara_hotel)
    EditText et_keterangan_bandara_hotel;
    @BindView(R.id.rg_istri)
    RadioGroup rg_istri;
    @BindView(R.id.rg_hotel_acara)
    RadioGroup rg_hotel_acara;
    @BindView(R.id.rb_diurusPanitia_hotel_acara)
    RadioButton rb_diurusPanitia_hotel_acara;
    @BindView(R.id.rb_tidakDiurusPanitia_hotel_acara)
    RadioButton rb_tidakDiurusPanitia_hotel_acara;
    @BindView(R.id.spinner_jenis_transport_hotel_acara)
    MaterialBetterSpinner spinner_jenis_transport;
    @BindView(R.id.et_keterangan_hotel_acara)
    EditText et_keterangan_hotel_acara;
    @BindView(R.id.spinner_hotel)
    MaterialBetterSpinner spinner_hotel;
    @BindView(R.id.spinner_tipe_kamar)
    MaterialBetterSpinner spinner_tipe_kamar;
    @BindView(R.id.spinner_bed)
    MaterialBetterSpinner spinner_bed;
    @BindView(R.id.cb_konsumsi1)
    CheckBox cb_konsumsi1;
    @BindView(R.id.cb_konsumsi2)
    CheckBox cb_konsumsi2;
    @BindView(R.id.v_total_harga)
    TextView v_total_harga;
    @BindView(R.id.btn_register)
    Button btn_register;
    Calendar calendar;
    String[] JABATAN = {"Pendeta", "Pendeta Muda", "Pendeta Pembantu", "Evangelist"};
    List<String> HOTEL = new ArrayList<>();
    List<String> TIPEKAMAR = new ArrayList<>();
    String[] KASUR = {"Single bed", "Double bed"};
    List<String> TRANSPORTASI_BANDARAHOTEL = new ArrayList<>();
    List<String> TRANSPORTASI_HOTELACARA = new ArrayList<>();
    String nama, role, jabatan, gerejaorg, ktp, nohp, namaistri, provinsi, kota, alamat,
            waktudatang, waktupulang, keteranganBandaraHotel, jenisTransportBandaraHotel,
            jenisTransportHotelAcara, keteranganHotelAcara, hotel, tipeKamar;
    int umur, kasur;
    boolean denganistri, isDiurusHotelAcara, isDiurusBandaraHotel, isKonsumsi1, isKonsumsi2;
    float totalHarga;
    PesertaModel pesertaModel;
    BandaraHotel bandaraHotel;
    HotelAcara hotelAcara;
    HotelResponse hotelList;
    TipeKamarResponse tipekamar;
    TransportasiResponse transportasiList;
    private String status, info;

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
        loadHotel();

//        Jabatan
        ArrayAdapter<String> jabatanAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, JABATAN);
        spinner_jabatan.setAdapter(jabatanAdapter);

//        Hotel
        final ArrayAdapter<String> hotelAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, HOTEL);
        spinner_hotel.setAdapter(hotelAdapter);
        spinner_hotel.setOnClickListener(new View.OnClickListener() {
            String selectedHotel;
            @Override
            public void onClick(View view) {
                selectedHotel = spinner_hotel.getText().toString();
                checkHotel(selectedHotel);
//                spinner_hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        selectedHotel = adapterView.getItemAtPosition(i).toString();
//                        Toast.makeText(adapterView.getContext(), HOTEL.get(i) + " itemSelected: " + adapterView.getItemAtPosition(i).toString(),
//                                Toast.LENGTH_SHORT).show();
//                        Log.d("itemselected", adapterView.getItemAtPosition(i).toString());
//                        checkHotel(selectedHotel);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//                        Toast.makeText(adapterView.getContext(), "nothing selected",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });

        spinner_hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedHotel = spinner_hotel.getText().toString();
                Toast.makeText(adapterView.getContext(), HOTEL.get(i) + " itemSelected: " + adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("itemselected", adapterView.getItemAtPosition(i).toString());
                checkHotel(selectedHotel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(adapterView.getContext(), "nothing selected",
                        Toast.LENGTH_SHORT).show();
            }
        });


//        Tipe Kamar
        ArrayAdapter<String> tipeKamarAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TIPEKAMAR);
        spinner_tipe_kamar.setAdapter(tipeKamarAdapter);

//        Kasur
        ArrayAdapter<String> bedAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, KASUR);
        spinner_bed.setAdapter(bedAdapter);

//        Istri
        et_nama_istri.setVisibility(View.INVISIBLE);
        rg_istri.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.rb_bersama_istri){
                    denganistri = true;
                    Toast.makeText(getContext(), "bersama istri", Toast.LENGTH_SHORT).show();
                    et_nama_istri.setVisibility(View.VISIBLE);
                } if (i == R.id.rb_tidak_bersama_istri){
                    denganistri = false;
                }
            }
        });

//        Transportasi Bandara - Hotel
        loadTransportasiBandaraHotel();
        et_keterangan_bandara_hotel.setVisibility(View.INVISIBLE);
        rg_bandara_hotel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.rb_diurusPanitia_bandara_hotel){
                    isDiurusBandaraHotel = true;
                    et_keterangan_bandara_hotel.setVisibility(View.VISIBLE);
                } if(i == R.id.rb_tidakDiurusPanitia_bandara_hotel){
                    isDiurusBandaraHotel = false;
                }
            }
        });
        ArrayAdapter<String> bandaraHotelAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TRANSPORTASI_BANDARAHOTEL);
        spinner_jenis_transport_bandara_hotel.setAdapter(bandaraHotelAdapter);

//        Transportasi Hotel - Acara
        loadTransportasiHotelAcara();
        spinner_jenis_transport.setVisibility(View.INVISIBLE);
        et_keterangan_hotel_acara.setVisibility(View.INVISIBLE);
        rg_hotel_acara.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.rb_diurusPanitia_hotel_acara){
                    isDiurusHotelAcara = true;
                    spinner_jenis_transport.setVisibility(View.VISIBLE);
                    et_keterangan_hotel_acara.setVisibility(View.VISIBLE);
                } if(i == R.id.rb_tidakDiurusPanitia_hotel_acara){
                    isDiurusHotelAcara = false;
                }
            }
        });
        ArrayAdapter<String> hotelAcaraAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TRANSPORTASI_HOTELACARA);
        spinner_jenis_transport.setAdapter(hotelAcaraAdapter);


//        Konsumsi
        if(cb_konsumsi1.isChecked()){
            isKonsumsi1 = true;
        } if(cb_konsumsi2.isChecked()){
            isKonsumsi2 = true;
        }
    }

    private void checkHotel(String selectedHotel) {
        Toast.makeText(getContext(), "madafaka 2", Toast.LENGTH_SHORT).show();
//        hotel = "amaris";
        try {
            hotel = selectedHotel;//spinner_hotel.getText().toString();
            Log.d("hotelspinner", hotel);
            if(!hotel.isEmpty() && hotel!="" && hotel!=null){
                Log.d("hotel isn't empty", hotel);
                getTipeKamarByHotelName(hotel);
            } else {
                Toast.makeText(getContext(), "... "+hotel+" ...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Log.d("error in checkHotel", e.getLocalizedMessage());
            Toast.makeText(getContext(), "Silakan pilih Hotel terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public long getItemId(int position){
        return getItemId(position);
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
        pesertaModel = new PesertaModel();
        bandaraHotel = new BandaraHotel();
        hotelAcara = new HotelAcara();
        hotelAcara.setDiurus(isDiurusHotelAcara);
        hotelAcara.setJenis(jenisTransportHotelAcara);
        hotelAcara.setKeterangan(keteranganHotelAcara);
        bandaraHotel.setWaktudatang(waktudatang);
        bandaraHotel.setWaktupulang(waktupulang);
        bandaraHotel.setDiurus(isDiurusBandaraHotel);
        bandaraHotel.setJenis(jenisTransportBandaraHotel);
        bandaraHotel.setKeterangan(keteranganBandaraHotel);
        pesertaModel.setNama(nama);
        pesertaModel.setRole(role);
        pesertaModel.setGerejaorg(gerejaorg);
        pesertaModel.setJabatan(jabatan);
        pesertaModel.setKtp(ktp);
        pesertaModel.setNohp(nohp);
        pesertaModel.setUmur(umur);
        pesertaModel.setProvinsi(provinsi);
        pesertaModel.setKota(kota);
        pesertaModel.setAlamat(alamat);
        pesertaModel.setNamaistri(namaistri);
        pesertaModel.setDenganistri(denganistri);
        pesertaModel.setHotel(hotel);
        pesertaModel.setTipekamar(tipeKamar);
        pesertaModel.setKasur(kasur);
        pesertaModel.setKonsumsi1(isKonsumsi1);
        pesertaModel.setKonsumsi2(isKonsumsi2);
        pesertaModel.setBandaraHotel(bandaraHotel);
        pesertaModel.setHotelAcara(hotelAcara);
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<PesertaModel> call = restApi.register(pesertaModel);
        call.enqueue(new Callback<PesertaModel>() {
            @Override
            public void onResponse(Call<PesertaModel> call, Response<PesertaModel> response) {
                pesertaModel = response.body();
                status = pesertaModel.getStatus();
                info = pesertaModel.getInfo();
                if(status=="202"){
                    Toast.makeText(getContext(), "Kode Peserta Anda adalah " + info, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Maaf, gagal mendaftar ke sistem", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PesertaModel> call, Throwable t) {
                Toast.makeText(getContext(), "Silakan periksa koneksi Internt Anda", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadTransportasiBandaraHotel(){
        transportasiList = new TransportasiResponse();
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<TransportasiResponse> call = restApi.getAllTransportByTrip("1");
        call.enqueue(new Callback<TransportasiResponse>() {
            @Override
            public void onResponse(Call<TransportasiResponse> call, Response<TransportasiResponse> response) {
                transportasiList = response.body();
                int transportSize = transportasiList.getTransport().size();
                for(int i=0; i < transportSize; i++){
                    String transportJenis = transportasiList.getTransport().get(i).getJenis();
                    float transportHarga = transportasiList.getTransport().get(i).getHarga();
                    TRANSPORTASI_BANDARAHOTEL.add(transportJenis);
                }
            }

            @Override
            public void onFailure(Call<TransportasiResponse> call, Throwable t) {

            }
        });
    }

    public void loadTransportasiHotelAcara(){
        transportasiList = new TransportasiResponse();
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<TransportasiResponse> call = restApi.getAllTransportByTrip("2");
        call.enqueue(new Callback<TransportasiResponse>() {
            @Override
            public void onResponse(Call<TransportasiResponse> call, Response<TransportasiResponse> response) {
                transportasiList = response.body();
                int transportSize = transportasiList.getTransport().size();
                for(int i=0; i < transportSize; i++){
                    String transportJenis = transportasiList.getTransport().get(i).getJenis();
                    float transportHarga = transportasiList.getTransport().get(i).getHarga();
                    TRANSPORTASI_HOTELACARA.add(transportJenis + " - Rp " + transportHarga);
                }
            }

            @Override
            public void onFailure(Call<TransportasiResponse> call, Throwable t) {

            }
        });
    }


    public void loadHotel(){
        hotelList = new HotelResponse();
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<HotelResponse> call = restApi.getAllHotel();
        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                hotelList = response.body();
                int hotelSize = hotelList.getHotelModels().size();
                for(int i=0; i < hotelSize; i++){
                    String hotelName = hotelList.getHotelModels().get(i).getNamahotel();
                    HOTEL.add(hotelName);
                }
                Log.d("hotelSize", String.valueOf(hotelSize));
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                Log.d("loadHotel onFailure", t.getLocalizedMessage());
                Log.d("loadHotel onFailure", t.getStackTrace().toString());
            }
        });
    }

    private void getTipeKamarByHotelName(String namahotel) {
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<TipeKamarResponse> call = restApi.getTipeKamarByHotelName(namahotel);
        call.enqueue(new Callback<TipeKamarResponse>() {
            @Override
            public void onResponse(Call<TipeKamarResponse> call, Response<TipeKamarResponse> response) {
                tipekamar = response.body();
                int kamarSize = tipekamar.getTipekamar().size();
                Log.d("hotel", hotel + " | kamarSize = " + String.valueOf(kamarSize));
                for(int j=0; j < kamarSize ; j++){
                    String tipeKamar = tipekamar.getTipekamar().get(j).getNama();
                    Log.d("tipeKamar", "ke[" + j + "] = " + tipeKamar);
                    TIPEKAMAR.add(tipeKamar);
                }
            }

            @Override
            public void onFailure(Call<TipeKamarResponse> call, Throwable t) {
                Log.e("onfailure", t.getLocalizedMessage());
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
        } if(TextUtils.isEmpty(provinsi)){
            et_provinsi.setError(getString(R.string.provinsi_required));
        } if(TextUtils.isEmpty(kota)){
            et_kabupaten_kota.setError(getString(R.string.kabupaten_kota_required));
        } if(TextUtils.isEmpty(alamat)){
            et_alamat.setError(getString(R.string.alamat_required));
        } if(v_jam_keberangkatan.equals("Jam Keberangkatan")){
            v_jam_keberangkatan.setError(getString(R.string.jam_keberangkatan_required));
        } if (v_jam_kepulangan.equals("Jam Kepulangan")){
            v_jam_kepulangan.setError(getString(R.string.jam_kepulangan_required));
        } if(v_tgl_keberangkatan.equals("Tanggal Keberangkatan")){
            v_tgl_keberangkatan.setError(getString(R.string.tgl_keberangkatan_required));
        } if(v_tgl_kepulangan.equals("Tanggal Kepulangan")){
            v_tgl_kepulangan.setError(getString(R.string.tgl_kepulangan_required));
        }
    }

    private void getAllData() {
        setRole();
        nama = et_fullname.getText().toString();
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
        hotel = spinner_hotel.getText().toString();
        totalHarga = 1000000;

        Log.d("Register", "nama: " + nama);
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
        Log.d("Register", "denganistri: " + denganistri);
        Log.d("Register", "isDiurusBandaraHotel: " + isDiurusBandaraHotel);
        Log.d("Register", "isDiurusHotelAcara: " + isDiurusHotelAcara);
        Log.d("Register", "hotel: " + hotel);
        Log.d("Register", "totalHarga: " + totalHarga);
    }

    private void setRole() {
        RegisterActivity activity = (RegisterActivity) getActivity();
        role = activity.prepareRole();
        Log.d("Register", "role: "+ role);
    }
}
