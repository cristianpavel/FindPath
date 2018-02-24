build:
	javac *.java
run:
	java -Xmx1G MainClass ${ARGS}

clean:
	rm -rf *.class
