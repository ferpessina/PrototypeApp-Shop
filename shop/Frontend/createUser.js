function createUser() {
	var frm = $("#newUser");
	var parsedForm = getFormData(frm);
	var formData = JSON.stringify(parsedForm);
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: formData,
		url: "http://localhost:8080/api/users/create",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
};

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