import sys
import RPi.GPIO as GPIO, time, os
import os.path

keysCounter = 0
pin = 17

# Create file with 0
if (os.path.isfile( 'pinCodeStatus.txt' )):
	os.remove( 'pinCodeStatus.txt' )

file = open( 'pinCodeStatus.txt', 'w' )
file.write( '0' )

# Setup pin
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(pin, GPIO.IN)

while keysCounter < 4:
	# Check button pressed
	if ( GPIO.input(pin) == GPIO.HIGH ):
		keysCounter += 1
		#print keysCounter
		while ( GPIO.input(pin) == GPIO.HIGH ):
			time.sleep(0.1)

# Set file to 1
os.remove( 'pinCodeStatus.txt' )
file = open( 'pinCodeStatus.txt', 'w' )
file.write( '1' )

