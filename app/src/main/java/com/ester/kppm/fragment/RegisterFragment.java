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
import android.widget.CompoundButton;
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
import com.ester.kppm.model.KonsumsiResponse;
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
    @BindView(R.id.tv_ket_harga_konsumsi)
    TextView tv_ket_harga_konsumsi;
    @BindView(R.id.cb_konsumsi1)
    CheckBox cb_konsumsi1;
    @BindView(R.id.v_total_harga)
    TextView v_total_harga;
    @BindView(R.id.btn_register)
    Button btn_register;
    ProgressDialog pd;
    Calendar calendar;
    String[] JABATAN = {"Pendeta", "Pendeta Muda", "Pendeta Pembantu", "Evangelist"};
    List<String> HOTEL = new ArrayList<>();
    List<String> TIPEKAMAR = new ArrayList<>();
    String[] KASUR = {"Single bed", "Double bed"};
    List<String> TRANSPORTASI_BANDARAHOTEL = new ArrayList<>();
    List<String> TRANSPORTASI_HOTELACARA = new ArrayList<>();
    String nama, role, jabatan, gerejaorg, ktp, nohp, namaistri, provinsi, kota, alamat,
            tgl_datang, tgl_pulang, jam_datang, jam_pulang,
            waktudatang, waktupulang, keteranganBandaraHotel, jenisTransportBandaraHotel,
            jenisTransportHotelAcara, keteranganHotelAcara, hotel, tipeKamar;
    int umur, kasur;
    boolean denganistri=false, isDiurusHotelAcara, isDiurusBandaraHotel, isKonsumsi1=false;
    float totalHarga, hargaKamar, hargaTransportBandaraHotel, hargaTransportHotelAcara,
            hargaKonsumsi, hargaTBanHotel, hargaTHotAcara;
    PesertaModel pesertaModel;
    BandaraHotel bandaraHotel;
    HotelAcara hotelAcara;
    HotelResponse hotelList;
    KonsumsiResponse konsumsiList;
    TipeKamarResponse tipekamar;
    TransportasiResponse transportasiList;
    private String status, info;
    HashMap<String, Float> mapTipeKamar;
    HashMap<String, Float> mapTransBandaraHotel;
    HashMap<String, Float> mapTransHotelAcara;
    ProgressDialog dialog;
    float sum=0, harga, grandTotal;

    public RegisterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_register_participant, container, false);
        pd = new ProgressDialog(getActivity());
        dialog = new ProgressDialog(getActivity());
        initViews();
        return view;
    }


    private void setTotalHarga() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        if(!isKonsumsi1){
            totalHarga = hargaTransportBandaraHotel + hargaTransportHotelAcara + hargaKamar
                    + 0;
        } if(isKonsumsi1){
            totalHarga = hargaTransportBandaraHotel + hargaTransportHotelAcara + hargaKamar
                    + hargaKonsumsi;
        }
        String result = format.format(totalHarga) + ",-";
        if(totalHarga!=0){
            v_total_harga.setText(result);
        } else {
            v_total_harga.setText("Rp 0");
        }
        grandTotal = totalHarga;
        Log.d("setTotalHarga", result + "...");
    }

    private void initViews() {
        ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        calendar = Calendar.getInstance();
        loadHotel();
        setRole();
//        Jabatan
        ArrayAdapter<String> jabatanAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, JABATAN);
        spinner_jabatan.setAdapter(jabatanAdapter);

        v_tgl_keberangkatan.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s.equals("Tanggal Keberangkatan")){
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.equals("Tanggal Keberangkatan")){
//                    check departure date with arrival date should be here
                    tgl_datang = v_tgl_keberangkatan.getText().toString();
                    validateDate(tgl_datang, tgl_pulang);
                }
            }

        });

        v_tgl_kepulangan.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s.equals("Tanggal Kepulangan")){

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.equals("Tanggal Keberangkatan")){
//                    check departure date with arrival date should be here
                    tgl_pulang = v_tgl_kepulangan.getText().toString();
                    validateDate(tgl_datang, tgl_pulang);
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
                    if(isKonsumsi1){
                        hargaKonsumsi = 2 * sum;
                    } if(!isKonsumsi1){
                        hargaKonsumsi = 0;
                    }
                    Log.d("hargaKonsumsi", "denganIstri: Rp " + hargaKonsumsi);
                    setTotalHarga();
                    et_nama_istri.setVisibility(View.VISIBLE);
                } if (i == R.id.rb_tidak_bersama_istri){
                    denganistri = false;
                    if(isKonsumsi1){
                        hargaKonsumsi = sum;
                    } if(!isKonsumsi1){
                        hargaKonsumsi = 0;
                    }
                    Log.d("hargaKonsumsi", "tanpaIstri: Rp " + hargaKonsumsi);
                    setTotalHarga();
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
                    hargaTransportBandaraHotel = 0;
                    setTotalHarga();
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
                    hargaTransportHotelAcara = 0;
                    setTotalHarga();
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
                    hargaTBanHotel = mapTransBandaraHotel.get(spinner_jenis_transport_bandara_hotel.getText().toString());
                    if(denganistri){
                        hargaTransportBandaraHotel = 2 * hargaTBanHotel;
                    } if(!denganistri){
                        hargaTransportHotelAcara = 1 * hargaTBanHotel;
                    }
                setTotalHarga();
                Log.d("hargaTBandaraHotel", String.valueOf(hargaTransportBandaraHotel));
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
                    hargaTHotAcara = mapTransHotelAcara.get(spinner_jenis_transport.getText().toString());
                    if(denganistri){
                        hargaTransportHotelAcara = 2 * hargaTHotAcara;
                    } if(!denganistri){
                        hargaTransportHotelAcara = 1 * hargaTHotAcara;
                    }
                setTotalHarga();
                Log.d("hargaTHotelAcara", String.valueOf(hargaTransportHotelAcara));
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
                    if(role.equals("khusus") && !denganistri){
                        hargaKamar = hargaKamar / 2;
                    }
                setTotalHarga();
                Log.d("hargaKamar", String.valueOf(hargaKamar));
            }
        });

        loadKonsumsi();

