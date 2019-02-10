/**
 * 
 */
import java.io.*;
import java.util.ArrayList;
/**
 * @author Visweswar Nemani
 *
 */
public class GoldenCrown {

	public static boolean kingdomAir(String check) {
		if(check.indexOf("o")!=-1 && check.indexOf("w")!=-1 && check.indexOf("l")!=-1) {
			return true;
		}else 
			return false;
	}
	public static boolean kingdomLand(String check) {
		if(check.indexOf("p")!=-1 && check.indexOf("n")!=-1 && check.indexOf("d")!=-1 && check.indexOf("a")!=-1 && check.indexOf("a",check.indexOf("a")+1) !=-1) {
			return true;
		}else
			return false;
	}
	public static boolean kingdomIce(String check) {
		if(check.indexOf("a")!=-1 && check.indexOf("o")!=-1 && check.indexOf("t")!=-1 && check.indexOf("h")!=-1) {
			if(check.indexOf("m")!=-1) {  //  first occurance of m
				int temp=check.indexOf("m");
				if(check.indexOf("m",temp+1)!=-1) {      //second occurance of m
					temp=check.indexOf("m",temp+1);
					if(check.indexOf("m",temp+1)!=-1) {     // third occurance of m
						System.out.println("Mammoth found!!");
						return true;
					} else return false;
				} else return false;
			} else return false;
		} else return false;
	}
	public static boolean kingdomFire(String check) {
		if(check.indexOf("d")!=-1 && check.indexOf("r")!=-1 && check.indexOf("a")!=-1 && check.indexOf("g")!=-1 && check.indexOf("o")!=-1 && check.indexOf("n")!=-1) {
		  return true;
		} else return false;
	}
	public static boolean kingdomWater(String check) {
		if(check.indexOf("o")!=-1 && check.indexOf("c")!=-1 && check.indexOf("t")!=-1 && check.indexOf("o")!=-1 && check.indexOf("p")!=-1 && check.indexOf("u")!=-1 && check.indexOf("s")!=-1) {
		  return true;
		} else return false;
	}
	
	public static String textReader(String s) {
		String afterSplit[]=s.split(",");
		String secondPart=afterSplit[1].toLowerCase().substring(1,afterSplit[1].length()-1);
		if(afterSplit[0].equalsIgnoreCase("Air")) {
			if(kingdomAir(secondPart)==true) {
				return "Air";				
			} else return "lost";
		}
		if(afterSplit[0].equalsIgnoreCase("Land")) {
			if(kingdomLand(secondPart)==true) {
				return "Land";
			} else return "lost";
		}
		if(afterSplit[0].equalsIgnoreCase("Ice")) {
			if(kingdomIce(secondPart)==true) {
				return "Ice";
			} else return "lost";
		}
		if(afterSplit[0].equalsIgnoreCase("Fire")) {
			if(kingdomFire(secondPart)== true) {
				return "Fire";
			} else return "lost";
		}
		if(afterSplit[0].equalsIgnoreCase("water")) {
			if(kingdomWater(secondPart)==true) {
				return "Water";
			} else return "lost";
		} 
		return "none";
	}
	
	public static String alliedResult( ArrayList <String> input) {
		int lostCount=0;
		for(int i=0;i<input.size();i++) {
			if(input.get(i).compareToIgnoreCase("lost")== 0) {
				lostCount=lostCount+1;
			}
		}
		if(lostCount>2 || lostCount==0) {
			return "none";
		} else {
			return "king Shah";
		}
		
	}
	public static String kingdomsAllied(ArrayList <String> list) {
		ArrayList <String> temp= new ArrayList <>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).compareToIgnoreCase("lost")!=0) {
				temp.add(list.get(i));	
			}
		}
		if(temp.size()>0) {
			return temp.toString().replace("[","").replace("]","");
		}
		return "none";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text="";
		int inputNumber=0;
		ArrayList <String> alliedKingdoms=new ArrayList <> ();
        ///// user view starts
		System.out.println(" Who is the ruler of Southeros? ");
		System.out.println(" Output : "+alliedResult(alliedKingdoms));
		System.out.println(" Allies of Ruler? ");
		System.out.println(" Output : "+kingdomsAllied(alliedKingdoms));
		////messages section
		System.out.println(" Input Messages to kingdoms from King Shan :");
		try {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		while(inputNumber < 5) {
			System.out.println(" Input : ");
			text=input.readLine();
			if(text.compareToIgnoreCase("stop")==0) {
				break;
			}
			alliedKingdoms.add(textReader(text));
			inputNumber++;
		}
		
		System.out.println(" Who is the ruler of Southeros? ");
		System.out.println(" Output : "+ alliedResult(alliedKingdoms));
		System.out.println(" Allies of King Shah? ");
		System.out.print(" Output : "+kingdomsAllied(alliedKingdoms));
					
		} catch(IOException io) {
			System.out.println(io);
			
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("Message entered in wrong format!!!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
