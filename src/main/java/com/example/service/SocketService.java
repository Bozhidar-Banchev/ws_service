package com.example.service;

import com.example.data.ExchangeData;
import com.example.model.OrderBook;

/** Service that manage websocket client functionality. */
public interface SocketService {

  OrderBook generateOrderBook(ExchangeData exchangeData);

  String printOrderBook(OrderBook orderBook);
}
