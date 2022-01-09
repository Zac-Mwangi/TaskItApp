<?php
$username = $_POST['username'];
$password = $_POST['password'];

// $username = "lenox";
// $password = "123";

$output = array();
//requires database connection

include 'config.php';
$password = md5($password);
$conn=$dbh->prepare("SELECT * FROM users WHERE username=? AND password = ?");
$conn->bindParam(1,$username);
$conn->bindParam(2,$password);
$conn->execute();
if($conn->rowCount() == 0){
	$output['error'] = true;
	$output['message'] = "Wrong Credentials";
}//checking password correctness
if($conn->rowCount() !==0){
	$results=$conn->fetch(PDO::FETCH_OBJ);
	//we get both the username and user_id and password and salt
	$username=$results->username;
	$userID = $results->user_id;

	$output['error'] = false;
	$output['message'] = "Login Success";
	$output['username'] = $username;
	$output['userID'] = $userID;
}
echo json_encode($output);

?>