package com.ester.kppm.model;

import java.util.List;

/**
 * Created by mel on 7/15/17.
 */

public class TipeKamarResponse {

    List<TipeKamar> tipekamar;

    public TipeKamarResponse(List<TipeKamar> tipekamar) {
        this.tipekamar = tipekamar;
    }

    public List<TipeKamar> getTipekamar() {
        return tipekamar;
    }

    public void setTipekamar(List<TipeKamar> tipekamar) {
        this.tipekamar = tipekamar;
    }
}
