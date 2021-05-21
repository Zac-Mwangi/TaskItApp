<?php
$loginUsername = $_POST['loginUsername'];
$loginPassword = $_POST['loginPassword'];
$loginPhoneNumber = $_POST['loginPhoneNumber'];
	/*
$loginUsername = "yoh";
$loginPassword = "yeh";
$loginPhoneNumber = "0785012545";*/
$output=array();
include ('config.php');
$loginPassword = md5($loginPassword);

$conn=$dbh->prepare('INSERT INTO users (fullname,username,phone,password) VALUES (?,?,?,?)');
$conn->bindParam(1,$loginUsername);
$conn->bindParam(2,$loginUsername);
$conn->bindParam(3,$loginPhoneNumber);
$conn->bindParam(4,$loginPassword);
$conn->execute();

if($conn->rowCount() == 0){
	$output['error'] = true;
	$output['message'] = "failed, Please try again";
}
else{
	$output['error'] = false;
	$output['message'] = "Successfull";
}
echo json_encode($output);

?>