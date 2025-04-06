package com.example.radiscache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiscache.dto.request.ProductRequest;
import com.example.radiscache.entity.Product;
import com.example.radiscache.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
    private ProductService productService;
	
	@PostMapping
	Product createProduct(@RequestBody ProductRequest request) {
		return productService.create(request);
	}
    // Lấy thông tin sản phẩm theo ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    // Xóa tất cả cache của sản phẩm
    @DeleteMapping("/clear-cache")
    public void clearCache() {
        productService.clearAllProductsCache();
    }
}