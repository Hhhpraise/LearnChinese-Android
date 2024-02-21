<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $email, $password)
    {
        $email = $this->prepareData($email);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where email = '" . $email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['email'];
            $dbpassword = $row['password'];
            if ($dbusername == $email && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $name, $email, $password)
    {
        $name = $this->prepareData($name);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (name, email, password) VALUES ('" . $name . "','" . $email . "','"  . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            json_encode(true);
        } else return json_encode(false);
    }


    public function postUp($table, $userName, $postDesc,$postDate)
    {
        $userName = $this->prepareData($userName);
        $postDesc = $this->prepareData($postDesc);
        $postDate = $this->prepareData($postDate);
    
        $this->sql =
            "INSERT INTO " . $table . " (userName, postDesc,postDate) VALUES ('" . $userName . "','" . $postDesc . "','"  . $postDate. "')";
        if (mysqli_query($this->connect, $this->sql)) {
            json_encode(true);
        } else return json_encode(false);
    }

    
    public function commentUp($table, $post_id, $user_name,$txt,$time)
    {
        $post_id = $this->prepareData($post_id);
        $user_name = $this->prepareData($user_name);
        $txt = $this->prepareData($txt);
        $time = $this->prepareData($time);
      
    
        $this->sql =
            "INSERT INTO " . $table . " (post_id, user_name,txt,time) VALUES ('" . $post_id . "','" . $user_name . "','" . $txt . "','"  . $time. "')";
        if (mysqli_query($this->connect, $this->sql)) {
            json_encode(true);
        } else return json_encode(false);
    }


    public function getUser($email){
        $email = $this->prepareData($email);

        $this->sql = "select id,email,name from user where email = '".$email."'";
        $result = mysqli_query($this->connect, $this->sql);
        $user = $result->fetch_assoc(); 
        return $user; 
        
    }
    public function uniqidEmail($email){
        $email = $this->prepareData($email);

        $this->sql = "select email from user where email = '".$email."'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if(empty($row)){
            return  true;
        }
        return  false;
    }

    public function jsonMessage($action, $data){

        $data = [
            "data" => $data,
            "from" => $action,
        ];

        return json_encode($data);
    }


    public function getQuestion($levelId){
        $levelId = $this->prepareData($levelId);
        $tq= "select * from questions where level_id = '".$levelId."'";
        $result = mysqli_query($this->connect, $tq);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 

        $arr = [];

        foreach ($rows as $key => $value) { 
           $quuery = "select * from answers where q_id='".$value["id"]."'";
           $resu = mysqli_query($this->connect, $quuery);
           $items = mysqli_fetch_all($resu,MYSQLI_ASSOC);
           $ar = [];
           foreach($items as $key => $item){
            $ar[] = [
                "A" => $item["A"],
                "B" => $item["B"],
                "C" => $item["C"],
                "D" => $item["D"],
                "answer" => $item["q_ans"],
            ];
           }
           $arr[] = [
            'question' => $value['question'],
            'options' =>  $ar 
           ];
        }

        if(!empty($arr)){ 
            return $this->jsonMessage("Questions", json_decode(json_encode($arr)));
        }
        return $this->jsonMessage("Questions", "empty");
    }


    public function getDetail($cultureId){
        $cultureId = $this->prepareData($cultureId);
        $jb = "select text from culture_details  where culture_id='".$cultureId."'";
        $rest = mysqli_query($this->connect, $jb);
        $rws = mysqli_fetch_all($rest, MYSQLI_ASSOC); 
        if(!empty($rws)){
            return $this-> jsonMessage("Details",json_decode(json_encode($rws)));
        }
        return $this->jsonMessage("Details", "empty");
    }

    public function getCount($post_id){
        $post_id = $this->prepareData($post_id);
        $jb = "SELECT count(post_id) FROM comment_tb where post_id ='".$post_id."'";
        $rest = mysqli_query($this->connect, $jb);
        $rws = mysqli_fetch_all($rest, MYSQLI_ASSOC); 
        if(!empty($rws)){
            return $this-> jsonMessage("Count",json_decode(json_encode($rws)));
        }
        return $this->jsonMessage("Count", "empty");
    }


    public function getAudioQuestion($levelId){
        $levelId = $this->prepareData($levelId);
        $qq= "SELECT * FROM `audio_question` where level_id = '".$levelId."'";
        $result = mysqli_query($this->connect, $qq);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        $drr = [];
        foreach ($rows as $key => $value) { 
           $quury = "SELECT * FROM `audio_answer` where `question_id`='".$value["id"]."'";
           $res = mysqli_query($this->connect, $quury);
           $items = mysqli_fetch_all($res,MYSQLI_ASSOC);
           $dr = [];
           foreach($items as $key => $item){
            $dr[] = [
                "A" => $item["A"],
                "B" => $item["B"],
                "C" => $item["C"],
                "D" => $item["D"],
                "answer" => $item["ans"],
            ];
           }
           $drr[] = [
            'question' => $value['word'],
            'options' =>  $dr 
           ];
        }
        if(!empty($drr)){ 
            return $this->jsonMessage("Questions", json_decode(json_encode($drr)));
        }
        return $this->jsonMessage("Questions", "empty");
    }

    
    public function getGenQuestion($levelId){
        $levelId = $this->prepareData($levelId);
        $qp= "SELECT * FROM `gen_question` where level_id = '".$levelId."'";
        $relt = mysqli_query($this->connect, $qp);
        $rows = mysqli_fetch_all($relt, MYSQLI_ASSOC); 
        if(!empty($rows)){ 
            return $this->jsonMessage("Questions", json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Questions", "empty");

    }


    public function getCulture($id){
        $id = $this->prepareData($id);
        $bbj = "select id, name,img from culture_table where type_id = '".$id."'";
        $result = mysqli_query($this->connect, $bbj);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Culture",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Culture", "empty");

    }

    public function getComment($id){
        $id = $this->prepareData($id);
        $bj = "select * from comment_tb where post_id = '".$id."'";
        $result = mysqli_query($this->connect, $bj);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Comments",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Comments", "empty");

    }
    
    public function getConvo($id){
        $id = $this->prepareData($id);
        $bbj = "select id, A,B from dialogue where video_id = '".$id."'";
        $result = mysqli_query($this->connect, $bbj);
        
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Dialogue",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Dialogue", "empty");

    }
    public function getWords($id){
        $id = $this->prepareData($id);
        $bbj = "select * from word_list where class_id = '".$id."'";
        $result = mysqli_query($this->connect, $bbj);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Word",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Word", "empty");

    }

    public function getInitialList($id){
        $id = $this->prepareData($id);
        $bbj = "select * from initials where class_id = '".$id."'";
        $result = mysqli_query($this->connect, $bbj);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Pinyin",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Pinyin", "empty");

    }
    public function getFinalList($id){
        $id = $this->prepareData($id);
        $bbj = "select * from finals where final_id = '".$id."'";
        $result = mysqli_query($this->connect, $bbj);
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC); 
        if(!empty($rows)){
            return $this-> jsonMessage("Pinyin",json_decode(json_encode($rows)));
        }
        return $this->jsonMessage("Pinyin", "empty");

    }

    public function loadLevels(){
    $bq= "select id,name,img from quiz_level";
    $result = mysqli_query($this->connect, $bq);
    $rows = array();
    while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}

