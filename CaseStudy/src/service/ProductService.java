package service;

import model.Product;
import utils.FileUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductService  {

    private static final String PATH = "data/product.txt";

    public static ProductService productService;

    public ProductService() {

    }

    public static ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public double getPrice(long id) {
        return findById(id).getPrice();
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }







    public void add(Product newProduct) {
        List<Product> products = findAll();
        products.add(newProduct);
        FileUtils.writeFile(PATH, products);
    }


    public void removeById(long id) {
        List<Product> products = findAll();
        products.removeIf(product -> product.getIdProduct() == id);
        FileUtils.writeFile(PATH, products);
    }




    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getIdProduct() == newProduct.getIdProduct()) {
                product.setNameProduct(newProduct.getNameProduct());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
                product.setUpdateAt(Instant.now());
            }
        }
        FileUtils.writeFile(PATH, products);

    }


    public boolean existsById(long id) {
        return findById(id) != null;
    }


    public Product findById(long id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (id == product.getIdProduct()) {
                return product;
            }
        }
        return null;
    }


    public Product findProductById(long id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateQuantity(long id, int quantity) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    FileUtils.writeFile(PATH, products);
                    break;
                }
            }
        }
    }
}