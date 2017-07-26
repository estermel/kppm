package com.ester.kppm.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.ester.kppm.model.HotelResponse;
import com.ester.kppm.model.PesertaModel;
import com.ester.kppm.model.TipeKamarResponse;
import com.ester.kppm.model.TransportasiResponse;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashMap;
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
//    @BindView(R.id.pb)
//    ProgressBar pb;
    ProgressDialog pd;
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
    float totalHarga=0, hargaKamar=0, hargaTransportBandaraHotel, hargaTransportHotelAcara,
            hargaKonsumsi=0, hargaSnack=0;
    PesertaModel pesertaModel;
    BandaraHotel bandaraHotel;
    HotelAcara hotelAcara;
    HotelResponse hotelList;
    TipeKamarResponse tipekamar;
    TransportasiResponse transportasiList;
    private String status, info;
    HashMap<String, Float> mapTipeKamar;
    HashMap<String, Float> mapTransBandaraHotel;
    HashMap<String, Float> mapTransHotelAcara;
    public RegisterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_register_participant, container, false);
        pd = new ProgressDialog(getActivity());
        initViews();
        initListener();
        return view;
    }

    private void initListener() {
        if(spinner_jenis_transport_bandara_hotel.getText().toString().equals("")){
            hargaTransportBandaraHotel = 0;
        } if(spinner_jenis_transport.getText().toString().equals("")){
            hargaTransportHotelAcara = 0;
        } if(spinner_tipe_kamar.getText().toString().equals("")){
            hargaKamar = 0;
        } if(!cb_konsumsi1.isChecked()){
            hargaKonsumsi = 0;
        } if (!cb_konsumsi2.isChecked()){
            hargaSnack = 0;
        } if(!spinner_jenis_transport_bandara_hotel.getText().toString().equals("")){
            hargaTransportBandaraHotel = mapTransBandaraHotel.get(spinner_jenis_transport_bandara_hotel.getText().toString());
        } if(!spinner_jenis_transport.getText().toString().equals("")){
            hargaTransportHotelAcara = mapTransHotelAcara.get(spinner_jenis_transport.getText().toString());
        } if(!spinner_tipe_kamar.getText().toString().equals("")){
            hargaKamar = mapTipeKamar.get(spinner_tipe_kamar.getText().toString());
        } if(cb_konsumsi1.isChecked()){
            hargaKonsumsi = 145000;
        } if (cb_konsumsi2.isChecked()){
            hargaSnack = 123;
        }
        Log.d("initListener", "spinner_jenis_transport_bandara_hotel: " +
                spinner_jenis_transport_bandara_hotel + "\nspinner_jenis_transport: "+
                spinner_jenis_transport + "\nspinner_tipe_kamar: " +
                cb_konsumsi1 + "\ncb_konsumsi2: " + cb_konsumsi2 + "\ntotalHarga: " +
                hargaTransportHotelAcara+hargaTransportHotelAcara+hargaKamar+hargaKonsumsi+hargaSnack);
    }

    private void setTotalHarga() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        totalHarga = hargaTransportBandaraHotel + hargaTransportHotelAcara + hargaKamar
                + hargaKonsumsi + hargaSnack;
        String result = format.format(totalHarga) + ",-";
        if(totalHarga!=0){
            v_total_harga.setText(result);
        } else {
            v_total_harga.setText("Rp 0");
        }
        Log.d("setTotalHarga", result + "...");
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

        v_tgl_keberangkatan.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s.equals("Tanggal Keberangkatan")){
                    Toast.makeText(getContext(), "sama", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.equals("Tanggal Keberangkatan")){
                    Toast.makeText(getContext(), "beda", Toast.LENGTH_SHORT).show();
                }
            }

        });

//        Hotel
        final ArrayAdapter<String> hotelAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, HOTEL);
        spinner_hotel.setAdapter(hotelAdapter);
        spinner_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        spinner_hotel.addTextChangedListener(new TextWatcher() {
            String selectedHotel;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                emptyTipeKamarList();
                selectedHotel = spinner_hotel.getText().toString();
                checkHotel(selectedHotel);
            }
        });

        loadTipeKamar();

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
//                    Toast.makeText(getContext(), "bersama istri", Toast.LENGTH_SHORT).show();
                    et_nama_istri.setVisibility(View.VISIBLE);
                } if (i == R.id.rb_tidak_bersama_istri){
                    denganistri = false;
                    et_nama_istri.setVisibility(View.INVISIBLE);
                }
            }
        });

