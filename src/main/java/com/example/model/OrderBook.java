package com.example.model;

import java.time.LocalDateTime;
import java.util.List;

/** Value object hold data after aggregation. */
public class OrderBook {

  private List<OrderBookValue> asks;
  private List<OrderBookValue> bids;
  private OrderBookValue bestBid;
  private OrderBookValue bestAsk;
  private String ticker;

  private LocalDateTime time;

  public List<OrderBookValue> getAsks() {
    return asks;
  }

  public void setAsks(List<OrderBookValue> asks) {
    this.asks = asks;
  }

  public List<OrderBookValue> getBids() {
    return bids;
  }

  public void setBids(List<OrderBookValue> bids) {
    this.bids = bids;
  }

  public OrderBookValue getBestBid() {
    return bestBid;
  }

  public void setBestBid(OrderBookValue bestBid) {
    this.bestBid = bestBid;
  }

  public OrderBookValue getBestAsk() {
    return bestAsk;
  }

  public void setBestAsk(OrderBookValue bestAsk) {
    this.bestAsk = bestAsk;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
