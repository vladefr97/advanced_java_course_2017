package edu.technopolis.advanced.boatswain;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.technopolis.advanced.boatswain.incoming.request.Message;
import edu.technopolis.advanced.boatswain.incoming.request.Recipient;
import edu.technopolis.advanced.boatswain.request.GetSubscriptionsRequest;
import edu.technopolis.advanced.boatswain.request.SendMessagePayload;
import edu.technopolis.advanced.boatswain.request.SendMessageRequest;
import edu.technopolis.advanced.boatswain.request.SendRecipient;
import edu.technopolis.advanced.boatswain.request.SubscribePayload;
import edu.technopolis.advanced.boatswain.request.SubscribeRequest;
import edu.technopolis.advanced.boatswain.response.GetSubscriptionsResponse;
import edu.technopolis.advanced.boatswain.response.SendMessageResponse;
import edu.technopolis.advanced.boatswain.response.SubscribeResponse;
import junit.framework.TestCase;

public class OkApiClientTest {
    private static OkApiClient client;

    @BeforeClass
    public static void createClient() throws IOException {
        client = new OkApiClient("https", "api.ok.ru",
                "access_token=tkn1QkblURYK61ihvr5c9Q7YnpfWbu6htbhjP9QmkeuT9hcwrrvjIhousxsjNVK11WRUq1:CBAJEOPLEBABABABA");
    }

    @Test
    public void testSubscriptions() throws Exception {
        GetSubscriptionsResponse subscriptions = client.get(new GetSubscriptionsRequest("/graph/me/subscriptions"), GetSubscriptionsResponse.class);
        subscriptions.getSubscriptions().forEach(subscription -> System.out.println(subscription.getUrl()));
        TestCase.assertNotNull(subscriptions);
    }

    @Test
    public void testSubscribe() throws Exception {
        SubscribeRequest req = new SubscribeRequest("/graph/me/subscribe");
        SubscribePayload payload = new SubscribePayload();
        payload.setUrl("https://boatswain.jtechnopolis.pw/onmessage");
        req.setPayload(payload);
        SubscribeResponse status = client.post(req, SubscribeResponse.class);
        TestCase.assertNotNull(status);
    }

    @Test
    public void testPostMessage() throws Exception {
        SendMessageRequest req = new SendMessageRequest("/graph/me/messages", "chat:C3e7a6b21f900");
        Message message = new Message();
        message.setText("text");
        SendMessagePayload payload = new SendMessagePayload(new SendRecipient("user:541569460261"), message);
        req.setPayload(payload);
        SendMessageResponse status = client.post(req, SendMessageResponse.class);
        TestCase.assertNotNull(status);
    }

    @AfterClass
    public static void closeClient() throws IOException {
        client.close();
    }
}