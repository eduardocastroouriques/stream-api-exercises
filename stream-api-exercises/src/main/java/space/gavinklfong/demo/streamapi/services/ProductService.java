package space.gavinklfong.demo.streamapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private OrderRepo orderRepository;

    public List<Product> getByCategoryAndPrice(String category, Double price) {

        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .filter(product -> product.getPrice() > price)
                .collect(Collectors.toList());
    }

    public List<Product> getByCategoryAndApplyDiscount(String category, double discount) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .map(product -> product.withPrice(product.getPrice() * discount))
                .collect(Collectors.toList());
    }

    public Product getLowestPrice(String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Product::getPrice))
                .findFirst()
                .orElse(null);
    }

    public Product getLowestPriceByMinOperator(String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public List<Product> getByOrderDate(LocalDate date){
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getOrderDate().isEqual(date))
                .peek(order -> System.out.println(order))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
