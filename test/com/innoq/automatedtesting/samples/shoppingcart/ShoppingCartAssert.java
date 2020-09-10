package com.innoq.automatedtesting.samples.shoppingcart;

import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class ShoppingCartAssert extends AbstractAssert<ShoppingCartAssert, ShoppingCart> {
    public ShoppingCartAssert(ShoppingCart shoppingCart) {
        super(shoppingCart, ShoppingCartAssert.class);
    }

    public static ShoppingCartAssert assertThat(ShoppingCart shoppingCart) {
        return new ShoppingCartAssert(shoppingCart);
    }

    public ShoppingCartAssert containsNumberOfItems(int numberOfItems) {
        return isNotNull()
                .withFailMessage("expected ShoppingCart to contain %d items but it contained %d", numberOfItems, actual.items().size())
                .matches(shoppingCart -> shoppingCart.items().size() == numberOfItems);
    }

    public ItemAssert containsItemFor(Article article) {
        var maybeItem = actual.items().stream().filter(i -> i.article().equals(article)).findFirst();
        if (maybeItem.isPresent())
            return new ItemAssert(maybeItem.get());
        failWithMessage("expected ShoppingCart to contain Item for Article but it doesn't");
        return null;
    }

    public ShoppingCartAssert hasSubtotalAmount(double amount) {
        return isNotNull().matches(shoppingCart -> shoppingCart.subtotalAmount().equals(BigDecimal.valueOf(amount)));
    }

    public ShoppingCartAssert hasShippingAmount(double amount) {
        return isNotNull().matches(shoppingCart -> shoppingCart.shippingAmount().equals(BigDecimal.valueOf(amount)));
    }

    public ShoppingCartAssert hasTotalAmount(double amount) {
        return isNotNull().matches(shoppingCart -> shoppingCart.totalAmount().equals(BigDecimal.valueOf(amount)));
    }

    public static class ItemAssert extends AbstractAssert<ItemAssert, ShoppingCart.Item>{
        public ItemAssert(ShoppingCart.Item item) {
            super(item, ItemAssert.class);
        }

        public ItemAssert withQuantity(int quantity) {
            return isNotNull()
                    .withFailMessage("expected ShoppingCart Item to have Quantity %d but it was %d", quantity, actual.quantity())
                    .matches(item -> item.quantity() == quantity);
        }

        public ItemAssert withAmount(double amount) {
            return isNotNull()
                    .withFailMessage("expected ShoppingCart Item to have Amount %.2f but it was %.2f", amount, actual.amount())
                    .matches(item -> item.amount().equals(BigDecimal.valueOf(amount)));
        }
    }
}
