import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgreSqlConnection 
{
	public static void main(String[] args) 
	{
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://cs1.calstatela.edu:5432/cs1222s110", "cs1222s110", "4RXMt7hD")) 
		{	
			System.out.println("Connected to PostgreSQL database!");
			
			//The following statements is what will allow us to send commands as SQL to our Database
			Statement statement = connection.createStatement();

			//We have three methods that can be used to exececute sql methods
			//execute - execute method can be used with any type of SQL statements. Accepts both select and insert/update statements.
			//executeQuery - statements that returns a result set by fetching some data from the database. Accepts only Select Statements
			//executeUpdate - statements that insert/update/delete data at the database. Accepts Non-Select Statement
			
			boolean continueProgram = true;
			Scanner input = new Scanner(System.in);
			int option;
			while (continueProgram)
			{
				System.out.println();
				System.out.println("What actions do you want to peform?");
				System.out.println("1 - Add faculty member");
				System.out.println("2 - Remove faculty member");
				System.out.println("3 - Add Project");
				System.out.println("4 - Remove Project");
				System.out.println();
				
				option = input.nextInt();
				System.out.println();

				switch(option)
				{
					case 1:
						statement.executeUpdate(addFaculty());
						continueProgram = continuePrompt();
						break;
					case 2:
						statement.executeUpdate(removeFaculty());
						continueProgram = continuePrompt();
						break;
					case 3:
						statement.executeUpdate(addProject());
						continueProgram = continuePrompt();
						break;
					case 4:
						statement.executeUpdate(removeProject());
						continueProgram = continuePrompt();
						break;
					default: 
						continueProgram = true; 
				}
			}

		} 
		
		catch (SQLException e) 
		{
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public static boolean continuePrompt()
	{
		Scanner input = new Scanner(System.in);
		char continuationFlag;
		do 
		{ 
			System.out.println("Do you wish to continue? y or n");
			continuationFlag = input.next().charAt(0);

			if (continuationFlag == 'y')
				return true;
			else 
				return false; 
		}
		while (continuationFlag != 'y' || continuationFlag != 'n');
	}
	
	public static String addFaculty()
	{
		//Initilize the SQL String Command for 
		 String sql = "INSERT INTO Professor (ssn,name,age,gender,rank,specialty,deptNum) ";
		 
		 //Delcare Scanner Class to allow user to input values into the Database
		 Scanner input = new Scanner(System.in);
		 
		 //Ask User to inputvalues into the proper colums for the Record
		 System.out.println("Enter the Falculty's Social Security Number");
		 String ssnValue = "'" + input.nextLine() + "'";
		 System.out.println("Enter the Falculty's Name");
		 String nameValue = "'" + input.nextLine() + "'"; //Single Apostrophes are used to pass the line as a character string to the SQL Statement
		 System.out.println("Enter the Falculty's Age");
		 String ageValue = input.nextLine();
		 System.out.println("Enter the Falculty's Gender");
		 String genderValue = "'" + input.nextLine() + "'";
		 System.out.println("Enter the Falculty's Rank");
		 String rankValue = input.nextLine(); 
		 System.out.println("Enter the Falculty's Specialty");
		 String specialtyValue = "'" + input.nextLine() + "'";
		 System.out.println("Enter the Falculty's Department Number");
		 String deptNumValue = input.nextLine();

		 //Finish appending the value inserted by the user into the SQL String to have a Complete String
		 sql+= "VALUES("+ssnValue+","+nameValue+","+ageValue+","+genderValue+","+rankValue+","+specialtyValue+","+deptNumValue+");";

		 //Log the output
		 System.out.println(sql);
		 
		 return sql;
	}

	public static String removeFaculty()
	{
		String sql = "DELETE FROM Professor WHERE ssn=";

		//Declare Scanner Class to allow user to delete a faculty from Database
		Scanner input = new Scanner(System.in);

		//Ask User to delete faculty from Database
		System.out.println("Enter the faculty's ssn that will be removed from the records");
		String ssnValue = "'" + input.nextLine() + "'";

		//Finish appending the SQL Statement of the faculty to be deleted 
		sql+=ssnValue;

		//Log the output
		System.out.println(sql);
		
		return sql;
	}

	public static String addProject()
	{
		String sql = "INSERT INTO Project (projNum,sponsor,start_date,end_date,budget,prin_Investigator) ";
		//Delcare Scanner Class to allow user to input values into the Database
		Scanner input = new Scanner(System.in);

		//Ask User to inputvalues into the proper colums for the Record
		System.out.println("Enter the Project Number");
		String projNumValue = "'" + input.nextLine() + "'";
		System.out.println("Enter the Sponsors Name");
		String sponsorNameValue = "'" + input.nextLine() + "'"; //Single Apostrophes are used to pass the line as a character string to the SQL Statement
		System.out.println("Enter the project's start date");
		String startDateValue = "'" + input.nextLine() + "'";
		System.out.println("Enter the project's end date");
		String endDateValue = "'" + input.nextLine() + "'";
		System.out.println("Enter the projects's budget");
		String budgetkValue = input.nextLine(); 
		System.out.println("Enter the Principal Investigators ssn");
		String prinInvesSSN = "'" + input.nextLine() + "'";

		//Finish appending the value inserted by the user into the SQL String to have a Complete String
		sql+= "VALUES("+projNumValue+","+sponsorNameValue+","+startDateValue+","+endDateValue+","+budgetkValue+","+prinInvesSSN+");";

		//Log the output
		System.out.println(sql);
		
		return sql;
	}

	public static String removeProject()
	{
		String sql = "DELETE FROM Project WHERE projNum=";

		//Declare Scanner Class to allow user to delete a faculty from Database
		Scanner input = new Scanner(System.in);

		//Ask User to delete project from Database
		System.out.println("Enter the Project number that will be removed from the records");
		String projNumValue = "'" + input.nextLine() + "'";

		//Finish appending the SQL Statement of the faculty to be deleted 
		sql+=projNumValue;

		//Log the output
		System.out.println(sql);
		
		return sql;
	}

}
