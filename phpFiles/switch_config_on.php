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

			$query5 = "select gpio from appliance where app_name ='$app_name'";
			$result5 = mysqli_query($this->connection, $query5);
			$gpio = mysqli_fetch_row($result5);

			$query4 = "select user_id from user_account where username ='$username'";
			$result3 = mysqli_query($this->connection, $query4);
			$user_id = mysqli_fetch_row($result3);

			$query3 ="insert into appliance_log(app_id,user_id,state)values ('$app_id[0]','$user_id[0]','ON')";
			$result4 = mysqli_query($this->connection,$query3);

			$query2 ="update appliance set app_state =1 where app_id = '$app_id[0]'";
			$result2 = mysqli_query($this->connection,$query2);
			$json = array();
			$setmode17 = shell_exec("/usr/local/bin/gpio -g mode '$gpio[0]' out");
				$gpio_on = shell_exec("/usr/local/bin/gpio -g write '$gpio[0]' 1");
			if($result2)
			{

				$json['success'] = 'Turn Off Successfully';
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
