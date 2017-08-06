package com.ester.kppm;

import com.ester.kppm.model.HotelModel;
import com.ester.kppm.model.HotelResponse;
import com.ester.kppm.model.KonsumsiModel;
import com.ester.kppm.model.KonsumsiResponse;
import com.ester.kppm.model.PanitiaModel;
import com.ester.kppm.model.PesertaModel;
import com.ester.kppm.model.TipeKamar;
import com.ester.kppm.model.TipeKamarResponse;
import com.ester.kppm.model.TransportasiModel;
import com.ester.kppm.model.TransportasiResponse;
import com.ester.kppm.model.UserModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mel on 7/10/17.
 */

public interface RestApi {

    final static String URL = "http://kppm.cosmiclatte.id/";

    @GET("exportCsv")
    Call<PesertaModel> exportCsv();

    @GET("getAllHotel")
    Call<HotelResponse> getAllHotel();

    @GET("getTipeKamarByName")
    Call<TipeKamarResponse> getTipeKamarByHotelName(@Query("hotel") String namahotel);

    @GET("getAllTransport")
    Call<TransportasiResponse> getAllTransportByTrip(@Query("trip") String trip);

    @GET("getAllKonsumsi")
    Call<KonsumsiResponse> getAllKonsumsi();

    @POST("register")
    Call<PesertaModel> register(@Body PesertaModel pesertaModel);

    @GET("getPersonalDetails")
    Call<PesertaModel> getPersonalDetails();

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
