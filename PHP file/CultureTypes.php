<?php
require "DataBase.php";
$db = new DataBase();

if ($db->dbConnect()) {
    $get = $db->loadCultureType();
    echo  var_dump(json_encode($get));  
 }  
 else   echo $db->jsonMessage("Cultures", "Failed");
   
   

 ?>