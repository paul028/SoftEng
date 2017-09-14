<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewAlarms // query use to fetch all of your post on your own timeline
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function does_alarm_exist($username)
		{
      $query = "select a.app_name, a.app_state, a.app_description from appliance a join controllers c
on(a.app_id = c.app_id)
where a.appcat_id=3 and c.user_id = (select user_id from user_account where username ='$username')";

			$result = mysqli_query($this->connection, $query);
			$json = array();

			if(mysqli_num_rows($result))
			{
				while($row=mysqli_fetch_assoc($result))
				{
					$json[]=$row;

				}
			}
			else
			{
				$json['error'] = 'Please check your internet connection';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$viewSwitch = new ViewAlarms();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewSwitch -> does_alarm_exist($username);
	}
?>