echo $this->jsonMessage("Levels",$rows);
}
 public function loadWordClasses(){
    $bq= "select * from word_class";
    $result = mysqli_query($this->connect, $bq);
    $rows = array();
    while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}
 
echo $this->jsonMessage("Word",$rows);

}


public function loadALevels(){
    $al= "SELECT * FROM `audio_level`";
    $result = mysqli_query($this->connect, $al);
    $rows = array();
while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}
echo $this->jsonMessage("Levels",$rows);
}


public function loadVideos(){
    $cc = "SELECT * FROM `videos`;";
    $result = mysqli_query($this->connect, $cc);
    $rows = array();
    while($r = mysqli_fetch_assoc($result)) {
        $rows[] = $r;
    }
    echo $this->jsonMessage("Courses",$rows);
}

public function loadCultureType(){
    $ccd = "SELECT * FROM `culture_type`;";
    $rest = mysqli_query($this->connect, $ccd);
    $rows = array();
    while($r = mysqli_fetch_assoc($rest)) {
        $rows[] = $r;
    }
    echo $this->jsonMessage("CultureType",$rows);
}




public function loadPinyinClass(){
    $al= "SELECT * FROM `pinyin_class`";
    $result = mysqli_query($this->connect, $al);
    $rows = array();

    while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}
 echo $this->jsonMessage("Pinyin",$rows);
}

public function loadPosts(){
    $al= "SELECT * FROM `post_tb`";
    $result = mysqli_query($this->connect, $al);
    $rows = array();

while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}
echo $this->jsonMessage("Post",$rows);
}

}

?>
