<?php
    $response=array();
    $connect=mysqli_connect("db.sice.indiana.edu","i494f19_team20","my+sql=i494f19_team20","i494f19_team20");
    if(isset($_REQUEST['pro_name']));
    
    {
        $name=$_REQUEST['pro_name'];
    
        $select = "select Name, Descrip from locations where Name  = '$name'";
        $select2 = "select Round(AVG(DATE_FORMAT(Timediff(departureTime,arrivalTime),'%i')),1) as Time from Timespent where locID = 'Two' and departureTime != '00:00:00'";
        
        $result = mysqli_query($connect, $select);
        $result2 = mysqli_query($connect, $select2);
       
 		$row = mysqli_fetch_assoc($result);
 		$row1 = mysqli_fetch_assoc($result2);
		
		$name2 = $row1["Time"];
		
	
 		
 		if (mysqli_num_rows($result)>0) {

			
			
			$response['message'] = $name2;
		
			
			
			
			}


			
		else {
		
		
		
			$response['message']='keep trying';
			
		}	
		
		
        
        echo json_encode($response);

    }

?>