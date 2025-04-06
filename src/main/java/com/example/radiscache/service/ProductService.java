package com.example.radiscache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.radiscache.dto.request.ProductRequest;
import com.example.radiscache.entity.Product;
import com.example.radiscache.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
	public Product create(ProductRequest request) {
		Product product = new Product();
		product.setCategory(request.getCategory());
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		
		return productRepository.save(product);
		
	}
	
    // Lấy thông tin sản phẩm từ cache nếu có, nếu không thì từ database
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        System.out.println("Fetching product from database...");
        return productRepository.findById(id).orElse(null);
    }

    // Cập nhật sản phẩm và xóa cache cũ của sản phẩm đó
    @CacheEvict(value = "products", key = "#id")
    public void updateProduct(Long id, Product updatedProduct) {
        System.out.println("Updating product in database...");
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
    }

    // Xóa tất cả cache của sản phẩm (Ví dụ: khi có thay đổi lớn như xóa tất cả sản phẩm)
    @CacheEvict(value = "products", allEntries = true)
    public void clearAllProductsCache() {
        System.out.println("Clearing all product cache...");
    }
}