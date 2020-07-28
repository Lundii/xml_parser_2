package xml_parser_2;

public class Product {
  private String name;
  private String description;
  private String price;
  private String rating;

  public Product(){
    this("", "", "", "");
  }
  public Product(String name, String description, String price, String rating){
    this.name = name;
    this.description = description;
    this.price = price;
    this.rating = rating;
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

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }
}