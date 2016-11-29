package ckeckStatus;

import java.io.BufferedWriter;
import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import basic_classess.Reservation_Files;
import rervation_types.Guest;
import rervation_types.Hotel_Room;
import rervation_types.Walk_In_Client;


public class Customer_Checkin extends Reservation_Files {

	final String Hotel_File_source = "C:\\OOP PROJECT FILES\\NewRandom.txt";
	
	private boolean hfound;
	protected int modified_answer = 0;
	public char test_answer;
	public char Next_Answer = ' ';
	public char answer = ' ';
	protected float n = 0;
	boolean found_customer = false;
	public Scanner menuu_input = new Scanner(System.in);
	String HFile="C:\\OOP PROJECT FILES\\CanceledHotelReservations.txt";
	String spaFile="C:\\OOP PROJECT FILES\\CanceledSpaReservations.txt";
	Hotel_Room hotel_Room = new Hotel_Room();
	Guest newGuest = new Guest();
	Walk_In_Client wclClient = new Walk_In_Client();
	String Spa_Requested;
	int Room_Number = 0;
	int ID = 0;

	public Scanner Str_Data_input = new Scanner(System.in);
	// -----------------temp variables------------------------
	protected int temp_customer_Identification = 0;
	protected int temp_Area_Code = 0;
	int temp_Line = 0;
	int temp_Exchange = 0;
	int temp_Day = 0;
	String temp_FirstName = " ";
	String temp_LastName = " ";
	protected int temp_DurationOfStay = 0;
	protected int temp_Ocuupancy = 0;
	protected String customer_StatusString = " ";
	public int tempc;
	public int customer_IdentificationString = 0;