//        Konsumsi
        cb_konsumsi1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked){
                    Log.d("cb_konsumsi1", "checked");
                    isKonsumsi1 = true;
                    if(denganistri){
                        hargaKonsumsi = 2 * sum;
                    } if(!denganistri){
                        hargaKonsumsi = sum;
                    }
                    Log.d("cb_konsumsi1", "checked: " + hargaKonsumsi);
                    setTotalHarga();
                }

                else if (!isChecked) {
                    Log.d("cb_konsumsi1", "not checked");
                    isKonsumsi1 = false;
                    hargaKonsumsi = 0;
                    Log.d("cb_konsumsi1", "not checked: " + hargaKonsumsi);
                    setTotalHarga();
                }

            }
        });

    }

    private void validateDate(String tgl_datang, String tgl_pulang) {
        if(tgl_datang.equals(tgl_pulang)){
            Toast.makeText(getContext(), "Silakan isi tanggal yang benar", Toast.LENGTH_SHORT).show();
        }
//        Check in advance
//        if(tgl_datang >= tgl_pulang){
//            Toast.makeText(getContext(), "Kepulangan tidak boleh lebih dulu dari pada kedatangan"
//                    , Toast.LENGTH_SHORT).show();
//        } if(tgl_pulang <= tgl_datang){
//            Toast.makeText(getContext(), "Kepulangan tidak boleh lebih dulu dari pada kedatangan"
//                    , Toast.LENGTH_SHORT).show();
//        }
    }

    private void loadKonsumsi() {
        dialog.setMessage("Mengambil data...");
        dialog.setCancelable(false);
        dialog.show();
        konsumsiList = new KonsumsiResponse();
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<KonsumsiResponse> call = restApi.getAllKonsumsi();
        call.enqueue(new Callback<KonsumsiResponse>() {
            @Override
            public void onResponse(Call<KonsumsiResponse> call, Response<KonsumsiResponse> response) {
                konsumsiList = response.body();
                int hotelSize = konsumsiList.getKonsumsi().size();
                for(int i=0; i < hotelSize; i++){
                    harga = konsumsiList.getKonsumsi().get(i).getHarga();
                    sum += harga;
                }
                hargaKonsumsi=sum;
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                format.setCurrency(Currency.getInstance("IDR"));
                String result = format.format(sum) + ",-";
                tv_ket_harga_konsumsi.setText(result);
                dialog.dismiss();
                Log.d("hargaAllKonsumsi", String.valueOf(sum));
            }

            @Override
            public void onFailure(Call<KonsumsiResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Terjadi kesalahan, silakan ulangi lagi dari halaman sebelumnya", Toast.LENGTH_LONG).show();
                Log.d("loadKonsumsi onFailure", t.getLocalizedMessage());
                Log.d("loadKonsumsi onFailure", t.getStackTrace().toString());
            }
        });
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

    private void daftarKPPM(){
        pesertaModel = new PesertaModel();
        bandaraHotel = new BandaraHotel();
        hotelAcara = new HotelAcara();
        pesertaModel.setNama(nama);
        pesertaModel.setRole(role);
        pesertaModel.setTotalharga(grandTotal);
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
        hotelAcara.setDiurus(isDiurusHotelAcara);
        hotelAcara.setJenis(jenisTransportHotelAcara);
        hotelAcara.setKeterangan(keteranganHotelAcara);
        bandaraHotel.setWaktudatang(waktudatang);
        bandaraHotel.setWaktupulang(waktupulang);
        bandaraHotel.setDiurus(isDiurusBandaraHotel);
        bandaraHotel.setJenis(jenisTransportBandaraHotel);
        bandaraHotel.setKeterangan(keteranganBandaraHotel);
        pesertaModel.setBandaraHotel(bandaraHotel);
        pesertaModel.setHotelAcara(hotelAcara);
        dialog.setCancelable(false);
        dialog.setMessage("Mengirimkan data Anda...");
        dialog.show();
        Log.d("Register", "nama: " + nama);
        Log.d("Register", "jabatan: " + jabatan);
        Log.d("Register", "gerejaorg: " + gerejaorg);
        Log.d("Register", "ktp: " + ktp);
        Log.d("Register", "nohp: " + nohp);
        Log.d("Register", "umur: " + umur);
        Log.d("Register", "provinsi: " + provinsi);
        Log.d("Register", "kota: " + kota);
        Log.d("Register", "alamat: " + alamat);
        Log.d("Register", "denganistri: " + denganistri);
        Log.d("Register", "namaistri: " + namaistri);
        Log.d("Register", "waktudatang: " + waktudatang);
        Log.d("Register", "waktupulang: " + waktupulang);
        Log.d("Register", "keteranganBandaraHotel: " + keteranganBandaraHotel + " Rp " + hargaTransportBandaraHotel + ",-");
        Log.d("Register", "jenisTransportHotelAcara: " + jenisTransportHotelAcara+" Rp " + hargaTransportHotelAcara + ",-");
        Log.d("Register", "keteranganHotelAcara: " + keteranganHotelAcara);
        Log.d("Register", "isKonsumsi1: " + isKonsumsi1);
        Log.d("Register", "isDiurusBandaraHotel: " + isDiurusBandaraHotel);
        Log.d("Register", "isDiurusHotelAcara: " + isDiurusHotelAcara);
        Log.d("Register", "hotel: " + hotel);
        Log.d("Register", "hargaKonsumsi: Rp " + hargaKonsumsi);
        Log.d("Register", "hargaKamar: " + tipeKamar + " Rp " + hargaKamar + ",-");
        Log.d("Register", "totalHarga: Rp " + grandTotal + ",-");
        RestApi restApi = RestApi.retrofit.create(RestApi.class);
        Call<PesertaModel> call = restApi.register(pesertaModel);
        call.enqueue(new Callback<PesertaModel>() {
            @Override
            public void onResponse(Call<PesertaModel> call, Response<PesertaModel> response) {
                dialog.dismiss();
                pesertaModel = response.body();
                status = pesertaModel.getStatus();
                info = pesertaModel.getInfo();
                Log.d("btn_register", "status: " + status + "\ninfo: " + info);
                pd.show();
                if(status.equals("202")){
                    pd.setContentView(R.layout.custom);
                    pd.setTitle("BERHASIL");
                    pd.setCancelable(false);

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


                } if(!status.equals("202")){
                    Toast.makeText(getContext(), info,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PesertaModel> call, Throwable t) {
//                pd.dismiss();
                dialog.dismiss();
                Toast.makeText(getContext(), "Silakan periksa koneksi Internt Anda", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_register) void register(){
        getAllData();
        validateData();
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
        dialog.setMessage("Mengambil data...");
        dialog.setCancelable(false);
        dialog.show();
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
                dialog.dismiss();
                Log.d("hotelSize", String.valueOf(hotelSize));
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Terjadi kesalahan, silakan ulangi lagi dari halaman sebelumnya", Toast.LENGTH_LONG).show();
                Log.d("loadHotel onFailure", t.getLocalizedMessage());
                Log.d("loadHotel onFailure", t.getStackTrace().toString());
            }
        });
    }

    private void getTipeKamarByHotelName(String namahotel) {
//        A PROGRESS BAR SHOULD BE SHOWN HERE, CAN'T BE CANCELLED
//        pb.setVisibility(View.VISIBLE);
        dialog.setMessage("Mengambil data...");
        dialog.setCancelable(false);
        dialog.show();
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
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<TipeKamarResponse> call, Throwable t) {
                Log.e("onfailure", t.getLocalizedMessage());
//                pb.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                Toast.makeText(getContext(), "Terjadi kesalahan, silakan ulangi pilih Hotel", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validateData() {
        if(nama.isEmpty() || jabatan.isEmpty() || gerejaorg.isEmpty() || umur == 0 || ktp.isEmpty()
                || nohp.isEmpty() || provinsi.isEmpty() || kota.isEmpty() || alamat.isEmpty()
                || tgl_datang.equals("-") || tgl_pulang.equals("-")
                || jam_datang.equals("-") || jam_pulang.equals("-")
                || hotel.isEmpty() || tipeKamar.isEmpty() || spinner_bed.getText().toString().equals("")){
            Toast.makeText(getContext(), "Mohon isi semua data", Toast.LENGTH_SHORT).show();
            Log.d("validateData", "show toast");
        } else{
            Log.d("validateData", "getAllData has done");
            daftarKPPM();
        }
    }

    private void getAllData() {
        nama = et_fullname.getText().toString();
        jabatan = spinner_jabatan.getText().toString();
        gerejaorg = et_org_gereja.getText().toString();
        ktp = et_no_ktp.getText().toString();
        nohp = et_no_telepon.getText().toString();

//        set umur
        if(et_umur.getText().toString().equals("")){
            umur = 0;
        } if(!et_umur.getText().toString().equals("")){
            umur = Integer.parseInt(et_umur.getText().toString());
        }
        provinsi = et_provinsi.getText().toString();
        kota = et_kabupaten_kota.getText().toString();
        alamat = et_alamat.getText().toString();
        namaistri = et_nama_istri.getText().toString();

//        set tanggal keberangkatan & kepulangan
        if(v_tgl_keberangkatan.getText().toString().equals("Tanggal Keberangkatan")){
            tgl_datang = "-";
        } if(!v_tgl_keberangkatan.getText().toString().equals("Tanggal Keberangkatan")){
            tgl_datang = v_tgl_keberangkatan.getText().toString();
        } if(v_tgl_kepulangan.getText().toString().equals("Tanggal Kepulangan")){
            tgl_pulang = "-";
        } if(!v_tgl_kepulangan.getText().toString().equals("Tanggal Kepulangan")){
            tgl_pulang = v_tgl_kepulangan.getText().toString();
        }
//        set jam keberangkatan & kepulangan
        if(v_jam_keberangkatan.getText().toString().equals("Jam Keberangkatan")){
            jam_datang = "-";
        } if(!v_jam_keberangkatan.getText().toString().equals("Jam Keberangkatan")){
            jam_datang = v_jam_keberangkatan.getText().toString();
        } if(v_jam_kepulangan.getText().toString().equals("Jam Kepulangan")){
            jam_pulang = "-";
        } if(!v_jam_kepulangan.getText().toString().equals("Jam Kepulangan")){
            jam_pulang = v_jam_kepulangan.getText().toString();
        }

        waktudatang = tgl_datang + " " + jam_datang;
        waktupulang = tgl_pulang + " " + jam_pulang;
        jenisTransportBandaraHotel = spinner_jenis_transport_bandara_hotel.getText().toString();
        keteranganBandaraHotel = et_keterangan_bandara_hotel.getText().toString();

//        set harga transport bandara - hotel
        if(jenisTransportBandaraHotel.equals("")){
            hargaTransportBandaraHotel = 0;
        } if(!jenisTransportBandaraHotel.equals("")) {
            hargaTransportBandaraHotel = mapTransBandaraHotel.get(jenisTransportBandaraHotel);
        }

        jenisTransportHotelAcara = spinner_jenis_transport.getText().toString();
        keteranganHotelAcara = et_keterangan_hotel_acara.getText().toString();

//        set harga transport hotel - acara
        if(jenisTransportHotelAcara.equals("")){
            hargaTransportHotelAcara = 0;
        } if(!jenisTransportHotelAcara.equals("")) {
            hargaTransportHotelAcara = mapTransHotelAcara.get(jenisTransportHotelAcara);
        }

        hotel = spinner_hotel.getText().toString();
        tipeKamar = spinner_tipe_kamar.getText().toString();

//      set tipe kasur
        if(spinner_bed.getText().toString().equals("Single bed")){
            kasur = 1;
        } if(spinner_bed.getText().toString().equals("Double bed")){
            kasur = 2;
        }
//        set harga kamar
        if(tipeKamar.equals("")){
            hargaKamar = 0;
        } if(!tipeKamar.equals("")) {
            hargaKamar = mapTipeKamar.get(tipeKamar);
        }

        totalHarga = hargaTransportBandaraHotel + hargaTransportHotelAcara + hargaKamar;
    }

    private void setRole() {
        RegisterActivity activity = (RegisterActivity) getActivity();
        role = activity.prepareRole();
        Log.d("Register", "role: "+ role);
    }
}
