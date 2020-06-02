<?php
    class DbConnect{
        private $con;
        function __construct(){
        }
        function connect(){
            include_once dirname(__FILE__).'/Constants.php';          //include package
            $this->con=new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME); //connect mysqpl
            if(mysqli_connect_errno()){                             //error check
                echo "Failed to connect with database".mysqli_connect_errno();
            }
            return $this->con;
        }
    }

?>