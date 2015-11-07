<?php
	shell_exec( "./buzzer.sh 750 0.1" );
	usleep( 50000 );
	shell_exec( "./buzzer.sh 750 0.6" );
	
	echo "1";
?>
