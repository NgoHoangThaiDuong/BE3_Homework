
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert New Product</title>
</head>
<body>
    <h1>Insert New Product</h1>
    
    <form action="product?action=insert-post" method="post">
        <div>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" required>
        </div>
        <div>
            <label for="productYear">Year:</label>
            <input type="number" id="productYear" name="productYear" required>
        </div>
        <div>
            <label for="image">Image URL:</label>
            <input type="text" id="image" name="image" required>
        </div>
        <div>
            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit">Add Product</button>
    </form>
</body>
</html>

