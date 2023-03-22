package space.gavinklfong.demo.streamapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.services.OrderService;
import space.gavinklfong.demo.streamapi.services.ProductService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OrderRepo orderRepo;

	@Transactional
	@Override
	public void run(String... args) throws Exception {

//		System.out.println(orderRepo.findAll());

//		List<Product> books = productService.getByCategoryAndPrice("Books", 100.0);
//		List<Product> toys = productService.getByCategoryAndApplyDiscount("Toys", 0.9);
//		Product lowestPrice = productService.getLowestPrice("Teste");
//		Product lowestPriceByMinOperator = productService.getLowestPriceByMinOperator("Books");
		List<Product> productByOrderDate = productService.getByOrderDate(LocalDate.of(2021, 3, 15));

//		List<Product> byCustomerTierAndBetweenDate = orderService.getByCustomerTierAndBetweenDate();
//		List<Order> baby = orderService.hasAnyProductCategoryLike("Baby");
//		List<Order> mostRecentPlacedOrder = orderService.getMostRecentPlacedOrder();

		System.out.println("Starting point...");
		System.out.println(productByOrderDate);
	}

}
