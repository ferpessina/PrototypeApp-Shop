function getCategories() {
	$.getJSON("http://localhost:8080/api/products/categories", function(json){
		console.log(json);
		var list = $("#catTable");
		$.each(json, function(index, cat){
			list.append("<tr><td>"+cat.catId+"</td><td>"+cat.catName+"</td>\
				<td><button type=\"button\" onClick=\"editCat('"+cat.catId+"')\""+"\">View/Edit</button></td></tr>"
			);
		})
	}, "json");
}

function newCategory(){
	var frm = $("#catForm");
	var parsedForm = getFormData(frm);
	var formData = JSON.stringify(parsedForm);
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: formData,
		url: "http://localhost:8080/api/products/categories/create",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
	
	setTimeout(function() { refreshCategories(); }, 100);
}

function deleteCat(id){
	$.post( "http://localhost:8080/api/products/categories/delete", {catid: id} );
	setTimeout(function() { refreshCategories(); }, 100);
}

function editCat(id){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/CatEdit.html?catId="+id;
}

function back(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/index.html";
}

function refreshCategories(){
	var mainTableBody = $("#tableBody");
	mainTableBody.html("");
	getCategories();
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});

	return indexed_array;
};

$(function(){
	getCategories();
});