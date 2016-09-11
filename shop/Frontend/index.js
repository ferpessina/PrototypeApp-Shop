function showUsers() {
	$.getJSON("http://localhost:8080/api/users", function(json){
		console.log(json);
		var mainTableBody = $("#usersTable");
		$.each(json, function(index, user){
			var buttonStatus = "";
			if (!user.canBeDeleted) {
				buttonStatus = "disabled=true";						
			}else{
				buttonStatus = "";
			}
			mainTableBody.append("<tr><td><button type=\"button\" onClick=\"logAs('"+user.userId+"')\">It's me!</button></td>\
				<td>"+user.userName+"</td><td>"+user.userEmail+"</td>\
				<td><button type=\"button\" onClick=\"editUser('"+user.userId+"')\">Edit</button></td>\
				<td><button type=\"button\" "+buttonStatus+"onClick=\"deleteUser('"+user.userId+"')\""+"\">Delete</button></td></tr>"
			);
		});
	}, "json");	
}

function editUser(id){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/EditUser.html?userId="+id;
}

function cleanupReservations(){
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "http://localhost:8080/api/products/reservations/cleanup",
	}).then(function(data, status, jqxhr) {
	   $("#statusInformation").text(data);
	   console.log(jqxhr);
	});
}

function deleteUser(id){
	$.post( "http://localhost:8080/api/users/delete", {userid: id} );
	setTimeout(function() { refreshUsers(); }, 100);
}

function categoryManager(){
	window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/CatManager.html";
}

function refreshUsers(){
	var mainTableBody = $("#tableBody");
	mainTableBody.html("");
	showUsers();
}

function logAs(id){
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "http://localhost:8080/api/users/"+id+"/login",
	}).then(function(data, status, jqxhr) {
	   console.log(jqxhr);
	   window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/AsUser.html?userId="+id;
	});
}

function navigateToCreateUsers(){
    window.location = "file:///C:/Users/Fernando/Documents/GitHub/PrototypeApp-Shop/shop/Frontend/CreateUser.html";
}


function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};
	
	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});
	return indexed_array;
};

// Functions from Users

$(function(){
	showUsers();
});