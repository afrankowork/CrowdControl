<?php
    $response=array();
    $connect=mysqli_connect("db.sice.indiana.edu","i494f19_team20","my+sql=i494f19_team20","i494f19_team20");
    if(isset($_REQUEST['pro_etlocID1']) && isset($_REQUEST['pro_etName1']) && isset($_REQUEST['pro_etDescrip1']) && isset($_REQUEST['pro_etEmail2']) && isset($_REQUEST['pro_etPassword1']));
    
    {
        $locID=$_REQUEST['pro_etlocID1'];

        
        
        
       	$delete = "Delete from locations where Name = '$locID'";
        $select = "select * from locations where Name = '$locID'";
        $result = mysqli_query($connect, $select);
       
 		if (mysqli_num_rows($result)>0) {

			
			mysqli_query($connect, $delete);
			$response['message']='Deleted';
			
			
			}


			
		else {
		
		
		
			$response['message']='Not Deleted';
			
		}	
		
		
        
        echo json_encode($response);
    }

?>