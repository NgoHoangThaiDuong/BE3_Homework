<%-- 
    Document   : editProduct
    Created on : Jan 4, 2025, 2:58:28 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
</head>
<body>
    <h1>Edit Product</h1>
    
    <form action="product?action=edit-post&id=${product.id}" method="post">
        <div>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
        </div>
        <div>
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>
        </div>
        <div>
            <label for="productYear">Year:</label>
            <input type="number" id="productYear" name="productYear" value="${product.productYear}" required>
        </div>
        <div>
            <label for="image">Image URL:</label>
            <input type="text" id="image" name="image" value="${product.image}" required>
        </div>
        <div>
            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit">Update Product</button>
    </form>
</body>
</html>

