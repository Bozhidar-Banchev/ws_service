package com.example.controller;

import com.example.websocket.SocketClient;
import java.net.URI;
import java.net.http.HttpClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kraken")
public class KrakenController {

  public static final String KRAKEN_WS_URI = "wss://ws.kraken.com";
  private final SocketClient socketClient;

  public KrakenController(SocketClient socketClient) {
    this.socketClient = socketClient;
  }

  @PostMapping
  public void getOrderBook() {
    HttpClient.newHttpClient()
        .newWebSocketBuilder()
        .buildAsync(URI.create(KRAKEN_WS_URI), socketClient)
        .join();
  }
}
