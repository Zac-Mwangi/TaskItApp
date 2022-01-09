<?php
//$service=$_POST['service'];
$service="plumber";
include 'config.php';
$conn=$dbh->prepare("SELECT * FROM sys_meta WHERE (meta_key='service' AND meta_value = ?)");
$conn->bindParam(1,$service);
$conn->execute();
	 if($conn->rowCount()!==0){
		$results=$conn->fetch(PDO::FETCH_OBJ);
		$meta_id=$results->meta_id;
		//echo($meta_id);
			$stmt = $con->prepare("SELECT fullname, email, phone,location,user_id FROM users WHERE service = '$meta_id' ORDER BY location");
			$stmt->execute();
			$stmt->bind_result($fullname, $email, $phone,$location,$user_id);
	 		$users = array(); 
	 		while($stmt->fetch()){
			 		$temp = array();
	 				$temp['fullname'] = $fullname; 
	 				$temp['email'] = $email; 
	 				$temp['phone'] = $phone;
	 				$temp['user_id'] = $user_id; 
	 				//$temp['location']=$location;
	 				//finding location
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
	}
 echo json_encode($users);
?>