package com.example.data;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class ExchangeData {

  private int channelId;

  private ExchangeElement element;
  private String channelName;
  private String pair;

  public int getChannelId() {
    return channelId;
  }

  public void setChannelId(int channelId) {
    this.channelId = channelId;
  }

  public ExchangeElement getElement() {
    return element;
  }

  public void setElement(ExchangeElement element) {
    this.element = element;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }
}
