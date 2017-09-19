<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewSwitch // query use to fetch all of your post on your own timeline
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function does_switch_exist($username)
		{
      $query = "select app_name, app_state, app_description from appliance where app_id not in ( select app_id from controllers where user_id = (select user_id from user_account where username ='$username'))";

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
	$viewSwitch = new ViewSwitch();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewSwitch -> does_switch_exist($username);
	}
?>
