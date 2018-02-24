
PATH="~/task1/"
from subprocess import call


for i in range(1, 10):
	newP = PATH + str(i) + ".in"
	call("java","MainClass 1 newP out")
	newP = PATH + str(i) + ".out"
	call("diff","out newP")
	
