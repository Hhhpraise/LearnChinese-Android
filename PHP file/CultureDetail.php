<?php
require "DataBase.php";
$db = new DataBase();

 
    if (isset($_POST['id'])) {
        if ($db->dbConnect()) {
           $get = $db->getDetail($_POST['id']); 
           echo  $get ;  
        }  
    } else   echo $db->jsonMessage("Details", "Failed");