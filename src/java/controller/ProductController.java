/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CaterogyDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private CaterogyDAO categoryDAO = new CaterogyDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String action = request.getParameter("action");

        switch (action == null ? "default" : action) {
            case "edit":
                int productIdEdit = Integer.parseInt(request.getParameter("id"));
                Product product = productDAO.getById(productIdEdit);
                List<Category> categories = categoryDAO.list();
                request.setAttribute("product", product);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
                break;

            case "delete":
               
                int productIdDelete = Integer.parseInt(request.getParameter("id"));
                productDAO.deleteProduct(productIdDelete);
                response.sendRedirect(request.getContextPath() + "/product");
                break;

            case "edit-post":
                
                int productIdPost = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                float price = Float.parseFloat(request.getParameter("price"));
                int productYear = Integer.parseInt(request.getParameter("productYear"));
                String image = request.getParameter("image");
                int categoryId = Integer.parseInt(request.getParameter("category"));

                Product productToUpdate = new Product();
                productToUpdate.setId(productIdPost);
                productToUpdate.setName(name);
                productToUpdate.setPrice(price);
                productToUpdate.setProductYear(productYear);
                productToUpdate.setImage(image);
                Category category = new Category();
                category.setId(categoryId);
                productToUpdate.setCategory(category);

                productDAO.updateProduct(productToUpdate);
                response.sendRedirect(request.getContextPath() + "/product");
                break;

            case "insert":
               
                List<Category> categoriesForInsert = categoryDAO.list();
                request.setAttribute("categories", categoriesForInsert);
                request.getRequestDispatcher("/insertProduct.jsp").forward(request, response);
                break;

            case "insert-post":
           
                String nameInsert = request.getParameter("name");
                float priceInsert = Float.parseFloat(request.getParameter("price"));
                int productYearInsert = Integer.parseInt(request.getParameter("productYear"));
                String imageInsert = request.getParameter("image");
                int categoryIdInsert = Integer.parseInt(request.getParameter("category"));

                Product newProduct = new Product();
                newProduct.setName(nameInsert);
                newProduct.setPrice(priceInsert);
                newProduct.setProductYear(productYearInsert);
                newProduct.setImage(imageInsert);
                Category categoryInsert = new Category();
                categoryInsert.setId(categoryIdInsert);
                newProduct.setCategory(categoryInsert);

                productDAO.insertUser(newProduct); // Gọi DAO để thêm sản phẩm vào DB
                response.sendRedirect(request.getContextPath() + "/product");
                break;

            case "default":
            default:
                List<Product> products = productDAO.list();
                request.setAttribute("products", products);
                request.getRequestDispatcher("/product.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

