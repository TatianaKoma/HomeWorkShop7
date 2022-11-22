package com.example.homeworkshop7.mapper;

import com.example.homeworkshop7.client.ExchangeRateClient;
import com.example.homeworkshop7.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class UahToUsdMapper {
    private final ExchangeRateClient client;

    public BigDecimal getUsdValue(BigDecimal value) {
        ExchangeRate exchangeRate = client.getRates().stream()
                .filter(el -> el.getCcy().equals("USD"))
                .findAny().orElseThrow(IllegalArgumentException::new);
        String rateString = exchangeRate.getSale();
        BigDecimal rateFromString = new BigDecimal(String.valueOf(rateString));
        return value.divide(rateFromString, RoundingMode.UP);
    }
}
