package space.gavinklfong.demo.streamapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    public List<Order> hasAnyProductCategoryLike(String categoryProduct) {

        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equalsIgnoreCase(categoryProduct)))
                .collect(Collectors.toList());
    }

    public List<Product> getByCustomerTierAndBetweenDate(){
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 01)) >= 0)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Order> getMostRecentPlacedOrder(){
        return orderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
