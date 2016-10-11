<?php
	echo "Welcome to connect Login of the android app!";
	$server = 'server20.000webhost.com';
	$username = 'a4869167';
	$password = 'magmadragon';
	$db = 'a4869167_vietdan';
	$conn = mysqli_connect($server,$username,$password,$db);
	if (!$conn) 
		echo "Connection failed" . mysqli_connect_error();
	else
		echo "Connection successful";
	//define the variables;	
	$username =$_POST['username'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	$address = $_POST['address'];
	$result = array();
	$sql = "insert into androidConnectionTest (username,password,email,address) values ('$username','$password','$email','$address')";
	if (mysqli_query($conn,$sql)) 
		$result = 'true';
	else
		$result = 'false';
	echo $result;
?>
