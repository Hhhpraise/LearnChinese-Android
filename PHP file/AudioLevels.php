<?php
require "DataBase.php";
$db = new DataBase();

if ($db->dbConnect()) {
    $get = $db->loadALevels(); 
    echo  var_dump(json_encode($get));  
 }  
 else   echo $db->jsonMessage("Levels", "Failed");
   
 ?>
