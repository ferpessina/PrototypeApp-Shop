var Id = "";
function getUser() {
	var parameter = window.location.search.replace("?", "");
	Id = parameter.replace(/[^0-9]/g,'');
	$.getJSON("http://localhost:8080/api/users/"+Id, function(user){
		console.log(user);
			document.getElementById("userId").value = user.userId;
			document.getElementById("userName").value = user.userName;
			document.getElementById("userEmail").value = user.userEmail;
			document.getElementById("firstName").value = user.firstName;
			document.getElementById("lastName").value = user.lastName;
			var date = Date.parse(user.birthDate).toString("yyyy-MM-dd");
			document.getElementById("birthDate").value = date;
	}, "json");	
}

function editUser(){
	var frm = $("#editUser");
	date = document.getElementById("birthDate").value;
	document.getElementById("birthDate").value = Date.parse(date).toString("yyyy-MM-dd");
	var parsedForm = getFormData(frm);
	var formData = JSON.stringify(parsedForm);
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: formData,
		url: "http://localhost:8080/api/users/update",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
}

function showUserProducts() {
	$.getJSON("http://localhost:8080/api/users/"+Id+"/products", function(json){
		console.log(json);
		var mainTableBody = $("#productsTable");
		$.each(json, function(index, product){
			buttonStatus="";
			mainTableBody.append("<tr><td>"+product.productId+"</td><td>"+product.productName+"</td><td>"+product.productPrice+"</td><td>"+product.productState+"</td></tr>"
			);
		});
	}, "json");	
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});

	return indexed_array;
};

function goBack(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/index.html";
}

$(function(){
	getUser();
	showUserProducts();
});