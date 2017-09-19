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
      $query = "select firstname from user_account where username ='$username'";

			$result = mysqli_query($this->connection, $query);

            $row = mysqli_fetch_row($result);
      if(mysqli_num_rows($result) > 0)
            {
                    $json['success'] = ''.$row[0];
      }else
      {
      $json['error'] = ' Wrong username or password ';
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
