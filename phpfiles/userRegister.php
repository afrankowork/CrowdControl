<?php 
require "conn.php";

$user_name = $_POST["user_name"];
$user_email = $_POST["email"];




$mysql_query = "Select * from userLogin where email = '$user_email'";
$query= "Insert into userLogin(name, email) Values ('$user_name', '$user_email')";

$result = mysqli_query($conn, $mysql_query);


if (mysqli_num_rows($result)>0) {

	echo "Welcome Back!";
	
	}

else  {

	mysqli_query($conn, $query);
	
	}

?>