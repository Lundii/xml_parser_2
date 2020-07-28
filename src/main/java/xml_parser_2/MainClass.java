
package xml_parser_2;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainClass {
    public static void main(String[] args) {
        XMLHandler xmlhandler = new XMLHandler();

        try {
            
           URL url = MainClass.class.getResource("products.xml");
           File inputFile = new File(url.getPath());
        
           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser saxParser = factory.newSAXParser();
           saxParser.parse(inputFile, xmlhandler);       
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            List<String> categories = xmlhandler.getCategoryList();
            System.out.println("Categories: ");
            for(String category: categories ){
                System.out.println(category);
            }
            System.out.println();

            String cate = "Android Phones";
            List<Product> products = xmlhandler.getProducts(cate);
            System.out.println(cate);

            for (Product product: products){
                System.out.printf("  Name:  %s%n  Desciption:  %s%n  Price: %s%n  Rating: %s%n%n", 
                                    product.getName(), 
                                    product.getDescription(), 
                                    product.getPrice(), 
                                    product.getRating());
            }
        } catch (Exception e){
            e.printStackTrace();;
        }

    }
    
}
