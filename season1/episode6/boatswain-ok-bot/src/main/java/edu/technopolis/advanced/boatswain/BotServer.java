package edu.technopolis.advanced.boatswain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpServer;

class BotServer {
    private static final Logger log = LoggerFactory.getLogger(BotServer.class);

    private final HttpServer httpServer;

    BotServer(String endpoint) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 80);
        httpServer = HttpServer.create(addr, 0);
        httpServer.createContext(endpoint, exchange -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                String data;
                while ((data = reader.readLine()) != null) {
                    log.info(data);
                }
            }

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            byte[] response = "ok".getBytes();
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        });
    }

    void start() {
        httpServer.start();
    }

    void stop() {
        httpServer.stop(1);
    }
}
