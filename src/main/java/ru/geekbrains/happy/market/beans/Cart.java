package ru.geekbrains.happy.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.happy.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.happy.market.model.OrderItem;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new CopyOnWriteArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void incrementCartItem(String productTitle) {

        for (OrderItem o : items) {
            if (o.getProduct().getTitle().equals(productTitle)) {
                o.incrementQuantity();
                recalculate();
            }
        }
    }

    public void decrementCartItem(String productTitle) {
        for (OrderItem o : items) {
            if (o.getProduct().getTitle().equals(productTitle) && o.getQuantity() > 1) {
                o.decrementQuantity();
                recalculate();
                break;
            }

            if (o.getProduct().getTitle().equals(productTitle) && o.getQuantity() == 1) {
                /**
                 * При декременте OrderItem, если его количество == 1 возникает ошибка
                 * java.util.ConcurrentModificationException: null
                 * 	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1012) ~[na:na]
                 * 	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:966) ~[na:na]
                 * 	at ru.geekbrains.happy.market.beans.Cart.decrementCartItem(Cart.java:71) ~[classes/:na]
                 *
                 *
                 * скорее всего из-за удаления элемента листа в цикле.
                 * Todo: Насколько правильно решать таким образом -> new CopyOnWriteArrayList<>(); ?
                 *
                 * */
                items.removeIf(p -> o.getProduct().getTitle().equals(productTitle));
                recalculate();
            }
        }
    }
}
