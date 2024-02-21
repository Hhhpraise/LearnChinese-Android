<?php
require "DataBase.php";
$db = new DataBase();

if ($db->dbConnect()) {
    $get = $db->loadPosts(); 
    echo  var_dump(json_encode($get));  
 }  
 else   echo $db->jsonMessage("Post", "Failed");
   
 ?>