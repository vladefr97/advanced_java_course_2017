package edu.technopolis.advanced.boatswain;

import edu.technopolis.advanced.boatswain.request.Request;
import edu.technopolis.advanced.boatswain.request.RequestPayload;

public class CurrencyRequest extends Request<RequestPayload.EmptyPayload, CurrencyRequest> {
    private final String query;

    public CurrencyRequest(String query) {
        this.query = query;
    }

    @Override
    protected CurrencyRequest thiss() {
        return this;
    }

    @Override
    public String getQueryStart() {
        return query;
    }

    @Override
    public String toString() {
        return "CurrencyRequest{" +
                "queryStart='" + getQueryStart() + '"' +
                '}';
    }
}
