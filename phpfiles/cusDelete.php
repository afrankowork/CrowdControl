<?php
    $response=array();
    $connect=mysqli_connect("db.sice.indiana.edu","i494f19_team20","my+sql=i494f19_team20","i494f19_team20");
    if(isset($_REQUEST['pro_etidcus1']) && isset($_REQUEST['pro_etnamecus1']) && isset($_REQUEST['pro_etemailcus1']));
    
    {
        $id=$_REQUEST['pro_etidcus1'];
   
        
        
        
        $insert = "Delete from userLogin where email = '$id'";
        $select = "select * from userLogin where email = '$id'";
        $result = mysqli_query($connect, $select);
       
 		if (mysqli_num_rows($result)>0) {

			mysqli_query($connect,$insert);
			
			$response['message']='Customer Deleted';
			
			
			}


			
		else {
		
		
		
			$response['message']='Unsuccesful';
			
		}	
		
		
        
        echo json_encode($response);
    }

?>