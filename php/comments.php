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
				$temp['date_posted'] = $date_posted; 
				$temp['rate'] = $rate; 
				$temp['feedback_by'] = $feedback_by;
				$temp['feedback_text'] = $feedback_text; 
				array_push($comments, $temp);
		}	
 echo json_encode($comments);
?>