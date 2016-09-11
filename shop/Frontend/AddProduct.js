var Id = window.location.search.replace("?", "").replace(/[^0-9]/g,'');

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

function saveProduct(){
	var frm = $("#productForm");
	document.getElementById("ownerId").value  = Id;
	var parsedForm = getFormData(frm);
	var formData = JSON.stringify(parsedForm);
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: formData,
		url: "http://localhost:8080/api/products/create",
	}).then(function(data, status, jqxhr) {
		if(data==""){
			$("#statusInformation").text("Please complete all fields");
			console.log(jqxhr);
		}else{
	   		$("#statusInformation").text("Product added succesfully");
	   		console.log(jqxhr);
	   		setCategories(data);
	   	}
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
		url: "http://localhost:8080/api/products/"+prodId+"/categories/add",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text("Product added succesfully");
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

function goBack(){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/AsUser.html?userId="+Id;
}

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
