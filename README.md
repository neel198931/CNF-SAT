CNF-SAT
===
A propositional logic formula, also called Boolean expression, is built from variables, operators AND (conjunction, also denoted by ?), OR (disjunction, ?), NOT (negation, ¬), and parentheses. A formula is said to be satisfiable if it can be made TRUE by assigning appropriate logical values (i.e. TRUE, FALSE) to its variables. The Boolean satisfiability problem (SAT) is, given a formula, to check whether it is satisfiable. This decision problem is of central importance in various areas of computer science, including theoretical computer science, complexity theory, algorithmics, and artificial intelligence.

This program will find possible solutions to a fiven boolean expression using Depth First Search algorithm and write it in output file. 

If number of variables are less than 5 then all the possible solutions will be found else only one possible solution will be printed.

This program will read from the command line the following two parameters: <input file name> <output file name>.

The input file has the following structure:
Line 1: n m sizeC // where n is the number of variables, and m the number of clauses and sizeC the number of literals in the clauses
Line 2 to m+1: a list of sizeC positive and negative numbers in the range 1 to n. If the number is negative the literal is negated

