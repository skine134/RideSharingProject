<?php
	require_once 'DbOperation.php';
	$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		if(isset($_POST["Table"])and isset($_POST["Command"]) and isset($_POST["Content"])){
    $table=$_POST["Table"];
    $command=$_POST["Command"];
    $contents=$_POST["Content"];
    $changetag=$_Post["Changetag"];
    $changeparam1=$_Post["Changeparam1"];
    $changeparam2=$_Post["Changeparam2"];
    $do=new DbOperation();
   if($table=='Costomer'){
    		switch ($command) {
    			case 'Insert':
    				$result=$do->InsertdataToCostomer($contents);
    				break;
    			case 'delete':
    				$result=$do->DeleteToCostomer($contents);
    				break;
    			case 'select':
    				$result=$do->getValueToCostomer($contents);
    				break;
    			case 'update':
    				if($changetag=='postCheck')
    				$result=$do->changepostChecktoCostomer($changeparam1,$changeparam2);
    				else{
    				$result=$do->changedeliveryChecktoCostomer($changeparam1,$changeparam2);
    				}
    				break;
    			default:
    				$result="not found Costomer command";
    				break;
    		}
    	}
    elseif ($table=='Uber') {
    	switch ($command) {
    		case 'Insert':
    			$result=$do->InsertdataToUber($contents);
    			break;
    		case 'delete':
    			$result=$do->DeleteToUber($contents);
   				break;
   			case 'select':
   				$result=$do->getValueToUber($contents);
   				break;
   			case 'update':
    				$result=$do->changeUbertoUber($changeparam1,$changeparam2);
    				break;
   			default:
    			$result="not found Uber command";
   				break;
    	}
    }
    elseif ($table=='Item'){
    	switch ($command) {
    		case 'Insert':
    			$result=$do->InsertdataToItem($contents);
    			break;
    		case 'delete':
    			$result=$do->DeleteToItem($contents);
    			break;
    		case 'select':
    			$result=$do->getValueToItem($contents);
    			break;
    		default:
    			$result="not found Item command";
    			break;
    	}
    }
}else{
	$response['error']=true;
	$result="Require failed_missing";
}
}else{
	$response['error']=true;
	$result="have not connected";
}
    echo $result;
?>