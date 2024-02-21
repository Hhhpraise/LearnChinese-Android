<?php
require "DataBase.php";
$db = new DataBase();

 
    if (isset($_POST['id'])) {
        if ($db->dbConnect()) {
           $get = $db->getCount($_POST['id']); 
           echo  $get ;  
        }  
    } else   echo $db->jsonMessage("Count", "Failed");