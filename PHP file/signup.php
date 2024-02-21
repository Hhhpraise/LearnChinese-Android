<?php
require "DataBase.php";
$db = new DataBase();
if($_SERVER['REQUEST_METHOD'] === 'POST'){
    if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])) {
        if ($db->dbConnect()) {
            if($db->uniqidEmail($_POST['email'])){
                if ($db->signUp("user", $_POST['name'], $_POST['email'], $_POST['password'])) { 
                    //return $db->jsonMessage("Signup", "Successfulvvvvvvv");
                }
                echo $db->jsonMessage("Signup", "Successful");
            }else  {
                echo $db->jsonMessage("Signup", "Email Alrady exist");
            }
             
             
        }  
    } else   echo $db->jsonMessage("Signup", "not");
 
     
}

?>
