<?php
require "conn.php";


$user_email = $_POST["identifier_loginEmail"];
$user_name = $_POST["identifier_loginName"]




$mysql_query = "Select * from userLogin where email = '$user_email'";

$result = mysqli_query($conn,$mysql_query);

if(mysqli_num_rows($result)>0) {
$row = mysqli_fetch_assoc($result);
$name = $row["name"];
$email = $row["email"];

echo "true,".$email.",".$name;

}

else
{echo "login unsuccessful";

} 




?>