import sys
import RPi.GPIO as GPIO, time, os

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

pin  = 27
freq = sys.argv[1]
dura = sys.argv[2]

def bibrate( pin, frequency, duration ):
	cumulativeDuration = 0
	GPIO.setup(pin, GPIO.OUT)
	GPIO.output(pin, GPIO.LOW)

	period = 1.0/frequency
	#print period
	hperiod = period * 0.9
	lperiod = period * 0.1
	#print hperiod
	#print lperiod

	while ( cumulativeDuration < duration ):
        	GPIO.output(pin, GPIO.HIGH)
        	time.sleep(hperiod)
		GPIO.output(pin, GPIO.LOW)
		time.sleep(lperiod)
		cumulativeDuration += period

	GPIO.output(pin, GPIO.LOW)

bibrate( pin, float(freq), float(dura) )
