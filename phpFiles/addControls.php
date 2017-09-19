<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class SwitchON // query use to fetch all of your post on your own timeline
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function switch_on($app_name,$username)
		{
      $query = "select app_id from appliance where app_name ='$app_name'";
			$result = mysqli_query($this->connection, $query);
			$app_id = mysqli_fetch_row($result);

			$query4 = "select user_id from user_account where username ='$username'";
			$result3 = mysqli_query($this->connection, $query4);
			$user_id = mysqli_fetch_row($result3);

			$query3 ="insert into controllers(app_id,user_id)values ('$app_id[0]','$user_id[0]')";
			$result4 = mysqli_query($this->connection,$query3);

			if($result2)
			{

				$json['success'] = 'Control Added Successfully';
			}
			else
			{
				$json['error'] = 'Please check your internet connection';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$switchon = new SwitchON();
	if(isset($_GET['app_name'],$_GET['username']))
	{
		$app_name = $_GET['app_name'];
		$username = $_GET['username'];
		$switchon -> switch_on($app_name,$username);
	}
?>