//        Transportasi Bandara - Hotel
        loadTransportasiBandaraHotel();
        spinner_jenis_transport_bandara_hotel.setVisibility(View.INVISIBLE);
        et_keterangan_bandara_hotel.setVisibility(View.INVISIBLE);
        rg_bandara_hotel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.rb_diurusPanitia_bandara_hotel){
                    isDiurusBandaraHotel = true;
                    spinner_jenis_transport_bandara_hotel.setVisibility(View.VISIBLE);
                    et_keterangan_bandara_hotel.setVisibility(View.VISIBLE);
                } if(i == R.id.rb_tidakDiurusPanitia_bandara_hotel){
                    isDiurusBandaraHotel = false;
                    spinner_jenis_transport_bandara_hotel.setVisibility(View.INVISIBLE);
                    et_keterangan_bandara_hotel.setVisibility(View.INVISIBLE);
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
                    spinner_jenis_transport.setVisibility(View.INVISIBLE);
                    et_keterangan_hotel_acara.setVisibility(View.INVISIBLE);
                }
            }
        });
        ArrayAdapter<String> hotelAcaraAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TRANSPORTASI_HOTELACARA);
        spinner_jenis_transport.setAdapter(hotelAcaraAdapter);

        spinner_jenis_transport_bandara_hotel.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    hargaTransportBandaraHotel = mapTransBandaraHotel.get(spinner_jenis_transport_bandara_hotel.getText().toString());
                setTotalHarga();
                Log.d("hargaTBanHot listener", String.valueOf(hargaTransportBandaraHotel));
            }
        });

        spinner_jenis_transport.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    hargaTransportHotelAcara = mapTransHotelAcara.get(spinner_jenis_transport.getText().toString());
                setTotalHarga();
                Log.d("hargaTHotAca listener", String.valueOf(hargaTransportHotelAcara));
            }
        });

        spinner_tipe_kamar.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    hargaKamar = mapTipeKamar.get(spinner_tipe_kamar.getText().toString());
                setTotalHarga();
                Log.d("hargaKamar listener", String.valueOf(hargaKamar));
            }
        });

//        Konsumsi
        if(cb_konsumsi1.isChecked()){
            isKonsumsi1 = true;
        } if(cb_konsumsi2.isChecked()){
            isKonsumsi2 = true;
        }

    }

    // Ganti Tipe Kamar
    private void loadTipeKamar() {
        ArrayAdapter<String> tipeKamarAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TIPEKAMAR);
        spinner_tipe_kamar.setAdapter(tipeKamarAdapter);
    }

    private void emptyTipeKamarList() {
        TIPEKAMAR.clear();
    }

    private void checkHotel(String selectedHotel) {
//        Toast.makeText(getContext(), "Hotel telah dipilih", Toast.LENGTH_SHORT).show();
        try {
            hotel = selectedHotel;
            if(!hotel.isEmpty() && hotel!="" && hotel!=null){
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
        getAllData();
        validateData();
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
        pesertaModel.setTotalharga(totalHarga);
        pd.setCancelable(false);
        pd.setMessage("Mengirimkan data Anda...");
        pd.show();
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<PesertaModel> call = restApi.register(pesertaModel);
        call.enqueue(new Callback<PesertaModel>() {
            @Override
            public void onResponse(Call<PesertaModel> call, Response<PesertaModel> response) {
                pd.dismiss();
                pesertaModel = response.body();
                status = pesertaModel.getStatus();
                info = pesertaModel.getInfo();
                Log.d("btn_register", "status: " + status + "\ninfo: " + info);
//                Toast.makeText(getContext(), info, Toast.LENGTH_LONG).show();
                pd.setContentView(R.layout.custom);
                pd.setTitle("BERHASIL");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) pd.findViewById(R.id.text);
                text.setText("Kode Peserta Anda adalah\n" + info);
                ImageView image = (ImageView) pd.findViewById(R.id.image);
                image.setImageResource(R.drawable.logo_kppm);

                Button dialogButton = (Button) pd.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pd.dismiss();
                    }
                });

                pd.show();
            }

            @Override
            public void onFailure(Call<PesertaModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getContext(), "Silakan periksa koneksi Internt Anda", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadTransportasiBandaraHotel(){
        transportasiList = new TransportasiResponse();
        mapTransBandaraHotel = new HashMap<>();
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
                    mapTransBandaraHotel.put(transportJenis, transportHarga);
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
        mapTransHotelAcara = new HashMap<>();
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
                    mapTransHotelAcara.put(transportJenis, transportHarga);
                    TRANSPORTASI_HOTELACARA.add(transportJenis);
                }
            }

            @Override
            public void onFailure(Call<TransportasiResponse> call, Throwable t) {

            }
        });
    }


    public void loadHotel(){
//        pb.setVisibility(View.VISIBLE);
        pd.setMessage("Mengambil data...");
        pd.setCancelable(false);
        pd.show();
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
//                pb.setVisibility(View.INVISIBLE);
                pd.dismiss();
                Log.d("hotelSize", String.valueOf(hotelSize));
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
//                pb.setVisibility(View.INVISIBLE);
                pd.dismiss();
                Toast.makeText(getContext(), "Terjadi kesalahan, silakan ulangi lagi dari halaman sebelumnya", Toast.LENGTH_LONG).show();
                Log.d("loadHotel onFailure", t.getLocalizedMessage());
                Log.d("loadHotel onFailure", t.getStackTrace().toString());
            }
        });
    }

    private void getTipeKamarByHotelName(String namahotel) {
//        A PROGRESS BAR SHOULD BE SHOWN HERE, CAN'T BE CANCELLED
//        pb.setVisibility(View.VISIBLE);
        pd.setMessage("Mengambil data...");
        pd.setCancelable(false);
        pd.show();
        mapTipeKamar = new HashMap<>();
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
                    float hargaKamar = tipekamar.getTipekamar().get(j).getHarga();
                    mapTipeKamar.put(tipeKamar, hargaKamar);
                    Log.d("tipeKamar", "ke[" + j + "] = " + tipeKamar + " harga = " + hargaKamar);
                    TIPEKAMAR.add(tipeKamar);
                }
                loadTipeKamar();
