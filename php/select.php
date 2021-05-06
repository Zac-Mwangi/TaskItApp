<?php
include 'config.php';
$stmt = $con->prepare("SELECT fullname, email, phone,service,location FROM users ORDER BY location");
 //executing the query 
 $stmt->execute();
  //binding results to the query 
 $stmt->bind_result($fullname, $email, $phone,$service,$location);
 $users = array(); 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['fullname'] = $fullname; 
 $temp['email'] = $email; 
 $temp['phone'] = $phone; 
// $temp['service'] = $service;
 //service string
$conn=$dbh->prepare("SELECT * FROM sys_meta WHERE (meta_key='service' AND meta_id = ?)");
$conn->bindParam(1,$service);
$conn->execute();
	 if($conn->rowCount()!==0){
		$results=$conn->fetch(PDO::FETCH_OBJ);
		$service_string=$results->meta_value;
		$temp['service_string'] = $service_string; 
	}else{
		$temp['service_string'] = "Yoh bro rada hello my friend :-)"; 
	}
 //$temp['location'] = $location;
$conn=$dbh->prepare("SELECT * FROM sys_meta WHERE (meta_key='location' AND meta_id = ?)");
$conn->bindParam(1,$location);
$conn->execute();
	 if($conn->rowCount()!==0){
		$results=$conn->fetch(PDO::FETCH_OBJ);
		$location_string=$results->meta_value;
		$temp['location_string'] = $location_string; 
	}else{
		$temp['location_string'] = "Yoh bro rada hello my friend :-)"; 
	}
 array_push($users, $temp);
 }
 //displaying the result in json format 
 echo json_encode($users);
?>