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
var userId = getUrlParameter('userId');
var prodId = getUrlParameter('prodId');
var catId = getUrlParameter('catId');

var ownerId = "";
var bidderId = "";

function back(){
	if(userId!=null){
		window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/Browse.html?userId="+userId;
	}else{
		window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/CatEdit.html?catId="+catId;
	}
}

$(function(){
	getAllInfo();
});
function getAllInfo(){
	if(userId == null){
		document.getElementById("reserveButton").hidden = true;
	}
	getProduct();
}

function getOwner() {
	$.getJSON("http://localhost:8080/api/users/"+ownerId, function(user){
		console.log(user);
		if(user.userId==userId){
			//disable reservation (it's my product)
			document.getElementById("reserveButton").disabled = true;
		}
		document.getElementById("ownerName").innerHTML  = "Owner: "+user.userName;
	}, "json");	
}

function getBidder() {
	$.getJSON("http://localhost:8080/api/users/"+bidderId, function(user){
		console.log(user);
		if(user.userId==userId){
			//enable reservation cancelling
			document.getElementById("bidderName").innerHTML = "Reserved by: "+user.userName+
			"  <button type=\"button\" onClick=\"cancelReservation()\">Cancel Reservation</button>";
		}else{
			document.getElementById("bidderName").innerHTML  = "Reserved by: "+user.userName;
		}
	}, "json");	
}

function reserveProduct(){
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(userId),
		url: "http://localhost:8080/api/products/"+prodId+"/reserve",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
	
	setTimeout(function() { refreshData(); }, 300);
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
	
	setTimeout(function() { refreshData(); }, 300);
}

function refreshData(){
	ownerId = "";
	bidderId = "";
	document.getElementById("bidderName").innerHTML = "";
	document.getElementById("reserveButton").disabled = false;
	getAllInfo();
}

function getProduct(){
	$.getJSON("http://localhost:8080/api/products/"+prodId, function(product){
		console.log(product);
			ownerId = product.ownerId;
			document.getElementById("productId").innerHTML = "ProductId: "+product.productId;
			document.getElementById("productName").innerHTML = "Product Name: "+product.productName;
			document.getElementById("productDescription").innerHTML = "Description: "+product.productDescription;
			document.getElementById("productPrice").innerHTML = "Price: "+product.productPrice;
			document.getElementById("productState").innerHTML = "State: "+product.productState;
			document.getElementById("productImage").text = product.productImage;
			document.getElementById("creationDate").innerHTML = "Published on: "+Date.parse(product.creationDate).toString("yyyy-MM-dd");
			getOwner();
			bidderId = product.bidderId;
			if(bidderId != "" && bidderId != null){
				document.getElementById("reserveButton").disabled = true;
				getBidder();
			}
	}, "json");
}