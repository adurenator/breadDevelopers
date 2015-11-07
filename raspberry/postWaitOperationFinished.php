<?php
	shell_exec( "sudo python utilBuzzer.py 750 0.1" );
	usleep( 50000 );
	shell_exec( "sudo python utilBuzzer.py 750 0.6" );
	
	echo "1";
?>
