package xml_parser_2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

  
  private static final String ALLCATEGORIES = "categories";
  private static final String CATEGORY = "category";
  private static final String SUBCATEGORY = "subcategory";
  private static final String PRODUCT = "product";
  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";
  private static final String PRICE = "price";
  private static final String RATING = "rating";

  public Stack<Category> categories;
  public Stack<Product> products;
  public Map<String, List<Product>> categoryProducts;
  public List<String> categoriesList = new LinkedList<>();
  public String currentValue;

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    currentValue = new String(ch, start, length);
  }

  @Override
  public void startDocument() throws SAXException {
    categories = new Stack<>();
    products = new Stack<>();
    categoryProducts = new HashMap<>();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    switch(qName) {
        case ALLCATEGORIES:
        case CATEGORY:
        case SUBCATEGORY: {
          Category category = new Category();
          String name = attributes.getValue("name");
          categoriesList.add(name);
          category.setName(name);
          categories.push(category);
          break;
        }
        case PRODUCT: {
          Product product = new Product();
          products.push(product);
          break;
        }
      }
    } 

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    switch (qName) {
      case NAME: {
        Product peekProduct = products.peek();
        peekProduct.setName(currentValue);
        break;
      }
      case DESCRIPTION: {
        Product peekProduct = products.peek();
        peekProduct.setDescription(currentValue);
        break;
      }
      case PRICE: {
        Product peekProduct = products.peek();
        peekProduct.setPrice(currentValue);
        break;
      }
      case RATING: {
        Product peekProduct = products.peek();
        peekProduct.setRating(currentValue);
        break;
      }
      case CATEGORY:
      case SUBCATEGORY: {
        Category currentCategory = categories.pop();
        Category peekCategory = categories.peek();
        if (products.size() > 0){
          List<Product> prod = new LinkedList<>();
          while (!products.isEmpty()){
            prod.add(products.pop());
          }
          currentCategory.setProducts(prod);
        }
        passProducts(currentCategory, peekCategory);
        categoryProducts.put(currentCategory.getName(), currentCategory.getProducts());
        break;
      }
      case ALLCATEGORIES: {
        Category currentCategory = categories.pop();
        categoryProducts.put(currentCategory.getName(), currentCategory.getProducts());
        break;
      }
    }
  }

  public void passProducts(Category currentCategory, Category peekCategory){
    List<Product> prod = currentCategory.getProducts();
    for(Product p : prod){
      peekCategory.addProduct(p);
    }
  }

  public List<Product> getProducts(String category) throws Exception {
    List<Product> products = categoryProducts.get(category);
    if(products == null){
      throw new Exception("No products available in that category");
    }
    return products;
  }
  public List<String> getCategoryList() {
    return categoriesList;
  }
}
