from time import sleep
import RPi.GPIO as GPIO

GPIO.setup( 11, GPIO.IN )

while 1:
	if GPIO.input(11):
		print "HIGH"
	else:
		print "low"

	sleep(1)

