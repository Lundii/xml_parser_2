package xml_parser_2;

import java.util.LinkedList;
import java.util.List;

public class Category {
    String name;
    List<Product> products;

    public Category(){
      this("", new LinkedList<>());
    }

    public Category(String name, List<Product> products){
      this.name = name;
      this.products = products;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<Product> getProducts() {
      return products;
    }

    public void setProducts(List<Product> products) {
      this.products = products;
    }

    public void addProduct(Product product){
      this.products.add(product);
    }
}