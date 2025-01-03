/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import utils.Database;
import java.sql.ResultSet;
import model.Category;

/**
 *
 * @author ASUS
 */
public class ProductDAO {

     public void insertUser(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Product(name, price, product_year, image, category_id) "
                + " VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getProductYear());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setInt(3, product.getCategory().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    public void updateProduct(Product product) throws ClassNotFoundException{
        String sql = "UPDATE product set name = ?, price = ?, product_year = ?, image = ?, category_id = ? where id = ?;";
         try (Connection connection = Database.getConnection(); 
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setString(1, product.getName());          
        preparedStatement.setFloat(2, product.getPrice());           
        preparedStatement.setInt(3, product.getProductYear());       
        preparedStatement.setString(4, product.getImage());          
        preparedStatement.setInt(5, product.getCategory().getId());  
        preparedStatement.setInt(6, product.getId());                
        System.out.println(preparedStatement); 
        preparedStatement.executeUpdate();     

    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    }

    public void deleteProduct(int productId) throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM Product WHERE id = ?";
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, productId); // ID của sản phẩm cần xóa
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("No product found with the given ID.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public List<Product> searchProducts(String categoryName, String productName, Float price) throws SQLException, ClassNotFoundException {
    List<Product> productList = new ArrayList<>();
    String sql = "SELECT p.id, p.name, p.price, p.product_year, p.image, p.category_id "
               + "FROM Product p "
               + "JOIN Category c ON p.category_id = c.id "
               + "WHERE 1=1 ";  
    
    // Tạo các điều kiện tìm kiếm dựa trên tham số
    if (categoryName != null && !categoryName.isEmpty()) {
        sql += "AND c.name LIKE ? ";
    }
    if (productName != null && !productName.isEmpty()) {
        sql += "AND p.name LIKE ? ";
    }
    if (price != null) {
        sql += "AND p.price = ? ";
    }

    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        int parameterIndex = 1;

        if (categoryName != null && !categoryName.isEmpty()) {
            preparedStatement.setString(parameterIndex++, "%" + categoryName + "%");
        }
        if (productName != null && !productName.isEmpty()) {
            preparedStatement.setString(parameterIndex++, "%" + productName + "%");
        }
        if (price != null) {
            preparedStatement.setFloat(parameterIndex++, price);
        }

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getFloat("price"));
            product.setProductYear(rs.getInt("product_year"));
            product.setImage(rs.getString("image"));

            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            category.setName(rs.getString("name"));  
            product.setCategory(category);

            productList.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productList;
}
      public List<Product> list() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, p.productYear, p.image, c.id as categoryId, c.name as categoryName FROM products p JOIN categories c ON p.categoryId = c.id";
        
        try (Connection connection = Database.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setProductYear(rs.getInt("productYear"));
                product.setImage(rs.getString("image"));

                Category category = new Category();
                category.setId(rs.getInt("categoryId"));
                category.setName(rs.getString("categoryName"));

                product.setCategory(category);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving product list", e);
        }

        return products;
    }
     public Product getById(int productId) throws SQLException, ClassNotFoundException {
    Product product = null;
    String sql = "SELECT p.id, p.name, p.price, p.product_year, p.image, p.category_id, c.name AS category_name "
               + "FROM Product p "
               + "JOIN Category c ON p.category_id = c.id "
               + "WHERE p.id = ?";
    
    try (Connection connection = Database.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getFloat("price"));
            product.setProductYear(rs.getInt("product_year"));
            product.setImage(rs.getString("image"));
            
            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            product.setCategory(category);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return product;
}
}
