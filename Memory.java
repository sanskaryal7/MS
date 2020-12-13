/*
 * Programmer: Sanskar Aryal
 * Class: Cosc 3355
 * Date: 02/05/2020
 */
public class Memory{
	public static String location[] = new String[5000];
	
	public static void loadmem(int address, String da_ins) 
	{
		location[address] = da_ins;
	}
}