	// --------------------end-----------------------------------
	public void find_Customer_Reservation() {
		System.out.println("C U S T O M E R | C H E C K I N G");
		System.out.println("Type of Reservation");
		System.out.println("Spa Appointment:[1]");
		System.out.println("Hotel Rooms:[2]");
		System.out.println("Choose:");
try{
		this.Next_Answer = this.menuu_input.nextLine().toUpperCase().charAt(0);
}catch(Exception a){
	System.out.println(a.getMessage());
}
		{
			

			if (Next_Answer == '2') {
				System.out.println("Searching database for all hotel based records");

				try {
					
					System.out.println("Please enter customer_Identification number: ");
							customer_IdentificationString = Integer.parseInt(menuu_input.nextLine().trim());
							

					found_customer = hotel_Room.Search(customer_IdentificationString);
					if (found_customer == true) {
						System.out.println("Record Located.");
						Customer_Checkin_Method();
						modified_answer = hotel_Room.Modified_Search(
								temp_Area_Code, temp_Line, temp_Exchange,
								temp_Day, temp_FirstName, temp_LastName,
								temp_DurationOfStay, temp_Ocuupancy,
								customer_IdentificationString);

						if (modified_answer == 1) {
							System.out
									.println("Good So Far, just one more thing");
							System.out
									.println("Do you wish to cancel this reservation[y/n]:");

							this.Next_Answer = this.menuu_input.nextLine()
									.toUpperCase().charAt(0);
							if (this.Next_Answer == 'Y') {
								customer_StatusString = "Canceled";
							
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								hfound = search_HotelCanceledReservations(customer_IdentificationString,HFile);
								if (hfound == true) {
									System.out.println("YOU HAVE ALREADY CHECKED in");

								} else {
									Write_HotelCanceledReservations(customer_IdentificationString,HFile,true);
									System.out
											.println("ADDING CANCELED RESERVATION " + " RESERVATION AS BEEN CANCELED : " +customer_IdentificationString);
								}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							} else if (this.Next_Answer == 'N') {
								hfound = search_HotelCanceledReservations(customer_IdentificationString,HFile);
								if (hfound == true) {
									System.out.println("YOU HAVE ALREADY CHECKED in");
								}
									else{
								customer_StatusString = "Active";

								System.out.println("Status : "
										+ customer_StatusString);
								
								Write_HotelCanceledReservations(customer_IdentificationString, "C:\\OOP PROJECT FILES\\activeHotelMembers.txt",true);
									}
							}
							
						} else if (modified_answer == -1) {
							System.out.println("Incorrect information submitted");
							// ask them to try again
						}
						// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						if (customer_StatusString.equalsIgnoreCase("Active")) {
							System.out
									.println("Thank You! You've been successfuly checked in.");
						} else if (customer_StatusString.equalsIgnoreCase("Canceled")&& hfound != false) {
							System.out.println("You have chosen to cancel your reservation .");// fix
																								// this
						}
					}

					else {
						System.out
								.println("Incorrect information submitted first");
					}
				} catch (IOException e) {
					System.err.print(e.getMessage());

				} catch (Exception k) {
					System.err.print(k.getMessage());
				}

			} else if (Next_Answer == '1') {

				// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				System.out.println("Type of Spa Appointment:");
				System.out.println("Guest Appointment: (G):");
				System.out.println("Walk_In_Client Appointment:(W):");
				String Spa_Appointment_Type = Str_Data_input.nextLine();

				switch (Spa_Appointment_Type.toUpperCase().charAt(0)) {

				case 'G': {

					System.out
							.println("Searching database for all guest spa records....");
					try {
						System.out.println("Please enter customer_Identification number: ");
						customer_IdentificationString = Integer.parseInt(menuu_input.nextLine().trim());

						found_customer = newGuest.Search(customer_IdentificationString);
						if (found_customer == true) {
							System.out.println("Record Located.");
							Customer_Checkin_Method2();
							modified_answer = newGuest.Modified_Search(
									Room_Number, Spa_Requested, temp_Day,
									temp_FirstName, temp_LastName,
									customer_IdentificationString);

							if (modified_answer == 1) {
								System.out
										.println("Good So Far, just one more thing");
								System.out
										.println("Do you wish to cancel this reservation[y/n]:");

								this.Next_Answer = this.menuu_input.nextLine()
										.toUpperCase().charAt(0);
								if (this.Next_Answer == 'Y') {
					
									customer_StatusString = "Canceled";
							
									
									hfound = search_SpaCanceledReservations(customer_IdentificationString,spaFile);
									if (hfound == true) {
										System.out
												.println("YOU HAVE ALREADY CHECKED in");

									} else {
									
										Write_SpaCanceledReservations(customer_IdentificationString,spaFile);
										System.out.println("ADDING CANCELED RESERVATION " + " RESERVATION AS BEEN CANCELED : " +customer_IdentificationString);
									}
									

								} 
								
								else if (this.Next_Answer == 'N') {
									hfound = search_HotelCanceledReservations(customer_IdentificationString,spaFile);
									if (hfound == true) {
										System.out.println("YOU HAVE ALREADY CHECKED in");
									}else{
									customer_StatusString = "Active";
									
									Write_SpaCanceledReservations(customer_IdentificationString, "C:\\OOP PROJECT FILES\\activeSpaMembers.txt");
									System.out.println("Status : " + customer_StatusString);
									}
									
								}
								
							} else if (modified_answer == -1) {
								System.out.println("Incorrect information submitted");
								
							}
							if (customer_StatusString.equalsIgnoreCase("Active")) {
								System.out.println("Thank You! You've been successfuly checked in.");
							} else if (customer_StatusString.equalsIgnoreCase("Canceled")&& hfound != false) {
								System.out.println("You have chosen to cancel your reservation .");
							}
						}
						
						else {
							System.out.println("Incorrect information submitted.");
						}

					} catch (Exception e) {
						System.out.println(e.getLocalizedMessage());
					}

				}
					break;

				case 'W': {

					System.out.println("Searching database for all walk in based records");
					try {

						System.out
								.println("Please enter customer_Identification number: ");
						customer_IdentificationString = Integer
								.parseInt(menuu_input.nextLine().trim());

						found_customer = wclClient.Search(customer_IdentificationString);
						if (found_customer == true) {
							System.out.println("Record Located.");
							Customer_Checkin_Method3();
							modified_answer = wclClient.Modified_Search(ID,
									customer_IdentificationString,
									temp_Area_Code, temp_Exchange, temp_Line,
									temp_FirstName, temp_LastName,
									Spa_Requested, temp_Day);

							// ----------------------------------
							if (modified_answer == 1) {
								System.out.println("Good So Far, just one more thing");
								System.out.println("Do you wish to cancel this reservation[y/n]:");

								this.Next_Answer = this.menuu_input.nextLine().toUpperCase().charAt(0);
								
								if (this.Next_Answer == 'Y') {
									customer_StatusString = "Canceled";
								
									
									
									hfound = search_SpaCanceledReservations(customer_IdentificationString,spaFile);
									if (hfound == true) {
										System.out.println("YOU HAVE ALREADY CHECKED in");

									} else {
								
																						
										Write_SpaCanceledReservations(customer_IdentificationString,spaFile);
										
										System.out.println("ADDING CANCELED RESERVATION " + " RESERVATION AS BEEN CANCELED : " +customer_IdentificationString);
									}
									
									

								} else if (this.Next_Answer == 'N') {
									
									hfound = search_HotelCanceledReservations(customer_IdentificationString,spaFile);
									if (hfound == true) {
										System.out.println("YOU HAVE ALREADY CHECKED in");
									}else{
										
										Write_SpaCanceledReservations(customer_IdentificationString, "C:\\OOP PROJECT FILES\\activeSpaMembers.txt");
									customer_StatusString = "Active";
									System.out.println("Status : " + customer_StatusString);
									}
									
								
								}
								
							} else if (modified_answer == -1) {
								System.out
										.println("Incorrect information submitted");
								// ask them to try again-----------------------------------------------------||||||||||||||||||||||||||||||||==========
								
							}

					
							
							if (customer_StatusString.equalsIgnoreCase("Active")) {
								System.out
										.println("Thank You! You've been successfuly checked in.");
							} else if (customer_StatusString
									.equalsIgnoreCase("Canceled")
									&& hfound != false) {
								System.out
										.println("You have chosen to cancel your reservation .");
																									
							}
							//-------------------
						} else {

							System.out
									.println("Incorrect information submitted.");
						}

					} catch (Exception e) {
						System.out.println(e.getLocalizedMessage());

					}

				}
					break;

				default: {

					System.out.println("NOT A VALID OPTION");
				}
					break;
				}

				// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
		}
	}

