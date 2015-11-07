<?php
	$res = shell_exec( "./photoresistor.sh" );
	
	if ( $res === NULL )
        {
                echo "0";
        }
        else if ( $res > 1800 )
        {
                echo "1";
		shell_exec( "./buzzer.sh 750 0.03" );
		usleep( 1000 );
		shell_exec( "./buzzer.sh 750 0.03" );
        }
	else
	{
		echo "0";
	}
?>

