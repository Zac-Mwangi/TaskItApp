<?php
$user_id = $_POST['user_id'];
//$user_id = 2;
$output=array();
include ('config.php');

$conn=$dbh->prepare('SELECT * FROM feedback WHERE user_id =?');
$conn->bindParam(1,$user_id);
$conn->execute();

if($conn->rowCount() !==0){
	$sth = $dbh->prepare("SELECT count(*) as total from feedback WHERE user_id = '$user_id'");
	$sth->execute();
	//print_r($sth->fetchColumn());
	$sth2 = $dbh->prepare("SELECT avg(rate) as average_rate FROM feedback WHERE user_id = '$user_id'");
	$sth2->execute();
	//print_r($sth2->fetchColumn());
	$output['total'] = $sth->fetchColumn();
	$output['average'] = number_format($sth2->fetchColumn(), 1);
}else{
	$output['total'] = 0;
	$output['average'] = 0;
}
echo json_encode($output);
?>