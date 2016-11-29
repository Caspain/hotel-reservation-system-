package utility_classes;

public class Reservation_Time {

	public int Minute;
	public int Hour;
	
	
	public  Reservation_Time(){
						this.Minute = 0;
						this.Hour=0;
						
		
	}
	
	public  Reservation_Time(int Minute,int Hour){
		this.Minute = Minute;
		this.Hour=Hour;
	


}
	public  void Set_Reservation_Time(int Minute,int Hour){
		this.Minute = Minute;
		this.Hour=Hour;
	


}
	public String Get_Reservation_Time(){
		return String.format(" %d : %d ",this.Hour,this.Minute);
	
}
}
