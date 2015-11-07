<?php
	$filename = "pinCodeStatus.txt";

	# Execute pin reading script
	shell_exec( "./buttons.sh" );

	// Check file exists
	if ( file_exists( $filename ) )
	{
		$pinStatus = shell_exec( "cat pinCodeStatus.txt" );

		if ( $pinStatus == "1" )
		{
			// Delete file
			//unlink( $filename );
		}
		echo $pinStatus;
	}
	else
	{
		echo "0";
	}
?>
