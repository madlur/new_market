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
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
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
            } else if (o.getProduct().getTitle().equals(productTitle) && o.getQuantity() == 1) {
                /**
                 * При декременте OrderItem, если его количество == 1 возникает ошибка 2021-01-27 18:16:05.297
                 * ERROR 19304 --- [nio-8189-exec-2] o.a.c.c.C.[.[.[.[dispatcherServlet]
                 * : Servlet.service() for servlet [dispatcherServlet] in context with path [/happy]
                 * threw exception [Request processing failed; nested exception is java.util.
                 * ConcurrentModificationException] with root cause
                 *
                 * По двойному клику по форме на фронте продукт исчезает из корзины, но баг сам устранить не смог
                 * м.б. это как то связано с операциями над Листом OrderItems...
                 *
                 * скорее всего из-за удаления элемента листа в цикле. Todo: Надо добавить итератор или найти другое решение
                 *
                 * */
                items.remove(o);
                recalculate();
            }
        }
    }
}
