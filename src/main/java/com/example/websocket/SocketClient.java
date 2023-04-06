package com.example.websocket;

import com.example.data.ExchangeData;
import com.example.model.OrderBook;
import com.example.model.Subscription;
import com.example.model.WsMessage;
import com.example.service.SocketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class SocketClient implements WebSocket.Listener {

  private static final Logger logger = Logger.getLogger(SocketClient.class.getName());

  public static final String BTC_USD_PAIR = "XBT/USD";
  public static final String ETH_USD_PAIR = "ETH/USD";

  public static final String BTC_ID = "336,{\"as\"";
  public static final String ETH_ID = "560,{\"as\"";
  public static final String SUBSCRIPTION_TYPE = "book";

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final SocketService socketService;

  public SocketClient(SocketService socketService) {
    this.socketService = socketService;
  }

  /**
   * Send subscribe message to kraken ws for BTC_USD and ETH_USD currency pair
   *
   * @param webSocket the WebSocket that has been connected
   */
  @Override
  public void onOpen(WebSocket webSocket) {
    Subscription subscription = new Subscription(SUBSCRIPTION_TYPE);
    WsMessage message =
        new WsMessage("subscribe", new String[] {BTC_USD_PAIR, ETH_USD_PAIR}, subscription);

    WebSocket.Listener.super.onOpen(webSocket);
    try {
      webSocket.sendText(objectMapper.writeValueAsString(message), true);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Accumulate ws response and print date in console.
   *
   * @param webSocket the WebSocket on which the data has been received
   * @param charSequence the data
   * @param last whether this invocation completes the message
   * @return
   */
  @Override
  public CompletionStage<?> onText(WebSocket webSocket, CharSequence charSequence, boolean last) {
    String sequence = charSequence.toString();
    if (sequence.contains(BTC_ID) || sequence.contains(ETH_ID)) {
      try {
        ExchangeData exchangeData = objectMapper.readValue(sequence, ExchangeData.class);
        OrderBook orderBook = socketService.generateOrderBook(exchangeData);

        logger.info(socketService.printOrderBook(orderBook));
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Failed to parse exchange data value: ", e);
      }
    }
    return WebSocket.Listener.super.onText(webSocket, charSequence, last);
  }

  @Override
  public void onError(WebSocket webSocket, Throwable error) {
    logger.info("Error occurs when reading ws data response ! " + webSocket.toString());
    WebSocket.Listener.super.onError(webSocket, error);
  }

  @Override
  public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
    return Listener.super.onClose(webSocket, statusCode, reason);
  }
}