	void Customer_Checkin_Method() {
		try {

			System.out.println("Please Confirm the following");
			System.out.print("Enter Area code: ");
			temp_Area_Code = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter  Exchange code: ");
			temp_Line = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter  Line code: ");
			temp_Exchange = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter First Name: ");
			temp_FirstName = Str_Data_input.nextLine();

			System.out.print("Enter Last Name: ");
			temp_LastName = Str_Data_input.nextLine();

			System.out.print("Enter Duration of stay:");
			temp_DurationOfStay = Integer.parseInt(menuu_input.nextLine()
					.trim());

			System.out.print("Number of individuals:");
			temp_Ocuupancy = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter Day:");
			temp_Day = Integer.parseInt(menuu_input.nextLine().trim());

		} catch (Exception e) {
			System.err.println("Incorect data type entered" + "Error 6.6");
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	void Customer_Checkin_Method2() {
		try {

			System.out.println("Please Confirm the following");

			System.out.print("Enter Room Number: ");
			Room_Number = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter First Name: ");
			temp_FirstName = Str_Data_input.nextLine();

			System.out.print("Enter Last Name: ");
			temp_LastName = Str_Data_input.nextLine();

			

			System.out.print("Enter Day:");
			temp_Day = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Type of Spa: ");
			Spa_Requested = Str_Data_input.nextLine();

		} catch (Exception e) {
			System.err.println("Incorect data type entered"  + "Error 6.6");
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	void Customer_Checkin_Method3() {
		try {// pass customer_Identification number here.

			System.out.println("Please Confirm the following");

			System.out.print("Enter ID: ");
			ID = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter Area code: ");
			temp_Area_Code = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter Line code: ");
			temp_Line = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter  Exchange code: ");
			temp_Exchange = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Enter First Name: ");
			temp_FirstName = Str_Data_input.nextLine();

			System.out.print("Enter Last Name: ");
			temp_LastName = Str_Data_input.nextLine();

			System.out.print("Enter Day:");
			temp_Day = Integer.parseInt(menuu_input.nextLine().trim());

			System.out.print("Type of Spa: ");
			Spa_Requested = Str_Data_input.nextLine();

		} catch (Exception e) {
			System.err.println("Error 6.6" +" Incorect data type entered" );
		}
	}

	public void Write_HotelCanceledReservations(int valc,String fileH,boolean val) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileH,val));
			writer.write(new Integer(valc).toString());
			writer.write(System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {

			System.err.print(e.getMessage());
		}

	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	boolean search_HotelCanceledReservations(int val,String fileH) {

		int TempVal = 0;

		try {
			Scanner inScanner = new Scanner(new File(
				 fileH));
			int res = Create_File(fileH);
			if (res != 0) {
				while (inScanner.hasNext()) {

					TempVal = inScanner.nextInt();
					if (val == TempVal) {
						found_customer = true;
						break;
					} else {
						found_customer = false;
					}
				}
			} else {
				found_customer = false;
			}
			inScanner.close();

		} catch (IOException e) {
			System.out.print("error file not found ");

		}

		return found_customer;

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	boolean search_SpaCanceledReservations(int val,String Spaval) {

		int TempVal = 0;

		try {
			Scanner inScanner = new Scanner(new File(Spaval));
			int res = Create_File(Spaval);
			if (res != 0) {
				while (inScanner.hasNext()) {

					TempVal = inScanner.nextInt();
					if (val == TempVal) {
						found_customer = true;
						break;
					} else {
						found_customer = false;
					}
				}
			} else {
				found_customer = false;
			}
			inScanner.close();

		} catch (IOException e) {
			System.out.print("error file not found ");

		}

		return found_customer;

	}
//---------------------------------------------------------------------------------------------------------------------------------------

	public void Write_SpaCanceledReservations(int valc,String fileH) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileH,true));
			writer.write(new Integer(valc).toString());
			writer.write(System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {

			System.err.print(e.getMessage());
		}

	}

}