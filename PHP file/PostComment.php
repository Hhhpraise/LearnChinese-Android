<?php
require "DataBase.php";
$db = new DataBase();

 if($_SERVER['REQUEST_METHOD'] === 'POST'){
    if (isset($_POST['post_id']) && isset($_POST['user_name']) && isset($_POST['txt']) && isset($_POST['time']) ) {
        if ($db->dbConnect()) {
            if ($db->commentUp("comment_tb", $_POST['post_id'],$_POST['user_name'],$_POST['txt'],$_POST['time']) ){ 
                //return $db->jsonMessage("Signup", "Successfulvvvvvvv");
                echo $db->jsonMessage("Comment", "Successful");
            }
            echo $db->jsonMessage("Comment", "Successful");
         }  
         else   echo $db->jsonMessage("Comment", "Failed");
    }
 }
 


 ?>
