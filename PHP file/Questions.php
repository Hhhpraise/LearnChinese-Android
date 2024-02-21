<?php
require "DataBase.php";
$db = new DataBase();

 
    if (isset($_POST['id'])) {
        if ($db->dbConnect()) {
           $get = $db->getQuestion($_POST['id']); 
           echo  $get ;  
        }  
    } else   echo $db->jsonMessage("Questions", "Failed");
 
     
 
