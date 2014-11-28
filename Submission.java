import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.lang.String.*;
import java.io.FileWriter;

/*CS575-01_FALL2014 Programming Assignment 1
* Created by - Neelabh Agrawal
*
* Program to implement CNF_SAT problem using DSF
*
* Input parameter - <input file name> <output file name>
*	Input file should be in following structure -
*	Line 1: n m sizeC// where n is the number of variables, and m the number of clauses
*	and sizeC the number of literals in the clauses
*	Line 2 to m+1: a list of sizeC positive and negative numbers in the range 1 to n. 
*	If the number is negative the literal is negated.  
*
* Ouput format - Output file with possible values of solutions
*/

class Submission {
	int n, m, sizeC;
	int[][] clauses;
	int[] x;
	boolean resultFound = false, noMore = false, dontPrint = false;
	String outFile;
	FileWriter writer;
	
	Submission(String in, String out) {
		
		try {
			Scanner scan = new Scanner(new File(in));
			outFile = out;
			n = scan.nextInt();
			m = scan.nextInt();
			sizeC = scan.nextInt();

			/* create a matrix clauses and read all the values from input file*/
			x = new int[n];
			clauses = new int[m][sizeC];

			for(int i = 0;i < m;i++) {
				for(int j=0;j<sizeC;j++) {
					clauses[i][j] = scan.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		/*create new file with output file name entered by user*/
		try {
			writer = new FileWriter(outFile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	boolean dfsCompute(int depth) {
		if(depth==n) {
			if(check()) {
				/*if CNF evaluate true mark resultFound as true*/
				resultFound = true;

				/*if number of variable are less than 5 print all results
				else print only 1 result if number of clauese are less than 30*/
				if(n<=5) {
					printResult();
				} else {
					noMore = true;
					if( m<=30) {
						printResult();
					} else {
						dontPrint = true;
					}
				}
			}
			return false;
		}
		/*recursion will stop once a solution is found in case number of variables are more than 5*/
		if(!noMore) {
			x[depth]=1;
			dfsCompute(depth+1);
		}
		if(!noMore) {
			x[depth]=0;
			dfsCompute(depth+1);
		}
		return true;
	}

	boolean check() {
		boolean result = true;

		for(int i=0;i<m;i++) {
			boolean clause = false;
			int j = 0;
			while(!clause && j < sizeC) {

				/*check if any of the value in caluse is 1 
				then clause will evaluate true*/
				if(clauses[i][j]<0) {
					if(x[(0-clauses[i][j])-1]==0) {
						clause = true;
					}
				} else {
					if(x[clauses[i][j]-1]==1) {
						clause = true;
					}				
				}
				j++;
			}
			/*if any of the clause value is 0, then CNF will evaluate false*/
			if(!clause) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	void printResult(){
		try {
			writer = new FileWriter(outFile, true);
			writer.write(" The solution is\n");

			/*print result in form of variable values*/
			for(int i=0;i<n;i++) {
				writer.write("x[" + (i+1) + "] = " + x[i] + "\n");
			}
			writer.close();
		}catch(IOException exc) {
			System.out.println(exc.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		String userInput = "", output = "";

		/*take input and output file names from user*/
		Scanner scan = new Scanner(System.in);
		userInput = scan.nextLine();
		
		long time1 = System.currentTimeMillis();

		String[] files = userInput.split(" ");

		Submission sub = new Submission(files[0],files[1]);

		/*if result is not found*/
		if(sub.dfsCompute(0) && !sub.resultFound) {
			output = "No satisfying assignment\n";

		/*if result is not found with n>5 and m>30*/
		} else if(sub.resultFound && sub.dontPrint) {
			output = "There is a satisfying assignment\n";
		}

		/*calculate running time*/
		long time2 = System.currentTimeMillis();
		output = output + "Run time is " + (time2 - time1) + " milliseconds";

		/*append the output string to output file*/
		try {
			FileWriter wr = new FileWriter(sub.outFile, true);
			wr.write(output);
			wr.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
