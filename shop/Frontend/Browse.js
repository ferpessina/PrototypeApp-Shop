var userId = window.location.search.replace("?", "").replace(/[^0-9]/g,'');
	
function getUser() {
	$.getJSON("http://localhost:8080/api/users/"+userId, function(user){
		console.log(user);
			var text = "You are logged in as "+user.userName;
			document.getElementById("userInfo").innerHTML  = text;
	}, "json");	
}

function getCategories() {
	$.getJSON("http://localhost:8080/api/products/categories", function(json){
		console.log(json);
		var list = $("#catList");
		var listString = "";
		$.each(json, function(index, category){
			document.getElementById('catList').options.add(new Option(category.catName,category.catId));
		});
	}, "json");	
}

function refreshProducts(catId){
	var mainTableBody = $("#tableBody");
	mainTableBody.html("");
	getProducts(catId);
}

function getProducts(catId) {
	if(catId != ""){
		$.getJSON("http://localhost:8080/api/products/categories/"+catId, function(json){
			console.log(json);
			var mainTableBody = $("#productsTable");
			$.each(json, function(index, product){
				buttonStatus="";
				mainTableBody.append("<tr><td>"+product.productId+"</td><td>"+product.productName+"</td><td>"+product.productPrice+"</td><td>"+product.productState+"</td>\
					<td><button type=\"button\" onClick=\"viewProduct('"+product.productId+"')\">View</button></td></tr>"
				);
			});
		}, "json");	
	}
}

function viewProduct(prodId){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/ViewProduct.html?userId="+userId+"&prodId="+prodId;
}

function back(){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/AsUser.html?userId="+userId;
}

$(function(){
	getCategories();
});
$(function(){
	getUser();
});