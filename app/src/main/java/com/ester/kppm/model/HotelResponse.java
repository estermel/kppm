package com.ester.kppm.model;

import java.util.List;

/**
 * Created by mel on 7/13/17.
 */

public class HotelResponse {

    List<HotelModel> hotel;

    public HotelResponse(){

    }

    public List<HotelModel> getHotelModels() {
        return hotel;
    }

    public void setHotelModels(List<HotelModel> hotel) {
        this.hotel = hotel;
    }
}
