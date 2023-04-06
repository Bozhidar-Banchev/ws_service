package com.example.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/** Main response object returned from kraken websocket server */
public class ExchangeElement {

  @JsonIgnore private String a;
  @JsonIgnore private String b;
  @JsonIgnore private String c;

  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  private List<ElementValue> as;

  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  private List<ElementValue> bs;

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  public String getC() {
    return c;
  }

  public void setC(String c) {
    this.c = c;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public List<ElementValue> getAs() {
    return as;
  }

  public void setAs(List<ElementValue> as) {
    this.as = as;
  }

  public List<ElementValue> getBs() {
    return bs;
  }

  public void setBs(List<ElementValue> bs) {
    this.bs = bs;
  }
}
