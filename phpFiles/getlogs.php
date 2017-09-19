<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewLogs // query use to fetch all of your post on your own timeline
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function openlogs()
		{
      $query = "select a.app_name, al.state, concat(ua.firstname,' ',ua.lastname) as name, al.log_time from appliance a
      join appliance_log al on(a.app_id=al.app_id) join user_account ua on(ua.user_id=al.user_id) order by log_time desc";

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
	$viewSwitch = new ViewLogs();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewSwitch -> openlogs();
	}
?>
