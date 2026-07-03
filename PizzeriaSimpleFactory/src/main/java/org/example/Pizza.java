package org.example;

import java.math.BigDecimal;
import java.util.List;

public interface Pizza {
    String getName();
    List<String> getIngredients();
    BigDecimal getPrice();
}
