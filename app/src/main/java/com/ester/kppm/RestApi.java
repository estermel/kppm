package com.ester.kppm;

import com.ester.kppm.model.HotelModel;
import com.ester.kppm.model.KonsumsiModel;
import com.ester.kppm.model.PanitiaModel;
import com.ester.kppm.model.TipeKamar;
import com.ester.kppm.model.TransportasiModel;
import com.ester.kppm.model.UserModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mel on 7/10/17.
 */

public interface RestApi {

    final static String URL = "http://kppm.cosmiclatte.id/";

    @POST("addHotel")
    Call<HotelModel> addHotel(@Body HotelModel hotelModel);

    @POST("addKonsumsi")
    Call<KonsumsiModel> addKonsumsi(@Body KonsumsiModel konsumsiModel);

    @POST("addPanitia")
    Call<PanitiaModel> addPanitia(@Body PanitiaModel panitiaModel);

    @POST("addTransportasi")
    Call<TransportasiModel> addTransportasi(@Body TransportasiModel transportasiModel);

    @POST("editHotel")
    Call<HotelModel> editHotel(@Body HotelModel hotelModel);

    @POST("editKonsumsi")
    Call<KonsumsiModel> editKonsumsi(@Body KonsumsiModel konsumsiModel);

    @POST("editPanitia")
    Call<PanitiaModel> editPanitia(@Body PanitiaModel panitiaModel);

    @POST("editTipeKamar")
    Call<TipeKamar> editTipeKamar(@Body TipeKamar tipeKamar);

    @POST("editTransportasi")
    Call<TransportasiModel> editTransportasi(@Body TransportasiModel transportasiModel);

    @POST("login")
    Call<UserModel> login(@Body UserModel userModel);

    @POST("register")
    Call<UserModel> register(@Body UserModel userModel);

    @GET("getPersonalDetails")
    Call<UserModel> getPersonalDetails();

    @GET("rmHotel")
    Call<HotelModel> rmHotel(@Field("id") int idHotel);

    @GET("rmKonsumsi")
    Call<KonsumsiModel> rmKonsumsi(@Field("id") int idKonsumsi);

    @GET("rmPanitia")
    Call<PanitiaModel> rmPanitia(@Field("id") int idPanitia);

    @GET("rmTipeKamar")
    Call<TipeKamar> rmTipeKamar(@Field("id") int idTipeKamar);

    @GET("rmTransport")
    Call<TransportasiModel> rmTransport(@Field("id") int idTransport);


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
