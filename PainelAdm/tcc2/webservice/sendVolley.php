<?php 
	require "../db_credentials.php"; 

	$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket); 

	$sql = "SELECT * from essencia";

	$result = mysqli_query($conn,$sql);

	$json_array = array();

	while($row = mysqli_fetch_assoc($result)) 
	{
		$json_array[] = $row;
	}

	$array = json_encode($json_array);
	echo '{"essencia":'.$array.'}'; 

?>