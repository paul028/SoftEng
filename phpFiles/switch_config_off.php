<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class SwitchOFF // query use to fetch all of your post on your own timeline
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function switch_off($app_name,$username)
		{
      $query = "select app_id from appliance where app_name ='$app_name'";
			$result = mysqli_query($this->connection, $query);
			$app_id = mysqli_fetch_row($result);

			$query4 = "select user_id from user_account where username ='$username'";
			$result3 = mysqli_query($this->connection, $query4);
			$user_id = mysqli_fetch_row($result3);

			$query3 ="insert into appliance_log(app_id,user_id,state)values ('$app_id[0]','$user_id[0]','OFF')";
			$result4 = mysqli_query($this->connection,$query3);

			$query2 ="update appliance set app_state =0 where app_id = '$app_id[0]'";
			$result2 = mysqli_query($this->connection,$query2);
			$json = array();
			$setmode17 = shell_exec("/usr/local/bin/gpio -g mode 17 out"); // set GPIO 17 as output 
				$gpio_on = shell_exec("/usr/local/bin/gpio -g write 17 0"); // turn off GPIO 17
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
	$switchon = new SwitchOFF();
	if(isset($_GET['app_name'],$_GET['username']))
	{
		$app_name = $_GET['app_name'];
		$username = $_GET['username'];
		$switchon -> switch_off($app_name,$username);
	}
?>
