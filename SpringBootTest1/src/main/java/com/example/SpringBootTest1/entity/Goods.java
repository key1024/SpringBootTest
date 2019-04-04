package com.example.SpringBootTest1.entity;

import java.io.Serializable;

/**
 * 商品表
 * @author key
 *
 */
public class Goods implements Serializable {
	
	private Long id;//商品编号
	private String title;//商品名称
	private String price;//商品价格
	private String image;//商品图片
	private String brand;//商品品牌
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