//                pb.setVisibility(View.INVISIBLE);
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<TipeKamarResponse> call, Throwable t) {
                Log.e("onfailure", t.getLocalizedMessage());
//                pb.setVisibility(View.INVISIBLE);
                pd.dismiss();
                Toast.makeText(getContext(), "Terjadi kesalahan, silakan ulangi pilih Hotel", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validateData() {
        if (nama.isEmpty()) {
            Toast.makeText(getContext(), "Nama masih kosong", Toast.LENGTH_SHORT).show();
        }
        if (jabatan.equals("")) {
            Toast.makeText(getContext(), "Jabatan masih kosong", Toast.LENGTH_SHORT).show();
        }
        if (gerejaorg.equals("")) {
            Toast.makeText(getContext(), "Gereja masih kosong", Toast.LENGTH_SHORT).show();
        } if(umur == 0){
            Toast.makeText(getContext(), "Umur masih kosong", Toast.LENGTH_SHORT).show();
        } if(ktp.equals("")){
            Toast.makeText(getContext(), "KTP masih kosong", Toast.LENGTH_SHORT).show();
        } if(nohp.equals("")){
            Toast.makeText(getContext(), "Nomor HP masih kosong", Toast.LENGTH_SHORT).show();
        } if(provinsi.equals("")){
            Toast.makeText(getContext(), "Provinsi masih kosong", Toast.LENGTH_SHORT).show();
        } if(kota.equals("")){
            Toast.makeText(getContext(), "Kota masih kosong", Toast.LENGTH_SHORT).show();
        } if(alamat.equals("")){
            Toast.makeText(getContext(), "Alamat masih kosong", Toast.LENGTH_SHORT).show();
        } if(v_tgl_keberangkatan.equals("Tanggal Keberangkatan")){
            Toast.makeText(getContext(), "Tanggal keberangkatan masih kosong", Toast.LENGTH_SHORT).show();
        }
//        if(TextUtils.isEmpty(nama)){
//            et_fullname.setError(getString(R.string.fullname_required));
//        } if(TextUtils.isEmpty(jabatan)){
//            spinner_jabatan.setError(getString(R.string.jabatan_required));
//        } if(TextUtils.isEmpty(gerejaorg)){
//            et_org_gereja.setError(getString(R.string.org_gereja_required));
//        } if(TextUtils.isEmpty(ktp)){
//            et_no_ktp.setError(getString(R.string.ktp_required));
//        } if(TextUtils.isEmpty(nohp)){
//            et_no_telepon.setError(getString(R.string.nohp_required));
//        } if(TextUtils.isEmpty(provinsi)){
//            et_provinsi.setError(getString(R.string.provinsi_required));
//        } if(TextUtils.isEmpty(kota)){
//            et_kabupaten_kota.setError(getString(R.string.kabupaten_kota_required));
//        } if(TextUtils.isEmpty(alamat)){
//            et_alamat.setError(getString(R.string.alamat_required));
//        } if(v_jam_keberangkatan.equals("Jam Keberangkatan")){
//            v_jam_keberangkatan.setError(getString(R.string.jam_keberangkatan_required));
//        } if (v_jam_kepulangan.equals("Jam Kepulangan")){
//            v_jam_kepulangan.setError(getString(R.string.jam_kepulangan_required));
//        } if(v_tgl_keberangkatan.equals("Tanggal Keberangkatan")){
//            v_tgl_keberangkatan.setError(getString(R.string.tgl_keberangkatan_required));
//        } if(v_tgl_kepulangan.equals("Tanggal Kepulangan")){
//            v_tgl_kepulangan.setError(getString(R.string.tgl_kepulangan_required));
//        }
    }

    private void getAllData() {
        setRole();
        nama = et_fullname.getText().toString();
        jabatan = spinner_jabatan.getText().toString();
        gerejaorg = et_org_gereja.getText().toString();
        ktp = et_no_ktp.getText().toString();
        nohp = et_no_telepon.getText().toString();
        if(et_umur.getText().toString().equals("")){
            umur = 0;
        } if(!et_umur.getText().toString().equals("")){
            umur = Integer.parseInt(et_umur.getText().toString());
        }
        provinsi = et_provinsi.getText().toString();
        kota = et_kabupaten_kota.getText().toString();
        alamat = et_alamat.getText().toString();
        namaistri = et_nama_istri.getText().toString();
        waktudatang = v_tgl_keberangkatan.getText().toString() + " " + v_jam_keberangkatan.getText().toString();
        waktupulang = v_tgl_kepulangan.getText().toString() + " " + v_jam_kepulangan.getText().toString();
        jenisTransportBandaraHotel = spinner_jenis_transport_bandara_hotel.getText().toString();
        keteranganBandaraHotel = et_keterangan_bandara_hotel.getText().toString();
        hargaTransportBandaraHotel = mapTransBandaraHotel.get(jenisTransportBandaraHotel);
        jenisTransportHotelAcara = spinner_jenis_transport.getText().toString();
        keteranganHotelAcara = et_keterangan_hotel_acara.getText().toString();
        hargaTransportHotelAcara = mapTransHotelAcara.get(jenisTransportHotelAcara);
        hotel = spinner_hotel.getText().toString();
        tipeKamar = spinner_tipe_kamar.getText().toString();
        if(spinner_bed.getText().toString().equals("Single bed")){
            kasur = 1;
        } if(spinner_bed.getText().toString().equals("Double bed")){
            kasur = 2;
        }
        hargaKamar = mapTipeKamar.get(tipeKamar);
        totalHarga = hargaTransportBandaraHotel + hargaTransportHotelAcara + hargaKamar;
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
        Log.d("Register", "keteranganBandaraHotel: " + keteranganBandaraHotel + " Rp " + hargaTransportBandaraHotel + ",-");
        Log.d("Register", "jenisTransportHotelAcara: " + jenisTransportHotelAcara+" Rp " + hargaTransportHotelAcara + ",-");
        Log.d("Register", "keteranganHotelAcara: " + keteranganHotelAcara);
        Log.d("Register", "isKonsumsi1: " + isKonsumsi1);
        Log.d("Register", "isKonsumsi2: " + isKonsumsi2);
        Log.d("Register", "denganistri: " + denganistri);
        Log.d("Register", "isDiurusBandaraHotel: " + isDiurusBandaraHotel);
        Log.d("Register", "isDiurusHotelAcara: " + isDiurusHotelAcara);
        Log.d("Register", "hotel: " + hotel);
        Log.d("Register", "hargaKamar: " + tipeKamar + " Rp " + hargaKamar + ",-");
        Log.d("Register", "totalHarga: Rp " + totalHarga + ",-");
    }

    private void setRole() {
        RegisterActivity activity = (RegisterActivity) getActivity();
        role = activity.prepareRole();
        Log.d("Register", "role: "+ role);
    }
}
