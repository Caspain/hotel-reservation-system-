package rervation_types;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import java.util.Scanner;

import randomAccessFiles.RandomAccessFiles;
import reservation_Options.Manage_Reservations;
import reservations.*;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basic_classess.Reservation_Files;
import ckeckStatus.Customer_Checkin;

public class Hotel_Room extends Reservation_Type implements
		Manage_Reservations, RandomAccessFiles {

	//
	final static int size_Of_Records = 76;
	RandomAccessFile accessFile = null;
	final String File_source = "C:\\OOP PROJECT FILES\\NewRandom.txt";
	int tempc = 0;
	static boolean true_Break;
	public Hotel_Room(int Duration_Of_Stay, int Occupancy) {
		this.Duration_Of_Stay = Duration_Of_Stay;
		this.Occupancy = Occupancy;

	}

	public Hotel_Room() {

	}

	@Override
	public void Add_Reservation() {

		System.out.println("Welcome to the Reservation Adder!");
		// do {
		try {

			System.out.print("Enter Area code: ");
			Area_Code = Integer.parseInt(int_Data_input.nextLine().trim());
			System.out.print("Enter Line Exchange: ");
			Exchange = Integer.parseInt(int_Data_input.nextLine().trim());
			System.out.print("Enter Line Number: ");
			Line = Integer.parseInt(int_Data_input.nextLine().trim());
			System.out.print("Enter First Name: ");

			First_Name = String_Data_input.nextLine();
			System.out.print("Enter Last Name: ");
			
			Last_Name = String_Data_input.nextLine();

			System.out.print("Enter Duration of stay:");
			Duration_Of_Stay = Integer.parseInt(int_Data_input.nextLine()
					.trim());

			System.out.print("Number of individuals:");
			Occupancy = Integer.parseInt(int_Data_input.nextLine().trim());

			Hour = calendar.get(Calendar.HOUR_OF_DAY);
			System.out.print("Day Selection  > Present|Later : ");
			{
				try_Again = String_Data_input.nextLine().toUpperCase()
						.charAt(0);
				if (try_Again == 'P') {
					Day = calendar.get(Calendar.DAY_OF_WEEK);
					true_Break =true;
				} else if (try_Again == 'L') {

					System.out.print("Day:");
					Day = Integer.parseInt(int_Data_input.nextLine().trim());
					true_Break =true;
				} else {
					System.out.print("Not a Valid option.");
					do{
						System.out.println("do you want to try again[y/n]:");
						
						try_Again = String_Data_input.nextLine().toUpperCase().charAt(0);
						if (try_Again == 'N') {
							System.out.println("Good Bye");
							true_Break =false;
							break;
						}
						if (try_Again == 'Y') {
							System.out.print("Present|Later : ");
							try_Again = String_Data_input.nextLine().toUpperCase().charAt(0);
							if (try_Again == 'P') {
								Day = calendar.get(Calendar.DAY_OF_WEEK);
								true_Break =true;
								break;
							} else if (try_Again == 'L') {

								System.out.print("Day:");
								Day = Integer.parseInt(int_Data_input.nextLine().trim());
								true_Break =true;
								break;
							}else{
								continue;
							}
							
							
						}
					
					}while(try_Again != 'N');
				}

			}

			Minute = calendar.get(Calendar.MINUTE);
			Month = calendar.get(Calendar.MONTH);
			Year = calendar.get(Calendar.YEAR);
			
			

		} catch (Exception e) {

			System.out.println("Data input is invalid!");

		}

		/*
		 * System.out.println("do you want to try again[y/n]:"); try_Again =
		 * String_Data_input.nextLine().toUpperCase().charAt(0); if (try_Again
		 * == 'N') { System.out.println("Good Bye"); break; } if (try_Again ==
		 * 'Y') {
		 * 
		 * continue; }
		 * 
		 * } while (try_Again != 'N');
		 */
		

		try {
			if(true_Break ){
			customer_IdentificationString = 1000 + (random.nextInt(2000-1000));
			System.out.println("Reservation Successfuly added,Here is your CIS: "+ customer_IdentificationString);
			Reservation_Type new_res = new Reservation_Type(Area_Code, Exchange,
			Line, First_Name, Last_Name, Day, Month, Year, Minute, Hour);
			Random_Access_File_Writer();
			
			
			}
		} catch (IOException e) {

			System.err.println(" Error" + e.getMessage());
		}

	}

	@Override
	public void Delete_Reservation() throws IOException {
		accessFile = new RandomAccessFile(File_source, "rw");
		
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine().trim());
		File_Pointer = tempc - 1;
		

		for (int d = 0; d < number_of_records; d++) { // update

			File_Length = (int) accessFile.length();

			accessFile.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile.readInt();
			
			if (tempc == customer_IdentificationString) {
				found = true;
			} else {
				found = false;
			}

		}
		if(found ==false)
			System.out.println("No Such Record exist.");
		else{

		{
			accessFile.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString =0;
			accessFile.writeInt(customer_IdentificationString);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeUTF("oooooooooooooooo");// dummy data
			accessFile.writeUTF("oooooooooooooooo");
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
			accessFile.writeInt(0);
		}
		System.out.println("Record as been successfully deleted.");
		}

		
		accessFile.close();

	}

	@Override
	public void Update_Reservation() throws IOException {
		int tempc = 0;

		accessFile = new RandomAccessFile(File_source, "rw");
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine());
		File_Pointer = tempc - 1;
		int menu_answer = 0;

		// ------------------------------------------------------------

		for (int d = 0; d < number_of_records; d++) { // update number of
														// records to 1000
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile.length();
			accessFile.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
				break;

			} else {
				found = false;
			}

		}
		// -------------------------------------------------

		if (found == false) {
			System.out.println("Cant Update, record not located.");
		} else {
			do{
			try{
			System.out.println("File/Record Founded: ");
			System.out.println("Which option , do you want to update ? ");
			System.out.println("Area_Code        =>[1]");
			System.out.println("Exchange         =>[2]");
			System.out.println("Line             =>[3]");
			System.out.println("First_Name       =>[4]");
			System.out.println("Last_Name        =>[5]");
			System.out.println("Duration_Of_Stay =>[6]");
			System.out.println("Occupancy        =>[7]");
			System.out.println("Day 			 =>[8]");
			System.out.println("Choice: ");
			menu_answer = Integer.parseInt(int_Data_input.nextLine().trim());

			switch (menu_answer) {
			
			case 1: {
				
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Area code: ");
				int tAreaCode = Integer.parseInt(int_Data_input.nextLine().trim());
				Hotel_Write_out(tempc,accessFile,size_Of_Records,tAreaCode, this.Exchange, this.Line,this.First_Name, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);
				
				
			}
				break;

			case 2: {
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Exchange  code: ");
			int	tExchange = Integer.parseInt(int_Data_input.nextLine().trim());
			Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, tExchange, this.Line,this.First_Name, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);
			
				
			}
				break;

			case 3: {
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Line   code: ");
				int tLine = Integer.parseInt(int_Data_input.nextLine().trim());
				Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, Exchange, tLine,this.First_Name, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);
			}
				break;

			case 4: {
				
				System.out.print("Enter New  First_Name : ");
			String	tFirst_Name = String_Data_input.nextLine().trim();
			hotel_read_in(tempc, accessFile, size_Of_Records);
			//-------------------------------------------------------------------
			
			if (tFirst_Name.length() > 16) {
				tempFirstNameString = tFirst_Name.substring(0, 16);

			} else if (tFirst_Name.length() < 16) {

				String_Length_Difference = 16 - tFirst_Name.length();
				for (int c = 0; c < String_Length_Difference; c++) {

					tempFirstNameString = tFirst_Name += " ";
				}

			} else {

				tempFirstNameString = tFirst_Name;
			}
			
			//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
			Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, Exchange, Line,tempFirstNameString, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);

				// ----------------------------------------------------------

			}
				break;

			case 5: {
				System.out.print("Enter New  First_Name : ");
				String	tLast_Name = String_Data_input.nextLine();
				hotel_read_in(tempc, accessFile, size_Of_Records);
				//-------------------------------------------------------------------
				
				if (tLast_Name.length() > 16) {
					tempLastNameString = tLast_Name.substring(0, 16);

				} else if (tLast_Name.length() < 16) {

					String_Length_Difference = 16 - tLast_Name.length();
					for (int c = 0; c < String_Length_Difference; c++) {

						tempLastNameString = tLast_Name += " ";
					}

				} else {

					tempLastNameString = tLast_Name;
				}
				
				Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, Exchange, Line,First_Name, tempLastNameString, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);

			}
				break;

			case 6: {
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Duration_Of_Stay: ");
				int tDuration_Of_Stay = Integer.parseInt(int_Data_input.nextLine().trim());
				Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, this.Exchange, this.Line,this.First_Name, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,tDuration_Of_Stay,this.Occupancy);
				
			}
				break;

			case 7: {
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Occupancy : ");
				int tOccupancy = Integer.parseInt(int_Data_input.nextLine().trim());
				Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, this.Exchange, this.Line,this.First_Name, this.Last_Name, this.Day, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,tOccupancy);
				
			}
				break;

			case 8: {
				hotel_read_in(tempc, accessFile, size_Of_Records);
				System.out.print("Enter New Day : ");
				int tDay = Integer.parseInt(int_Data_input.nextLine().trim());
				Hotel_Write_out(tempc,accessFile,size_Of_Records,Area_Code, this.Exchange, this.Line,this.First_Name, this.Last_Name, tDay, this.Month, this.Year,this.Minute, this.Hour,this.Duration_Of_Stay,this.Occupancy);
			}
				break;
			default: {
				System.out.print("Invalid Selection!");
				break;
					}
			
	}// end switch
			}catch(Exception g){
				g.printStackTrace();
			}
			
		
				System.out.println("Do you wish to continue updating[y|n]:");
				try_Again = int_Data_input.nextLine().toUpperCase().charAt(0);
				if (try_Again=='Y') {
					break;
				}else if(try_Again=='N'){
					continue;
					}
		}while(try_Again!='Y');
		}//end else

		// ---------------------------------------------------------------
		//accessFile.close();
	}

	@Override
	public void view_Reservation() throws IOException {
		
		accessFile = new RandomAccessFile(File_source, "r");
		System.out.println("Please enter Customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine());

		for (int d = 0; d < number_of_records && accessFile.length() != -1; d++) { // update
																					// number
																					//																// records
																					// to
																					// 1000
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile.length();
			accessFile.seek(0);// --------------------------------------------
			accessFile.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile.readInt();
			if (tempc == customer_IdentificationString && customer_IdentificationString!=0) {
				found = true;
				Area_Code = accessFile.readInt();
				Exchange = accessFile.readInt();
				Line = accessFile.readInt();
				First_Name = accessFile.readUTF();
				Last_Name = accessFile.readUTF();
				Duration_Of_Stay = accessFile.readInt();
				Occupancy = accessFile.readInt();
				Hour = accessFile.readInt();
				Minute = accessFile.readInt();
				Month = accessFile.readInt();
				Day = accessFile.readInt();
				Year=accessFile.readInt();
				break;
				
			} else {
				found = false;
			}

		}
		if (found == false) {
			System.out.println("File Not Found");
			
		} else {

			// ----------------------------------------------------------------------------
			JFrame frame = new JFrame("V I E W | H O T E L | R E S E R V A T I O N S");
			DefaultTableModel Tmodel = new DefaultTableModel();
			JTable table=new JTable(Tmodel);
			frame.setVisible(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			Tmodel.addColumn("CIS");
			Tmodel.addColumn("Area_Code");
			Tmodel.addColumn("Exchange");
			Tmodel.addColumn("Line");
			Tmodel.addColumn("First Name");
			Tmodel.addColumn("Last Name");
			Tmodel.addColumn("Length|Of|Stay ");
			Tmodel.addColumn("Occupancy");
			Tmodel.addColumn("Hour");
			Tmodel.addColumn("Minute");
			Tmodel.addColumn("Month");
			Tmodel.addColumn("Day");
			Tmodel.addColumn("Year");
			table.setAutoCreateRowSorter(true);
			table.setCellSelectionEnabled(true);
			table.setSelectionBackground(Color.BLUE);
			table.setSelectionForeground(Color.gray);
			frame.add(new JScrollPane(table));
			
			Tmodel.addRow(new Object[]{customer_IdentificationString,Area_Code,Exchange,Line,First_Name,Last_Name,Duration_Of_Stay,Occupancy,Hour,Minute,Month,Day,Year});
			///////////////////////////////////////////////////////////////////////////////
			
		}

		accessFile.close();

	}

	@Override
	public void Random_Access_File_Writer() throws IOException, EOFException {

		accessFile = new RandomAccessFile(File_source, "rw");

		File_Length = (int) accessFile.length();
		// ---------------------------------------------------------------

		if (First_Name.length() > 16) {
			tempFirstNameString = First_Name.substring(0, 16);

		} else if (First_Name.length() < 16) {

			String_Length_Difference = 16 - First_Name.length();
			for (int c = 0; c < String_Length_Difference; c++) {

				tempFirstNameString = First_Name += " ";
			}

		} else {

			tempFirstNameString = First_Name;
		}
		// ---------------------------

		if (Last_Name.length() > 16) {
			tempLastNameString = Last_Name.substring(0, 16);

		} else if (Last_Name.length() < 16) {

			String_Length_Difference = 16 - Last_Name.length();
			for (int c = 0; c < String_Length_Difference; c++) {

				tempLastNameString = Last_Name += " ";
			}

		} else {

			tempLastNameString = Last_Name;
		}

		// /////////////////////////////////////////////////////////////////////////////////
		
		for (int j = 0; j < 1; j++) {
			File_Pointer = customer_IdentificationString - 1;
			File_Length = (int) accessFile.length();
			accessFile.seek(File_Pointer * size_Of_Records);
			

			accessFile.writeInt(customer_IdentificationString);
		
			accessFile.writeInt(Area_Code);
			
			accessFile.writeInt(Exchange);
			
			accessFile.writeInt(Line);

			accessFile.writeUTF(tempFirstNameString);
			

			accessFile.writeUTF(tempLastNameString);
			
			accessFile.writeInt(Duration_Of_Stay);
			
			accessFile.writeInt(Occupancy);
			
			accessFile.writeInt(Hour);
			
			accessFile.writeInt(Minute);
		
			accessFile.writeInt(Month);
			accessFile.writeInt(Day);
			accessFile.writeInt(Year);


		}

		accessFile.close();
	}

	public boolean Search(int tempc) throws IOException {

		accessFile = new RandomAccessFile(File_source, "r");

		for (int d = 0; d < number_of_records && accessFile.length() != -1; d++) {
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile.length();

			accessFile.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
				break;
			} else {
				found = false;
			}

		}// end for
		accessFile.close();
		return found;

	}

	public int Modified_Search(int temp_Area_Code, int temp_Line, int temp_Exchange, int temp_Day,String temp_FirstName, String temp_LastName,int temp_DurationOfStay, int temp_Ocuupancy,int tempc) throws IOException {

		
		accessFile = new RandomAccessFile(File_source, "r");
		
		
		//System.out.println("Please enter customer_Identification number once more: ");tempc = Integer.parseInt(int_Data_input.nextLine());
		int Rval=0;
		for (int d = 0; d < number_of_records && accessFile.length() != -1; d++) { 
																			
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile.length();
			accessFile.seek(0);

			accessFile.seek(File_Pointer * size_Of_Records);
			
			customer_IdentificationString = accessFile.readInt();
			
			if (tempc == customer_IdentificationString) {
				Area_Code = accessFile.readInt();
				Exchange = accessFile.readInt();
				Line = accessFile.readInt();
				First_Name = accessFile.readUTF();
				Last_Name = accessFile.readUTF();
				Duration_Of_Stay = accessFile.readInt();
				Occupancy = accessFile.readInt();
				Hour = accessFile.readInt();
				Minute = accessFile.readInt();
				Month = accessFile.readInt();
				Day = accessFile.readInt();
				Year=accessFile.readInt();
				found = true;
				break;
			} else {
				found = false;
			}

		}
		if (found == true) {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			if ((tempc == this.customer_IdentificationString)&& (temp_FirstName.equalsIgnoreCase(this.First_Name.trim()))&&(temp_LastName.equalsIgnoreCase(this.Last_Name.trim())) &&(Occupancy == temp_Ocuupancy)  && (Day == temp_Day) &&(Duration_Of_Stay == temp_DurationOfStay ) && (Area_Code == temp_Area_Code)&& (Line == temp_Line)&& (Exchange == temp_Exchange))  // 
			{
				
				Rval=1;

			}else{
				Rval= -1;
			}
		} 
		
		return Rval;

	}
	
	
	
	
	
	
	
	
public void active_Active_Hotel() throws IOException{
	
	
	
		int extract_Data = 0;
		accessFile = new RandomAccessFile(File_source, "r");
		int counter = 0;
	@SuppressWarnings("resource")
	Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\activeHotelMembers.txt"));
	Reservation_Files gfFiles=new Reservation_Files();
	int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\activeHotelMembers.txt");
	
	if(res>0){
		
	//throw new ArithmeticException("Cant divide by zero.");
	
	int[] arr=new int[1000];
		while (FileScanner.hasNext() ){
			
			extract_Data=FileScanner.nextInt();
			arr[counter]=extract_Data;
			counter++;
		}
			for (int i = 0; i <counter; i++) {
			 File_Pointer = arr[i] -1;
			accessFile.seek(File_Pointer*size_Of_Records);
			//////////////////////////////////
				customer_IdentificationString = accessFile.readInt();
				
				Area_Code = accessFile.readInt();
				Exchange = accessFile.readInt();
				Line = accessFile.readInt();
				First_Name = accessFile.readUTF();
				Last_Name = accessFile.readUTF();
				Duration_Of_Stay = accessFile.readInt();
				Occupancy = accessFile.readInt();
				Hour = accessFile.readInt();
				Minute = accessFile.readInt();
				Month = accessFile.readInt();
				Day = accessFile.readInt();
				Year = accessFile.readInt();
				////////////////////////////////////////////////////
/////////////////////////////////////////////////
				
JFrame frame = new JFrame("A C T I V E | H O T E L | R E S E R V A T I O N S");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("Area_Code");
Tmodel.addColumn("Exchange");
Tmodel.addColumn("Line");
Tmodel.addColumn("First Name");
Tmodel.addColumn("Last Name");
Tmodel.addColumn("Length|Of|Stay ");
Tmodel.addColumn("Occupancy");
Tmodel.addColumn("Hour");
Tmodel.addColumn("Minute");
Tmodel.addColumn("Month");
Tmodel.addColumn("Day");
Tmodel.addColumn("Year");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.gray);
frame.add(new JScrollPane(table));




///////////////////////////////////////////////////////
				Tmodel.addRow(new Object[]{customer_IdentificationString,Area_Code,Exchange,Line,First_Name,Last_Name,Duration_Of_Stay,Occupancy,Hour,Month,Minute,Day,Year});
				
				///////////////////////////////////////////////////
			}
			
	}else{
		
		System.out.printf("There are zero records to procesed." + " Error " + " Empty File");
	}
		
		
		
		

}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void DisplayCanceledHotelReservations() throws IOException{
	accessFile = new RandomAccessFile(File_source, "r");
	
	
	int extract_Data = 0;
	// remember to check if file length is zero and a specific customer search
	
	
	int counter = 0;
	@SuppressWarnings("resource")
	Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\CanceledHotelReservations.txt"));
	Reservation_Files gfFiles=new Reservation_Files();
	int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\CanceledHotelReservations.txt");
	if(res>0)
	{
	///res = res / res;
	
	int[] arr=new int[1000];
	
	
	while (FileScanner.hasNext() ){
		
		extract_Data=FileScanner.nextInt();
		arr[counter]=extract_Data;
		counter++;
	}

	for (int i = 0; i <counter; i++) {
		 File_Pointer = arr[i] -1;
		accessFile.seek(File_Pointer*size_Of_Records);
		//////////////////////////////////
			customer_IdentificationString = accessFile.readInt();
			
			Area_Code = accessFile.readInt();
			Exchange = accessFile.readInt();
			Line = accessFile.readInt();
			First_Name = accessFile.readUTF();
			Last_Name = accessFile.readUTF();
			Duration_Of_Stay = accessFile.readInt();
			Occupancy = accessFile.readInt();
			Hour = accessFile.readInt();
			Minute = accessFile.readInt();
			Month = accessFile.readInt();
			Day = accessFile.readInt();
			Year = accessFile.readInt();
			////////////////////////////////////////////////////
			JFrame frame = new JFrame("C A N C E L E D | H O T E L | R E S E R V A T I O N S");
			DefaultTableModel Tmodel = new DefaultTableModel();
			JTable table=new JTable(Tmodel);
			frame.setVisible(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			Tmodel.addColumn("CIS");
			Tmodel.addColumn("Area_Code");
			Tmodel.addColumn("Exchange");
			Tmodel.addColumn("Line");
			Tmodel.addColumn("First Name");
			Tmodel.addColumn("Last Name");
			Tmodel.addColumn("Length|Of|Stay ");
			Tmodel.addColumn("Occupancy");
			Tmodel.addColumn("Hour");
			Tmodel.addColumn("Minute");
			Tmodel.addColumn("Month");
			Tmodel.addColumn("Day");
			Tmodel.addColumn("Year");
			table.setAutoCreateRowSorter(true);
			table.setCellSelectionEnabled(true);
			table.setSelectionBackground(Color.BLUE);
			table.setSelectionForeground(Color.gray);
			frame.add(new JScrollPane(table));
			
			
			Tmodel.addRow(new Object[]{customer_IdentificationString,Area_Code,Exchange,Line,First_Name,Last_Name,Duration_Of_Stay,Occupancy,Hour,Month,Minute,Day,Year});
			
	}
	
	}else{
		
		System.out.printf("There are zero records to procesed." + " Error 7.0 " + " Empty File");
	}
	
	
}

}
