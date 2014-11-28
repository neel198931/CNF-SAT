all: Submission.class

Submission.class: Submission.java
	javac Submission.java

clean:
	rm *.class
