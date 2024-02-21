<?php
require "DataBase.php";
$db = new DataBase();

if (isset($_POST['email']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("user", $_POST['email'], $_POST['password'])) { 
            $user = $db->getUser($_POST['email']);
            $data = [
            'msg' => "Successful",
            'data' => [
                    'id' => $user['id'],
                    'name' => $user['name'],
                    'email' => $user['email'],
                    ] 
            ];
            echo $db->jsonMessage("Login", $data);
        } else  echo $db->jsonMessage("Login", "Email or Password wrong");
    } else  echo $db->jsonMessage("Login", "Error: Database connection");
} else  echo $db->jsonMessage("Login", "All fields are required");
?>
