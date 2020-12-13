/*
 * Programmer: Sanskar Aryal
 * Class: Cosc 3355
 * Date: 02/05/2020
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CPU {
	static int PC; 
	static String IR;
	static int AC;
	static int REG;
	static Stack stk = new Stack();
	static boolean flag=true;
	
	public static void main(String args[]){
		int start = 0;
		int no_of_instructions=0;
		int no_of_subroutines=0;
		boolean notfoundstart = true;
		String nextword;
		File file = new File("Sanskar_Aryal_input.txt");
		
		try(Scanner sc = new Scanner(file)){
			while (sc.hasNextLine()) {
				
				nextword = sc.nextLine();
				if (!(nextword.charAt(0)=='=') && notfoundstart){
					String fword[] = nextword.split("[  ]+");
					start = Integer.parseInt(fword[1], 16);
					notfoundstart = false;	
				}
				if (!(nextword.charAt(0)=='=')){	
					String words[] = nextword.split("[  ]+");
					Memory.loadmem(Integer.parseInt(words[1], 16),words[2]); //converting hex into decimal and passing
				}
			}
		}
		catch (IOException e) {	
		}
		PC = start;
		while (flag){
			no_of_instructions++;
			IR = Memory.location[PC];
			int opcode;
			int latter;
			opcode = Integer.parseInt(IR.substring(0,1),16); //getting opcode
			latter = Integer.parseInt(IR.substring(1,4),16); //getting latter part of the memory
			PC++;
			switch (opcode)
			{
				case 1:
					//load AC from memory
					if (Memory.location[latter] != null) {
						AC= Integer.parseInt(Memory.location[latter],16);
					}
					//System.out.println(AC);
					break;
				case 2:
					// Store AC to memory
					Memory.location[latter]=Integer.toHexString(AC).toUpperCase();
					break;
				case 3:
					//Load AC from REG
					AC = REG;
					break;
				case 4:
					//Store AC to REG
					REG = AC;
					break;
				case 5:
					//Add to AC from memory
					AC = AC + Integer.parseInt(Memory.location[latter],16);
					break;
				case 6:
					//Load REG with operand
					REG= latter;
					break;
				case 7: 
					//Add REG to AC
					AC = AC + REG;
					break;
				case 8:
					//Multiply REG to AC
					AC = AC * REG;
					break;
				case 9:
					//Subtract REG from AC
					AC = AC - REG;
					break;
				case 10:
					//Divide AC by REG value (int division)
					AC = AC / REG;
					break;
				case 11:
					// jump to subroutine starting at the address
					int prevPC = PC -1;
					stk.push(Integer.toHexString(prevPC));
					stk.push(Memory.location[prevPC]);
					PC = latter;
					break;
				case 12:
					no_of_subroutines++;
					//Return from subroutine and load from stack
					System.out.println("======Before Return from Subroutine " +no_of_subroutines +" Status======");
					printstatus(no_of_instructions);
					IR = stk.pop().toString();
					PC = (Integer.parseInt(stk.pop().toString(),16))+1;
					break;
				case 15:
					flag =false;
					break;
			}	
		}
		System.out.println("======End of Program Status======");
		printstatus(no_of_instructions);
		
	}
	public static void printstatus(int no_of_instructions){
		System.out.println("=============Stack Status=============");
		if (stk.isEmpty()){
			System.out.println("No data in Stack!");
		}
		else {
		for (int j= 1020;j<=1023;j++){
			Memory.location[j] = Memory.location[j] == null?"0":Memory.location[j];
			System.out.println("Stack contents at "+Integer.toHexString(j).toUpperCase() + "= " +Memory.location[j].toUpperCase());
		}
		}
		System.out.println( "=============Registers & Memory Status=============");
		System.out.println("Accumulator= "+Integer.toHexString(AC).toUpperCase());
		System.out.println("Register= "+Integer.toHexString(REG).toUpperCase());
		System.out.println("PC= "+Integer.toHexString(PC).toUpperCase());
		System.out.println("IR= "+ IR);
		System.out.println("Memory 940= "+Memory.location[2368]);
		System.out.println("Memory 941= "+Memory.location[2369]);
		System.out.println("Memory 942= "+Memory.location[2370]);
		System.out.println("No. of Instructions executed= "+ no_of_instructions);
		System.out.println();
	}
}