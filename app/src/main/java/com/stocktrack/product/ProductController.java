package com.stocktrack.product;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api")
public class ProductController {
  private final ProductRepository repo;
  public ProductController(ProductRepository repo){ this.repo = repo; }

  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }
  @GetMapping("/products") public List<Product> products(){ return repo.findAll(); }
}