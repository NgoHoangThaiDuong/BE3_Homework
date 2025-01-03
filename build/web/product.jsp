
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        .product-list {
            margin-top: 20px;
        }
        .product-item {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
        }
        .product-item .details {
            display: flex;
            justify-content: space-between;
        }
        .product-item .details span {
            margin-right: 15px;
        }
        .action-btns a {
            margin-right: 10px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
    <h1>Product List</h1>
    
    <!-- Button to add new product -->
    <a href="product?action=insert">
        <button>Add New Product</button>
    </a>
    
    <div class="product-list">
        <c:forEach var="product" items="${products}">
            <div class="product-item">
                <div class="details">
                    <span><strong>ID:</strong> ${product.id}</span>
                    <span><strong>Name:</strong> ${product.name}</span>
                    <span><strong>Price:</strong> ${product.price}</span>
                    <span><strong>Year:</strong> ${product.productYear}</span>
                    <span><strong>Category:</strong> ${product.category.name}</span>
                </div>
                <div class="action-btns">
                    <a href="product?action=edit&id=${product.id}">Edit</a>
                    <a href="product?action=delete&id=${product.id}" onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>