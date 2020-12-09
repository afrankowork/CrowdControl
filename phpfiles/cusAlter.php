<?php
    $response=array();
    $connect=mysqli_connect("db.sice.indiana.edu","i494f19_team20","my+sql=i494f19_team20","i494f19_team20");
    if(isset($_REQUEST['pro_etidcus1']) && isset($_REQUEST['pro_etnamecus1']));
    
    {
        $id=$_REQUEST['pro_etidcus1'];
        $descrip=$_REQUEST['pro_etnamecus1'];
   
        
      
        
        $update = "Update userLogin set email = '$descrip' where id = '$id'";
        $select = "select * from userLogin where id = '$id'";
        $result = mysqli_query($connect, $select);
       
 		if (mysqli_num_rows($result)>0) {

			mysqli_query($connect,$update);
			
			$response['message']='Updated Email!';
			
			
			}


			
		else {
		
		
		
			$response['message']='Unsuccesful';
			
		}	
		
		
        
        echo json_encode($response);
    }

?>