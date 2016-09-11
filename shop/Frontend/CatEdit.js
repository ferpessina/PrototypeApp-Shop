var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
var catId = getUrlParameter('catId');
console.log(catId);

function back(){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/CatManager.html";
}

$(function(){
	getProducts();
	getCategory();
});

function getCategory(){
	$.getJSON("http://localhost:8080/api/categories/"+catId, function(cat){
		console.log(cat);
		document.getElementById("catId").value = cat.catId;
		document.getElementById("catName").value = cat.catName;
		document.getElementById("catDescription").value = cat.catDescription;
		
	}, "json");
}

function getProducts() {
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
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/ViewProduct.html?catId="+catId+"&prodId="+prodId;
}