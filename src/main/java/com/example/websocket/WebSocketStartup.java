package com.example.websocket;

import java.net.URI;
import java.net.http.HttpClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WebSocketStartup {

  public static final String KRAKEN_WS_URI = "wss://ws.kraken.com";

  private final SocketClient socketClient;

  public WebSocketStartup(SocketClient socketClient) {
    this.socketClient = socketClient;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void runWebsocketAfterStartup() {
    HttpClient.newHttpClient()
        .newWebSocketBuilder()
        .buildAsync(URI.create(KRAKEN_WS_URI), socketClient)
        .join();
  }
}
