package com.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.model.Product;
import com.project.repository.ProductReository;

@Service
public class ProductService {

	private ProductReository pr;

	// constructor injection
	public ProductService(ProductReository pr) {
		super();
		this.pr = pr;
	}

	public List<Product> listProduct() {
		return pr.findAll();
	}

	public Product addProduct(Product pro, MultipartFile imagefile) throws IOException {
		if (imagefile != null && !imagefile.isEmpty()) {
			pro.setImageName(imagefile.getOriginalFilename());
			pro.setImageType(imagefile.getContentType());
			pro.setImageData(imagefile.getBytes());
		}
		return pr.save(pro);
	}

	public Product getProduct(int id) {

		return pr.findById(id).orElse(null);
	}

//	public Product updateProduct(int id, Product product, MultipartFile imagefile) throws IOException {
//		product.setImageData(imagefile.getBytes());
//		product.setImageType(imagefile.getOriginalFilename());
//		product.setImageName(imagefile.getContentType());
//		return pr.save(product);
//	}

	public Product updateProduct(int id, Product updatedProduct, MultipartFile imagefile) throws IOException {

		// 1️⃣ Fetch existing product (MOST IMPORTANT)
		Product existing = pr.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		// 2️⃣ Update normal fields
		existing.setName(updatedProduct.getName());
		existing.setDescription(updatedProduct.getDescription());
		existing.setBrand(updatedProduct.getBrand());
		existing.setPrice(updatedProduct.getPrice());
		existing.setCategory(updatedProduct.getCategory());
		existing.setReleaseDate(updatedProduct.getReleaseDate());
		existing.setProductAvailable(updatedProduct.getProductAvailable());
		existing.setStockQuantity(updatedProduct.getStockQuantity());

		// 3️⃣ Update image ONLY if a new image is provided
		if (imagefile != null && !imagefile.isEmpty()) {
			existing.setImageName(imagefile.getOriginalFilename());
			existing.setImageType(imagefile.getContentType());
			existing.setImageData(imagefile.getBytes());
		}

		// 4️⃣ Save existing entity → UPDATE
		return pr.save(existing);
	}

	public void deleteProduct(int id) {
		pr.deleteById(id);

	}

	public List<Product> searchProduct(String keyword) {
		
		return pr.searchProduct(keyword);
	}

}
