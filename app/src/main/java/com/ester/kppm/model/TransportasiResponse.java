package com.ester.kppm.model;

import java.util.List;

/**
 * Created by mel on 7/15/17.
 */

public class TransportasiResponse {

    private List<TransportasiModel> transport;

    public TransportasiResponse(List<TransportasiModel> transport) {
        this.transport = transport;
    }

    public TransportasiResponse() {

    }

    public List<TransportasiModel> getTransport() {
        return transport;
    }

    public void setTransport(List<TransportasiModel> transport) {
        this.transport = transport;
    }
}
