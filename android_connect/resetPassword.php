<?php
	$server = 'mysql4.000webhost.com';
	$username = 'a4869167_vietdan';
	$password = 'magmadragon';
	$db = 'a4869167_vietdan';
	$conn = mysqli_connect($server,$username,$password,$db);
	$username = $_POST['username'];
	$sql = "select * from androidConnectionTest where username = '$username'";
	$result = mysqli_query($conn,$sql);
	$num_rows = mysqli_num_rows($result);
	if ($num_rows > 0) {
		$row_username = mysqli_fetch_array($result,MYSQLI_ASSOC);
		$server_password = $row_username['password'];
		echo $server_password;
	}
	else {
		echo $serverpassowrd. " does not exist";
	}
	
?>
