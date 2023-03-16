package space.gavinklfong.demo.streamapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.streamapi.services.OrderService;
import space.gavinklfong.demo.streamapi.services.ProductService;

import javax.transaction.Transactional;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		log.info("ProductService:");
		//System.out.println(productService.getByCategoryAndPrice("Books", 100.0));
		System.out.println(productService.getByCategoryAndApplyDiscount("Toys", 0.9));

//		log.info("OrderService:");
//		System.out.println(orderService.hasAnyProductCategoryLike("Baby"));
	}

}
