<?php
//$user_id = $_POST['user_id'];
$user_id = 2;

include 'config.php';
	$stmt = $con->prepare("SELECT date_posted,rate,feedback_by,feedback_text FROM feedback WHERE user_id = '$user_id' ORDER BY date_posted");
	$stmt->execute();
	$stmt->bind_result($date_posted, $rate, $feedback_by,$feedback_text);
		$comments = array(); 
		while($stmt->fetch()){
	 		$temp = array();
	 		if($feedback_text==""){

	 		}else{
					$conn=$dbh->prepare("SELECT * FROM users WHERE user_id=?");
					$conn->bindParam(1,$feedback_by);
					$conn->execute();
					if($conn->rowCount()!==0){
						$results=$conn->fetch(PDO::FETCH_OBJ);
						//we get both the username and user_id and password and salt
						$username=$results->username;
						$temp['date_posted'] = $date_posted; 
						$temp['rate'] = $rate; 
						$temp['feedback_by'] = $username;
						$temp['feedback_text'] = $feedback_text; 
						array_push($comments, $temp);
		}
echo json_encode($comments);
	}
	 
}

?>