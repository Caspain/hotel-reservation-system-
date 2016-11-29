package rervation_types;

import java.io.IOException;

import reservations.*;

public class Spa_Appointment extends Reservation_Type {
///////////////////////////////////////////////
	Spa_Appointment newSpa_Appointment;
//////////////////////////////////////////////	
	
	String Spa_Appointment_Type;
	
	final String Spa_File_source = "C:\\OOP PROJECT FILES\\Spa.txt";
	protected int tempc = 0;
	protected int menu_answer = 0;
	protected boolean True_Break = true;

	public Spa_Appointment() {
		Spa_Appointment_Type = " ";
		Spa_Requested = " ";
	}

	public Spa_Appointment(String spa_Appointment_Type) {
		Spa_Appointment_Type = spa_Appointment_Type;
	}

	public void spa_decider(char spa_type_char) {
		
		System.out.println("Type of Spa Appointment:");
		System.out.println("Guest Appointment: [=>G]:");
		System.out.println("Walk_In_Client Appointment:[=>W]:");
		Spa_Appointment_Type = String_Data_input.nextLine();

		
		
		switch (Spa_Appointment_Type.toUpperCase().charAt(0)) {
		case 'G': {
			newSpa_Appointment  = new Guest();
			switch (spa_type_char) {

			case 'A': {
				((Guest) newSpa_Appointment).Add_Reservation();

			}
				break;

			case 'U': {
				try {
					((Guest) newSpa_Appointment).Update_Reservation();
				} catch (Exception e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;
			case 'V': {

				try {
					((Guest) newSpa_Appointment).view_Reservation();
				} catch (Exception e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;

			case 'D': {

				try {
					((Guest) newSpa_Appointment).Delete_Reservation();
				} catch (Exception e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;

			}// end inner switch

		}
			break;

		case 'W': {
			newSpa_Appointment = new Walk_In_Client();

			switch (spa_type_char) {

			case 'A': {
				((Walk_In_Client) newSpa_Appointment).Add_Reservation();

			}
				break;

			case 'U': {
				try {
					((Walk_In_Client) newSpa_Appointment).Update_Reservation();
				} catch (Exception e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;
			case 'V': {

				try {
					((Walk_In_Client) newSpa_Appointment).view_Reservation();
				} catch (IOException e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;

			case 'D': {

				try {
					((Walk_In_Client) newSpa_Appointment).Delete_Reservation();
				} catch (IOException e) {
					System.out.println("ERROR " + e.getMessage());
				}

			}
				break;

			}// end inner switch

		}
		

		
		default:{
			System.out.println("Not a valid choice.");
		}
		}
	}//end function

}
