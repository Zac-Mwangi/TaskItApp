<?php
$feedback_by = $_POST['feedback_by'];
$rating = $_POST['rating'];
$user_id = $_POST['user_id'];
$feedback_text = $_POST['feedback_text'];

$output=array();
include ('config.php');

$conn=$dbh->prepare('INSERT INTO feedback (user_id,feedback_by,rate,feedback_text) VALUES (?,?,?,?)');
$conn->bindParam(1,$user_id);
$conn->bindParam(2,$feedback_by);
$conn->bindParam(3,$rating);
$conn->bindParam(4,$feedback_text);
$conn->execute();

if($conn->rowCount() == 0){
	$output['error'] = true;
	$output['message'] = "failed, Please try again";
}
else{
	$output['error'] = false;
	$output['message'] = "Thanks for your feedback";
}
echo json_encode($output);
?>

