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
        function InsertdataToCostomer($postAddress,$postEmail,$postID,$postName,$postNumber){
            $stmt=$this->con->prepare("INSERT INTO COMSTOMERS (postAddress,postCheck,postEmail,postID,postName,postNumber) VALUE(postAddress,false,postEmail,postID,postName,postNumber)");
            $stmt->bind_param('postAddress',$postAddress);
            $stmt->bind_param('postEmail',$postEmail);
            $stmt->bind_param('postID',$postID);
            $stmt->bind_param('postName',$postName);
            $stmt->bind_param('postNumber',$postNumber);
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
            if($stmt=$this->con->prepare("SELECT * FROM COMSTOMERS WHERE postID=?")){
                $stmt->bind_param('s',$Costomerkey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['postID'].",".$result['postAddress'].",".$result['postCheck'].",".$result['postEmail'].",".$result['postName'].",".$result['postNumber'];

            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        /**
         * [DeleteToCostomer description]
         * @param [type] $DeleteKey [description]
         */
        function DeleteToCostomer($DeleteKey){
            
            if($stmt=$this->con->prepare("DELETE FROM COMSTOMERS WHERE postID=?")){
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
        //Item Function
        function InsertdataToItems($itemName,$itemNumber,$Receiver,$Sender){
            $stmt=$this->con->prepare("INSERT INTO Items (itemName,itemNumber,Receiver,Sender) VALUE(itemName,itemNumber,Receiver,Sender)");
            $stmt->bind_param('itemName',$itemName);
            $stmt->bind_param('itemNumber',(int)$itemNumber);
            $stmt->bind_param('Receiver',$Receiver);
            $stmt->bind_param('Sender',$Sender);
            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }
        function getValueToItem($ItemKey){
            if($stmt=$this->con->prepare("SELECT * FROM ITEMS WHERE itemNumber=?")){
                $stmt->bind_param('i',(int)$ItemKey);
                $stmt->execute();
                $result=$stmt->get_result()->fetch_assoc();
                if($result==NULL)
                    return "sibal";
                else
                    return $result['itemName'].",".$result['itemNumber'].",".$result['Receiver'].",".$result['Sender'];

            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }
        }
        function DeleteToItems($DeleteKey){
            if($stmt=$this->con->prepare("DELETE FROM ITEMS WHERE itemNumber=?")){
                $stmt->bind_param('i',(int)$DeleteKey);
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
        function InsertdataToUber($DeliverCheck,$Item,$ITemNumber,$ReceiverAddress,$ReceiverName,$ReceiverNumber,$SenderAddress){
            $stmt=$this->con->prepare("INSERT INTO UBER (DeliverCheck,Item,ITemNumber,ReceiverAddress,ReceiverName,ReceiverNumber,SenderAddress) VALUE(DeliverCheck,Item,ITemNumber,ReceiverAddress,ReceiverName,ReceiverNumber,SenderAddress)");
            $stmt->bind_param('DeliverCheck',$DeliverCheck);
            $stmt->bind_param('Item',$Item);
            $stmt->bind_param('ITemNumber',$ITemNumber);
            $stmt->bind_param('ReceiverAddress',$ReceiverAddress);
            $stmt->bind_param('ReceiverName',$ReceiverName);
            $stmt->bind_param('ReceiverNumber',$ReceiverNumber);
            $stmt->bind_param('SenderAddress',$SenderAddress);
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
                    return $result['DeliverCheck'].",".$result['Item'].",".$result['ITemNumber'].",".$result['ReceiverAddress'].",".$result['ReceiverName'].",".$result['ReceiverNumber'].",".$result['SenderAddress'];
            }else{
                    return "\n"."error_code : ".mysqli_error($this->con) . "\n";
            }

        }
        function DeleteToUber($DeleteKey){
            if($stmt=$this->con->prepare("DELETE FROM UBER WHERE itemNumber=?")){
                $stmt->bind_param('?',(int)$DeleteKey);
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