package reservation_Options;

import java.io.FileNotFoundException;
import java.io.IOException;



public interface Manage_Reservations {

	public void Add_Reservation();
	public void Delete_Reservation() throws IOException;
	public void Update_Reservation() throws IOException;
	public void view_Reservation() throws IOException;
	
}
