package com.example.mapper;

import com.example.data.ElementValue;
import com.example.model.OrderBookValue;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderBookValueMapper {

  List<OrderBookValue> toOrderBookValues(List<ElementValue> elementValue);

  @Mapping(target = "price", expression = "java(stringToDouble(elementValue.getPrice()))")
  @Mapping(target = "amount", expression = "java(stringToDouble(elementValue.getAmount()))")
  OrderBookValue toOrderBookValue(ElementValue elementValue);

  @Named("stringToDouble")
  default double stringToDouble(String value) {
    return Double.parseDouble(String.format("%.2f", Double.parseDouble(value)));
  }
}
