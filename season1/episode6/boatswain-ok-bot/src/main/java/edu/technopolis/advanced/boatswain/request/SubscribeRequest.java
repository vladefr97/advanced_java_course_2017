package edu.technopolis.advanced.boatswain.request;

public class SubscribeRequest extends Request<SubscribePayload> {
    private final String query;

    public SubscribeRequest(String subscriptionEndpoint) {
        this(subscriptionEndpoint, null);
    }

    public SubscribeRequest(String subscriptionEndpoint, SubscribePayload payload) {
        super(payload);
        this.query = subscriptionEndpoint;
    }

    @Override
    public String getQueryString() {
        return query;
    }
}
