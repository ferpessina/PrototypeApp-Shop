var userId = "";

$(function(){
	getProduct();
});
$(function(){
	getCategories();
});

var expanded = false;
function showCheckboxes() {
    var checkboxes = document.getElementById("checkboxes");
    if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
    } else {
        checkboxes.style.display = "none";
        expanded = false;
    }
}

var bidderId = "";
var parameter = window.location.search.replace("?", "");
var prodId = parameter.replace(/[^0-9]/g,'');

function getProduct() {
	$.getJSON("http://localhost:8080/api/products/"+prodId, function(product){
		console.log(product);
			userId = product.ownerId;
			document.getElementById("productId").value = product.productId;
			document.getElementById("productName").value = product.productName;
			document.getElementById("productDescription").value = product.productDescription;
			document.getElementById("productPrice").value = product.productPrice;
			document.getElementById("productState").value = product.productState;
			document.getElementById("productImage").value = product.productImage;
			document.getElementById("creationDate").value = product.creationDate;
			document.getElementById("creationDate").value = Date.parse(product.creationDate).toString("yyyy-MM-dd");
			bidderId = product.bidderId;
			if(bidderId != "" && bidderId != null){
				getBidder();
			}
	}, "json");
	setTimeout(function() { getProductCategories(prodId); }, 300);
}

function getBidder() {
	$.getJSON("http://localhost:8080/api/users/"+bidderId, function(user){
		console.log(user);
		document.getElementById("bidderName").innerHTML = "Reserved by: "+user.userName+
		"  <button type=\"button\" onClick=\"cancelReservation()\">Cancel Reservation</button>";
	}, "json");	
}

function cancelReservation(){
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "http://localhost:8080/api/products/"+prodId+"/rescancel",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
	
	document.getElementById("bidderName").innerHTML = "";
}

function getProductCategories(Id){
	$.getJSON("http://localhost:8080/api/products/"+Id+"/categories", function(json){
		console.log(json);
		$.each(json, function(index, catId){
			var catCheckboxes = document.getElementById("checkboxes").children;
			for(var i = 0; i < catCheckboxes.length; i++) {
			    if(catCheckboxes[i].children[0].value == catId.toString()){
			        catCheckboxes[i].children[0].checked = true;
			    }
			}
		});
	}, "json");
}

function editProduct(){
	var frm = $("#productForm");
	date = document.getElementById("creationDate").value;
	document.getElementById("creationDate").value = Date.parse(date).toString("yyyy-MM-dd");
	var parsedForm = getFormData(frm);
	var formData = JSON.stringify(parsedForm);
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: formData,
		url: "http://localhost:8080/api/products/update",
	}).then(function(data, status, jqxhr) {
	   console.log(jqxhr);
	   var prodId = document.getElementById("productId").value;
		//update categories
		setCategories(prodId);
	});
}

function setCategories(prodId){
	//get checked categories
	var catCheckboxes = document.getElementById("checkboxes").children;
	var cats = [];
	for(var i = 0; i < catCheckboxes.length; i++) {
	    if(catCheckboxes[i].children[0].checked == true){
	        cats.push(catCheckboxes[i].children[0].value);
	    }
	}
	console.log(cats);
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(cats),
		url: "http://localhost:8080/api/products/"+prodId+"/categories/update",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text("Product updated succesfully");
	   console.log(jqxhr);
	});
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});

	return indexed_array;
};

function getCategories() {
	$.getJSON("http://localhost:8080/api/products/categories", function(json){
		console.log(json);
		var list = $("#catList");
		var listString = "";
		$.each(json, function(index, category){
			listString += "<label><input type=\"checkbox\" value=\""+category.catId+"\"/>"+category.catName+"</label>";
		});
		document.getElementById('checkboxes').innerHTML = listString;
	}, "json");	
}

function goBack(){
window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/AsUser.html?userId="+userId;}