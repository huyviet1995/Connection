<?php
	echo "Welcome to connect Login of the android app!";
	$server = 'server20.000webhost.com';
	$username = 'a4869167';
	$password = 'magmadragon';
	$db = 'a4869167_vietdan';
	$conn = mysqli_connect($server,$username,$password,$db);
	if (!$conn) 
		echo "Connection failed, try to reestablish the connection" . mysqli_connect_error();
	else
		echo "Connection successful";
	//define the variables;	
	$result='';
	$inputuser = $_POST['username'];
	$inputpass = $_POST['password'];
	$sql_username = "select * from androidConnectionTest where username = '$inputuser'";
	$sql_password = "select * from androidConnectionTest where password = '$inputpass'";
	$result_username = mysqli_query($conn,$sql_username);
	$result_password = mysqli_query($conn,$sql_password);
	$row_username = mysqli_fetch_array($result_username,MYSQLI_ASSOC);
	$row_password = mysqli_fetch_array($result_password,MYSQLI_ASSOC);
	$server_user = $row_username['username'];
	$server_pass = $row_password['password'];
	
	if (!$result_username || !$result_password) 
	{
		die ('Invalid username or password');
		$result = 'false';
		mysqli_close();
	}
	else {
		if ($result_username == $inputuser && $result_password = $inputpass) 
			$result ='true';
		else
			$result = 'false';
		
	}
	echo $result;
?>
