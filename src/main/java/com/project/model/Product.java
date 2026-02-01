package com.project.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

//@Data  -->gives the gettere and setters
//@NoArgsConstructor  -->gives the default constructor
//@AllArgsConstructor  -->gives constructor using fields
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//	private LocalDate releaseDate;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date releaseDate;
	private Boolean productAvailable;
	private Integer stockQuantity;
	private String imageName;
	private String imageType;
	@Lob // the annotation is use to store the large object
	private byte[] imageData;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Product(Integer id, String name, String desc, String brand, BigDecimal price, String category,
			Date releaseDate, Boolean available, Integer quantity, String imageName, String imageType,
			byte[] imageData) {
		super();
		this.id = id;
		this.name = name;
		this.description = desc;
		this.brand = brand;
		this.price = price;
		this.category = category;
		this.releaseDate = releaseDate;
		this.productAvailable = available;
		this.stockQuantity = quantity;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageData = imageData;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getProductAvailable() {
		return productAvailable;
	}
	public void setProductAvailable(boolean available) {
		this.productAvailable = available;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int quantity) {
		this.stockQuantity = quantity;
	}
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desc=" + description + ", brand=" + brand + ", price=" + price
				+ ", category=" + category + ", releaseDate=" + releaseDate + ", available=" + productAvailable + ", quantity="
				+ stockQuantity + ", imageName=" + imageName + ", imageType=" + imageType + ", imageData="
				+ Arrays.toString(imageData) + "]";
	}
	

}
