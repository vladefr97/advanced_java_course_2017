package edu.technopolis.advanced.boatswain.request;

public class GetSubscriptionsRequest extends Request<RequestPayload> {
    private final String query;

    public GetSubscriptionsRequest(String query) {
        this.query = query;
    }

    @Override
    public String getQueryString() {
        return query;
    }
}
