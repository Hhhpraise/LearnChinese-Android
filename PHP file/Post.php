<?php
require "DataBase.php";
$db = new DataBase();

 if($_SERVER['REQUEST_METHOD'] === 'POST'){
    if (isset($_POST['userName']) && isset($_POST['postDesc']) && isset($_POST['postDate']) ) {
        if ($db->dbConnect()) {
            if ($db->postUp("post_tb", $_POST['userName'], $_POST['postDesc'], $_POST['postDate']) ){ 
                //return $db->jsonMessage("Signup", "Successfulvvvvvvv");
                echo $db->jsonMessage("Posts", "Successful");
            }
            echo $db->jsonMessage("Posts", "Successful");
         }  
         else   echo $db->jsonMessage("Posts", "Failed");
    }
 }
 


 ?>
