package com.example.model;

/** POJO used for sending message request to ws server. */
public class WsMessage {

  private String event;
  private String[] pair;
  private Subscription subscription;

  public WsMessage(String event, String[] pair, Subscription subscription) {
    this.event = event;
    this.pair = pair;
    this.subscription = subscription;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String[] getPair() {
    return pair;
  }

  public void setPair(String[] pair) {
    this.pair = pair;
  }

  public Subscription getSubscription() {
    return subscription;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }
}
