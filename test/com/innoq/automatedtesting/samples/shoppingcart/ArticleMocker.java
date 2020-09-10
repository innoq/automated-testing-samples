package com.innoq.automatedtesting.samples.shoppingcart;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleMocker {

    private final Stock stock;
    private final PriceCalculator priceCalculator;

    private Article article;

    public ArticleMocker(Stock stock, PriceCalculator priceCalculator) {
        this.stock = stock;
        this.priceCalculator = priceCalculator;
        this.article = mock(Article.class);
    }

    public ArticleMocker withPrice(double price) {
        when(priceCalculator.calculatePrice(eq(article), any(CustomerStatus.class))).thenReturn(BigDecimal.valueOf(price));
        return this;
    }

    public ArticleMocker availableInStock() {
        when(stock.availableUnits(article)).thenReturn(9999);
        return this;
    }

    public Article andGetIt() {
        return article;
    }
}
