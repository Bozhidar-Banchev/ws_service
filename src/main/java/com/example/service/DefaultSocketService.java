package com.example.service;

import com.example.data.ExchangeData;
import com.example.mapper.OrderBookValueMapper;
import com.example.model.OrderBook;
import com.example.model.OrderBookValue;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** Service implementation of {@link SocketService} */
@Service
public class DefaultSocketService implements SocketService {

  private final OrderBookValueMapper bookValueMapper;

  public DefaultSocketService(OrderBookValueMapper bookValueMapper) {
    this.bookValueMapper = bookValueMapper;
  }

  @Override
  public OrderBook generateOrderBook(ExchangeData exchangeData) {
    OrderBook orderBook = new OrderBook();
    List<OrderBookValue> asks = sortAsksDSC(exchangeData);

    List<OrderBookValue> bids = sortBids(exchangeData);

    orderBook.setAsks(asks);
    orderBook.setBids(bids);
    orderBook.setBestBid(bids.get(0));
    orderBook.setBestAsk(asks.get(0));

    orderBook.setTicker(exchangeData.getPair());
    orderBook.setTime(LocalDateTime.now());

    return orderBook;
  }

  @Override
  public String printOrderBook(OrderBook orderBook) {
    StringBuilder sb = new StringBuilder();
    sb.append("\n<------------------------------------>\n");
    sb.append("asks:\n");
    sb.append("[ ");
    for (OrderBookValue ask : orderBook.getAsks()) {
      sb.append("[ ").append(ask.getPrice()).append(", ").append(ask.getAmount()).append(" ], \n");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("]\n");
    sb.append("best bid: [ ")
        .append(orderBook.getBestBid().getPrice())
        .append(", ")
        .append(orderBook.getBestBid().getAmount())
        .append(" ]\n");
    sb.append("best ask: [ ")
        .append(orderBook.getBestAsk().getPrice())
        .append(", ")
        .append(orderBook.getBestAsk().getAmount())
        .append(" ]\n");
    sb.append("bids:\n");
    sb.append("[ ");
    for (OrderBookValue bid : orderBook.getBids()) {
      sb.append("[ ").append(bid.getPrice()).append(", ").append(bid.getAmount()).append(" ], \n");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("]\n");
    sb.append(orderBook.getTime()).append("\n");
    sb.append(orderBook.getTicker());
    sb.append("\n<------------------------------------>\n");
    return sb.toString();
  }

  private List<OrderBookValue> sortBids(ExchangeData exchangeData) {
    return bookValueMapper.toOrderBookValues(exchangeData.getElement().getBs()).stream()
        .sorted(Comparator.comparing(OrderBookValue::getPrice))
        .collect(Collectors.toList());
  }

  private List<OrderBookValue> sortAsksDSC(ExchangeData exchangeData) {
    return bookValueMapper.toOrderBookValues(exchangeData.getElement().getAs()).stream()
        .sorted(Comparator.comparing(OrderBookValue::getPrice).reversed())
        .collect(Collectors.toList());
  }
}
