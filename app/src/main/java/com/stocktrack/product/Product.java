package com.stocktrack.product;
import jakarta.persistence.*;

@Entity @Table(name="products")
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double price;
  @Column(name="on_sale")
  private Boolean onSale;

  // JPA requires a no-args constructor
  protected Product() {}

  // Convenience constructor
  public Product(String name, Double price, Boolean onSale) {
    this.name = name;
    this.price = price;
    this.onSale = onSale;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Double getPrice() { return price; }
  public void setPrice(Double price) { this.price = price; }

  public Boolean getOnSale() { return onSale; }
  public void setOnSale(Boolean onSale) { this.onSale = onSale; }
}