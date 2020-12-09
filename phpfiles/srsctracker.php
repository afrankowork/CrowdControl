<?php
    $response=array();
    $connect=mysqli_connect("db.sice.indiana.edu","i494f19_team20","my+sql=i494f19_team20","i494f19_team20");
    if(isset($_REQUEST['pro_name']));
    
    {
        $name=$_REQUEST['pro_name'];
    
        $select = "select count(*) as track from Timespent where locID = 'One' and departureTime = '00:00:00'";

        
        $result = mysqli_query($connect, $select);
       
       
 		$row = mysqli_fetch_assoc($result);
 	
		$name1 = $row["track"];
	
		
	
 		
 		if (mysqli_num_rows($result)>0) {

			
			
			
			$response['message2'] = $name1;
			
			
			
			}


			
		else {
		
		
		
			$response['message2']='keep trying';
			
		}	
		
		
        
        echo json_encode($response);

    }

?>