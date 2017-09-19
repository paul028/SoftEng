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

         function does_user_exist($firstname,$lastname,$email_address,$username,$password)
			{
				$query = "select * from user_account where email_address = '$email_address' OR username='$username'";
				$result = mysqli_query($this->connection, $query);
				$row_cnt = $result->num_rows;

				if ($row_cnt > 0) {
				   $json['error'] = 'Email or Username already in use.' ;}
				else
				{
					$query2 = "insert into user_account(firstname,lastname,username,password,email_address,role) VALUES ('$firstname','$lastname','$username','$password','$email_address','user')";
					$is_inserted = mysqli_query($this->connection, $query2);
					if($is_inserted == 1)
					{
						$json['success'] = ''.$firstname;
					}
					else
					{
						$json['error'] = ' An error occured. Please check your internet ' ;
					}
				}
				echo json_encode($json);
				mysqli_close($this->connection);
			}

	}
	$register = new Register();
	if(isset($_POST['firstname'],$_POST['lastname'],$_POST['emailaddress'],$_POST['username'],$_POST['password']))
	{
		$firstname = $_POST['firstname'];
		 $lastname = $_POST['lastname'];
		$email_address = $_POST['emailaddress'];
		$username = $_POST['username'];
		$password = $_POST['password'];

		if(!empty($firstname) && !empty($lastname) && !empty($email_address)  && !empty($username) && !empty($password))
		{
			$register -> does_user_exist($firstname,$lastname,$email_address,$username,$password);
		}
		else
		{
			echo json_encode("Complete all textfield first!");
		}
	}
?>
