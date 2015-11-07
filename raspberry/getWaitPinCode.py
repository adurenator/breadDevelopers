import sys
import RPi.GPIO as GPIO, time, os
import os.path

def bibrate( pin, frequency, duration ):
        cumulativeDuration = 0
        GPIO.setup(pin, GPIO.OUT)
        GPIO.output(pin, GPIO.LOW)

        period = 1.0/frequency
        hperiod = period * 0.9
        lperiod = period * 0.1

        while ( cumulativeDuration < duration ):
                GPIO.output(pin, GPIO.HIGH)
                time.sleep(hperiod)
                GPIO.output(pin, GPIO.LOW)
                time.sleep(lperiod)
                cumulativeDuration += period

        GPIO.output(pin, GPIO.LOW)


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
		print keysCounter
		bibrate( int(27), float(750), float(0.1))
		while ( GPIO.input(pin) == GPIO.HIGH ):
			time.sleep(0.1)

# Set file to 1
os.remove( 'pinCodeStatus.txt' )
file = open( 'pinCodeStatus.txt', 'w' )
file.write( '1' )


