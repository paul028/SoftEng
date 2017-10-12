<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class Register // for register
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

         function does_user_exist($username,$old_pass,$new_pass)
			{
				$query = "select * from user_account where password = '$old_pass' AND username='$username'";
				$result = mysqli_query($this->connection, $query);
				$row_cnt = $result->num_rows;

				if ($row_cnt > 0) {

          $query2 = "update user_account set password = '$new_pass' where username ='$username' ";
          $is_inserted = mysqli_query($this->connection, $query2);
          if($is_inserted)
          {
              $json['success'] = ''.$username;

          }
          else
          {

    $json['error'] = ' An error occured. Please check your internet ' ;

          }
         }
				else
				{
          $json['error'] = 'Wrong Old Password' ;
				}
				echo json_encode($json);
				mysqli_close($this->connection);
			}

	}
	$register = new Register();
	if(isset($_POST['username'],$_POST['old_pass'],$_POST['new_pass']))
	{
		$username = $_POST['username'];
		 $old_pass = $_POST['old_pass'];
		$new_pass = $_POST['new_pass'];
		if(!empty($username) && !empty($old_pass) && !empty($new_pass))
		{
			$register -> does_user_exist($username,$old_pass,$new_pass);
		}
		else
		{
			echo json_encode("Complete all textfield first!");
		}
	}
?>
