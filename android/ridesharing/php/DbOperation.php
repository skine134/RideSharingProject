<?php
    class DbOperation{
        private $con;
        private $db;
        //__construct if first start function ,java e.x.) public DbOperation(){}
        
        /**
         * [__construct description]
         */
        function __construct(){
            require_once dirname(__FILE__).'/DbConnect.php';
            $db=new DbConnect();
            $this->con=$db->connect();
        }
        //declaration used function.
        //sample function.
        //function createUser($username,$pass,$email){
        //    $password=md5($pass); //CheckSum password
        //    $stmt=$this->con->prepare("INSERT INTO 'users' ('id','username','password','email') VALUES(NULL,?,?,?);");//prepare(mysql command);
        //    $stmt->bind_param("sss",$username,$password,$email);                                                        //exchange parameter;
        //    if($stmt->execute()){                               //stmt is execute?
        //        return true;
        //    }else{
        //        return false;
        //        }

        //}

        //Costomer Function
        /**
         * [InsertdataToCostomer description]
         * @param [type] $postAddress [description]
         * @param [type] $postEmail   [description]
         * @param [type] $postID      [description]
         * @param [type] $postName    [description]
         * @param [type] $postNumber  [description]
         */
        function InsertdataToCostomer($postName,$postAddress,$postNumber,$DeliverCheck,$postEmail,$ReceiverName,$ReceiverAddress,$ItemName,$ReceiverNumber,$postCheck,$Date){
            $stmt=$this->con->prepare("INSERT INTO COMSTOMERS VALUE(?,?,?,?,?,?,?,?,?,?,?)");
            $stmt->bind_param('ssiissssii,s',$postName,$postAddress,(int)$postNumber,(int)$DeliverCheck,$postEmail,$ReceiverName,$ReceiverAddress,$ItemName,(int)$ReceiverNumber,(int)$postCheck,$Date);
            if($stmt->execute()){
                return true; 
            }else{
                return false;
            }
        }
        /**
         * [getValueToCostomer description]
         * @param  [type] $Costomerkey [description]
         * @return [type]              [description]
         */
        function getValueToCostomer($Costomerkey){
            if($stmt=$this->con->prepare("SELECT * FROM COMSTOMERS WHERE postName=?")){
                $stmt->bind_param('s',$Costomerkey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['postName'].",".$result['postAddress'].",".$result['postNumber'].",".$result['DeliverCheck'].",".$result['postEmail'].",".$result['ReceiverName'].$result['ReceiverAddress'].",".$result['ItemName'].",".$result['ReceiverNumber'].",".$result['postCheck'].",".$result['Date'];

            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        /**
         * [DeleteToCostomer description]
         * @param [type] $DeleteKey [description]
         */
        function DeleteToCostomer($DeleteKey){
            
            if($stmt=$this->con->prepare("DELETE FROM COMSTOMERS WHERE postName=?")){
                $stmt->bind_param('s',$DeleteKey);
                if($stmt->execute()){
                    return true;
                }else{
                    return false;
                }
                }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
                }
             

            }
        function changepostChecktoCostomer($postCheck,$ReceiverName){
            if($stmt=$this->con->prepare("UPDATE COMSTOMERS SET postCheck=? WHERE ReceiverName=?")){
            $stmt->bind_param('is',(int)$postCheck,$ReceiverName);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        function changedeliveryChecktoCostomer($DeliverCheck,$ReceiverName){
            if($stmt=$this->con->prepare("UPDATE COMSTOMERS SET DeliverCheck=? WHERE ReceiverName=?")){
            $stmt->bind_param('is',(int)$DeliverCheck,$ReceiverName);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        //Item Function
        function InsertdataToItems($itemNumber,$itemName,$Sender,$Receiver,$ItemCount){
            $stmt=$this->con->prepare("INSERT INTO Items VALUE('isssi')");
            $stmt->bind_param('isssi',(int)$itemNumber,$itemName,$Sender,$Receiver,(int)$ItemCount);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }
        function getValueToItem($ItemKey){
            if($stmt=$this->con->prepare("SELECT * FROM ITEMS WHERE itemName=?")){
                $stmt->bind_param('s',$ItemKey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['itemNumber'].",".$result['itemName'].",".$result['Sender'].",".$result['Receiver'].",".$result['ItemCount'];

            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        function DeleteToItems($DeleteKey){
            if($stmt=$this->con->prepare("DELETE FROM ITEMS WHERE itemName=?")){
                $stmt->bind_param('s',$DeleteKey);
                if($stmt->execute()){
                    return true;
                }else{
                    return false;
                }
                }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
                }

        }

        //Uber Function
        function InsertdataToUber($ReceiverName,$ReceiverNumber,$ReceiverAddress,$Item,$SenderAddress,$SenderNumber,$DeliverCheck,$UberId,$UberName,$postCheck){
            $stmt=$this->con->prepare("INSERT INTO UBER  VALUE?,?,?,?,?,?,?,?,?,?)");
            $stmt->bind_param('s,i,s,s,s,i,i,s,i',$ReceiverName,(int)$ReceiverNumber,$ReceiverAddress,$Item,$SenderAddress,(int)$SenderNumber,(int)$DeliverCheck,$UberId,$UberName,(int)$postCheck);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }
        function getValueToUber($Uberkey){
            if($stmt=$this->con->prepare("SELECT * FROM UBER WHERE itemNumber=?")){
                $stmt->bind_param('i',(int)$Uberkey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['ReceiverName'].",".$result['ReceiverNumber'].",".$result['ReceiverAddress'].",".$result['Item'].",".$result['SenderAddress'].",".$result['SenderNumber'].",".$result['DeliverCheck'].",".$result['UberId'].",".$result['UberName'].",".$result['postCheck'];
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }

        }
        function DeleteToUber($DeleteKey){
            if($stmt=$this->con->prepare("DELETE FROM UBER WHERE UberId=?")){
                $stmt->bind_param('s',$DeleteKey);
                if($stmt->execute()){
                    return true;
                }else{
                    return false;
                }
                }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
                }
        }

        function changeUbertoUber($ChangeUberName,$UberName){
            if($stmt=$this->con->prepare("UPDATE COMSTOMERS SET UberName=? WHERE UberName=?")){
            $stmt->bind_param('ss',$ChangeUberName,$UberName);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        //User Function
        function InsertdataToUser($userID,$userPassword,$userName,$userAge,$userAddress,$userNumber,$userEmail,$tag){
            $stmt=$this->con->prepare("INSERT INTO USER  VALUE(?,?,?,?,?,?,?,?)");
            $stmt->bind_param('s,s,s,i,s,i,s,i',$userID,$userPassword,$userName,(int)$userAge,$userAddress,(int)$userNumber,$userEmail,(int)$tag);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }
        function getValueToUser($Userkey){
            if($stmt=$this->con->prepare("SELECT * FROM USER WHERE userID=?")){
                $stmt->bind_param('s',$Userkey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['userID'].",".$result['userPassword'].",".$result['userName'].",".$result['userAge'].",".$result['userAddress'].",".$result['userNumber'].",".$result['$userEmail'].",".$result['tag'];
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }

        }
        function DeleteToUser($DeleteKey){
            if($stmt=$this->con->prepare("DELETE FROM USER WHERE userID=?")){
                $stmt->bind_param('s',$DeleteKey);
                if($stmt->execute()){
                    return true;
                }else{
                    return false;
                }
                }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
                }
        }
    }

?>