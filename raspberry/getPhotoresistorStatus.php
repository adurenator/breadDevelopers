<?php
	$res = shell_exec( "sudo python getPhotoresistorStatus.py" );

        if ( $res === false )
        {
                echo "0";
        }
        else if ( $res > 1800 )
        {
                echo "1";
		shell_exec( "sudo python utilBuzzer.py 750 0.03" );
		usleep( 1000 );
		shell_exec( "sudo python utilBuzzer.py 750 0.03" );
        }
	else
	{
		echo "0";
	}
?>

