$(function(){
	getUser();
});

$(function(){
	showUserProducts();
});


var Id = window.location.search.replace("?", "").replace(/[^0-9]/g,'');
	
function getUser() {
	$.getJSON("http://localhost:8080/api/users/"+Id, function(user){
		console.log(user);
			var text = "You are logged in as "+user.userName;
			document.getElementById("statusInformation").innerHTML  = text;
	}, "json");	
}

function showUserProducts() {
	$.getJSON("http://localhost:8080/api/users/"+Id+"/products", function(json){
		console.log(json);
		var mainTableBody = $("#productsTable");
		$.each(json, function(index, product){
			buttonStatus="";
			mainTableBody.append("<tr><td>"+product.productId+"</td><td>"+product.productName+"</td><td>"+product.productPrice+"</td><td>"+product.productState+"</td>\
				<td><button type=\"button\" onClick=\"editProduct('"+product.productId+"')\">View/Edit</button></td>\
				<td><button type=\"button\" "+buttonStatus+"onClick=\"deleteProduct('"+product.productId+"')\""+"\">Delete</button></td></tr>"
			);
		});
	}, "json");	
}

function navigateToAddProduct(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/AddProduct.html?userId="+Id;
}

function browseProducts(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/Browse.html?userId="+Id;
}

function logout(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/index.html";
}

function editProduct(id){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/EditProduct.html?productId="+id;
}

function deleteProduct(id){
	$.post( "http://localhost:8080/api/products/delete", {productid: id} );
	setTimeout(function() { refreshProducts(); }, 300);
}

function refreshProducts(){
	var mainTableBody = $("#tableBody");
	mainTableBody.html("");
	showUserProducts();
}