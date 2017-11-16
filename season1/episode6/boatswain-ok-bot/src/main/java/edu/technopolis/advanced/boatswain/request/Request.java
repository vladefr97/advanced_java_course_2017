package edu.technopolis.advanced.boatswain.request;

public abstract class Request<RP extends RequestPayload> {
    private RP payload;

    public Request() {
    }

    public Request(RP payload) {
        this.payload = payload;
    }

    public RP getPayload() {
        return payload;
    }

    public void setPayload(RP payload) {
        this.payload = payload;
    }

    public abstract String getQueryString();
}
