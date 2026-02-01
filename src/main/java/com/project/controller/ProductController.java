package com.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.project.model.Product;
import com.project.service.ProductService;



@RestController
@CrossOrigin // its the annotation that can be use to connect the frountend and backend which
				// are working on the different port
@RequestMapping("/api")
public class ProductController {

	private ProductService ps;

	// constructor injection
	public ProductController(ProductService ps) {
		super();
		this.ps = ps;
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> listProduct() {
		return new ResponseEntity<>(ps.listProduct(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {

		Product pro = ps.getProduct(id);

		if (pro != null) {
			return new ResponseEntity<>(pro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// RequestBody accept the whole json/body as the object
	// RequestPart accept the object in the parts
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart("product" ) Product product, 
			@RequestPart ("imagefile") MultipartFile imagefile) {
		try {
			Product pro1 = ps.addProduct(product, imagefile);
			return new ResponseEntity<>(pro1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//    @PostMapping(
//            value = "/product",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE  // it support the file that is image 
//        )
//        public ResponseEntity<?> addProduct(
//                @RequestPart("pro") String productJson,  //@tequestPart is unreliable for JSON + file 
//                @RequestPart("imagefile") MultipartFile imagefile
//        ) {
//            try {
//            	    System.out.println(productJson);
//                ObjectMapper mapper = new ObjectMapper();  //ObjectMapper is a Jackson library class used to: Convert JSON ↔ Java Objects
//                Product pro = mapper.readValue(productJson, Product.class);
//
//                Product saved = ps.addProduct(pro, imagefile);
//                return new ResponseEntity<>(saved, HttpStatus.CREATED);
//
//            } catch (Exception e) {
//                return new ResponseEntity<>(e.getMessage(),
//                        HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//    }

	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
		Product product = ps.getProduct(productId);
		byte[] imageFile = product.getImageData();
		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart ("product") Product product,
			@RequestPart(value = "imagefile", required = false)  MultipartFile imagefile) {
//		Product pro1 = null;
		try {
			 ps.updateProduct(id, product, imagefile);
			 return new ResponseEntity<>("Updated", HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>("Failed to Update", HttpStatus.OK);
		}
//		if (pro1 != null) {
//			return new ResponseEntity<>("Updated", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
//		}

	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		Product product = ps.getProduct(id);
		if (product != null) {
			ps.deleteProduct(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
		System.out.println("searching with keyword: "+keyword);
		List<Product> products = ps.searchProduct(keyword);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

}
