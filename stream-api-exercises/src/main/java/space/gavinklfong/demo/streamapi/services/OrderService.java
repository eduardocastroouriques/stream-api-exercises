package space.gavinklfong.demo.streamapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    public List<Order> hasAnyProductCategoryLike(String categoryProduct) {

        return orderRepository.findAll().stream()
                .filter(o ->
                        o.getProducts().stream()
                                .anyMatch(p -> p.getCategory().equalsIgnoreCase(categoryProduct))
                ).collect(Collectors.toList());
    }
}
