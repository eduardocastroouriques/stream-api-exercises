package space.gavinklfong.demo.streamapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    public List<Product> getByCategoryAndPrice(String category, Double price) {

        return productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .filter(p -> p.getPrice() > price)
                .collect(Collectors.toList());
    }

    public List<Product> getByCategoryAndApplyDiscount(String category, double discount) {
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().equals(category))
                .map(product -> product.withPrice(product.getPrice() * discount))
                .collect(Collectors.toList());
    }
}
