Amier, <br>
So on entry to a screen it will auto populate once, and prevent<br>
all attempts to autopopulate again til you rearrive at the screen.<br>
	
	
This is an error I get on request maintenance, if a user sends in	<br>
a second maintenance request for the same type of thing <br>

	@REQUESTMAINTENENCECONTROLLER.JAVA<br>
Apartment number: 465 issueType: Broken Heater<br>
INSERT INTO MAINTENANCE_REQUEST (Apt_Number, Date_Request, Issue_Type) VALUES('465', '2014-11-28', 'Broken Heater') <br>
com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '465-Broken<br> Heater-2014-11-28' for key 'PRIMARY'<br>

Kentaro<br>
Bhavya<br>
Amier<br>
Clarence<br>
