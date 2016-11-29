package utility_classes;

public class FullName {

	String First_Name;
	String Last_Name;
	public FullName()
	{
		String First_Name = " ";
		String Last_Name = " ";
	}
	public FullName(String First_Name,	String Last_Name)
	{
		this.First_Name = First_Name;
		this.Last_Name =Last_Name;
	}
	
	public void Set_FullName(String First_Name,	String Last_Name)
	{
		this.First_Name = First_Name;
		this.Last_Name =Last_Name;
	}
	public String Get_FullName()
	{
		return String.format(" First name : %s Last Name : %s",this.First_Name,this.Last_Name);
	}
}
