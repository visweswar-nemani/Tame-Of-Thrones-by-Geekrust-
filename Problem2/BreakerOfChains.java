
package Problem2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class BreakerOfChains extends test implements Comparable <BreakerOfChains> {
	private int winCount;
	private String kingdomName;
    static Message empty = new Message("","","");
	Message m;

	private BreakerOfChains(){
		//Empty parameter constructor
	}

    private BreakerOfChains(int winCount,String kingdomName, Message m){
	    this.winCount=winCount;
	    this.kingdomName = kingdomName;
	    this.m=m;
    }

    public int compareTo(BreakerOfChains obj){
        int compareCount = ((BreakerOfChains) obj).winCount;
        return compareCount-this.winCount;                  //descending
    }

	private static ArrayList<String> receivers(String kingdomName, String kingdoms[]) {               //receivers ready
		ArrayList<String> receiverNames = new ArrayList<>();
		for (String n : kingdoms) {
			if (kingdomName.compareToIgnoreCase(n) != 0) {
				receiverNames.add(n);
			}
		}
		return receiverNames;
	}

	private  static Message message (String sender, String receiver, String messageBody) {
		Message  temp=new Message(sender,receiver,messageBody)	;
		return temp;
	}

    private  static ArrayList <Message> rule2( ArrayList<Message>  messages, ArrayList<String> input){
        for(int i =0;i<input.size();i++){
            for(int j=0;j<messages.size();j++){
                if(input.get(i).compareToIgnoreCase(messages.get(j).receiver)==0){
                    messages.remove(j);
                    j=0;
                }
            }
        }
        return messages;
    }

	private  static ArrayList <Message> rule3(ArrayList<Message>messages){
        for(int i =0;i<messages.size();i++){
            for(int j=0;j<messages.size();j++){
                if(messages.get(i).sender.compareToIgnoreCase(messages.get(j).sender)!=0 && messages.get(i).receiver.compareToIgnoreCase(messages.get(j).receiver)==0){
                    messages.remove(j);
                    i=0; j=0;
                }
            }
        }
	    return messages;
    }

    private   static Message  rule1(Message  message, HashMap<String,String> emblems){                     ///message check
	    int count=0;
        HashMap<Character, Integer> emblemCount = new HashMap<>();
        HashMap<Character, Integer> charCount = new HashMap<>();
	    if(emblems.containsKey(message.receiver)){
	        char emblem [] =  emblems.get(message.receiver).toLowerCase().toCharArray();
	        for( char c: emblem){
                if(emblemCount.containsKey(c)){
                    emblemCount.put( c,emblemCount.get(c)+1);                            //emblem name into hash
                }else {
                    emblemCount.put( c,1);
                }
            }
	        char messageCount[] =  message.content.toLowerCase().toCharArray();
	         for( char c :messageCount){
	             if(charCount.containsKey(c)) {
	                charCount.put(c,charCount.get(c)+1);                   // messsage into hash
	             } else {
                   charCount.put(c,1);
	             }
	         }
	       for( char c: emblemCount.keySet()) {
	           if(charCount.containsKey(c)) {
                   if(emblemCount.get(c) <= charCount.get(c)) {
                       count++;
                   }
               }
           }
	       if(emblemCount.size() == count){
               return message;
           }
        }
	    return  empty;
    }

    private  static ArrayList<Message> ballotBox(ArrayList <Message> messages){
        ArrayList <Message> messagesBox = new ArrayList<>();
	    Collections.shuffle(messages);                           //randomizing

        for(int count=0;count<6;count++){                                                           ///6 messages picked by priest
            messagesBox.add(messages.get(count));
        }
        return messagesBox;
    }

    private static ArrayList<BreakerOfChains> reset (ArrayList<BreakerOfChains> empireList){
	    for(int i=0;i<empireList.size();i++){
	        empireList.get(i).winCount  =0;
            empireList.get(i).m  =null;
        }
	    return empireList;
    }

    private static BreakerOfChains winner(ArrayList <BreakerOfChains> emperorSort){
        BreakerOfChains temp = emperorSort.get(0);
        boolean x =false ;
        for(int j = 1;j < emperorSort.size(); j++) {
            if(temp.winCount < emperorSort.get(j).winCount) {                                  //finding highest value
                temp = emperorSort.get(j);
            } else if(temp.winCount == emperorSort.get(j).winCount ) {
                x = true;
            }
        }
        return temp;
    }

    private static ArrayList<String> updatedInput(ArrayList<String> names,ArrayList <BreakerOfChains> emperorSort){
        BreakerOfChains temp = emperorSort.get(0);
        boolean x =false ;
        for(int j = 1;j < emperorSort.size(); j++) {
            if(temp.winCount < emperorSort.get(j).winCount) {                                  //finding highest value
                temp = emperorSort.get(j);

            } else if(temp.winCount == emperorSort.get(j).winCount ) {
                x = true;
            }
        }

        if(x) {
            for(int i=0;i<emperorSort.size();i++){
                for(int j=i+1;j<emperorSort.size();j++){
                    if(emperorSort.get(i).winCount == emperorSort.get(j).winCount) {               //Allies  tie case
                        if(!(names.contains(emperorSort.get(i).kingdomName))){
                            names.add(emperorSort.get(i).kingdomName);
                        }
                        if(!(names.contains(emperorSort.get(j).kingdomName))){
                            names.add(emperorSort.get(j).kingdomName);
                        }
                    }
                }
            }
        }

	    return names;
    }



    public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> competingRulers = new ArrayList<>();
		 String kingdoms []= {"water","space","ice","land","fire","air"};
        HashMap <String,String> emblem = new HashMap<>() ;
		 ArrayList<String> bodyOfMessage ;
        ArrayList<BreakerOfChains> empires = new ArrayList<>();
		String input;
		ArrayList<String> inputNames= new ArrayList<>();
        ArrayList<Message> resultKingdoms = new ArrayList<>();
		ArrayList <Message> allMessages=new ArrayList<>();
        ArrayList<ArrayList <String>> receiverNames=new ArrayList<>();
        ArrayList<BreakerOfChains> emperorSort = new ArrayList<>();
        ArrayList <Message> selectedMessages;
        int loopCount=0;     // ballot round count
        //BreakerOfChains ballot = new BreakerOfChains();
        BreakerOfChains air = new BreakerOfChains(0,"air",null);
        BreakerOfChains fire = new BreakerOfChains(0,"fire",null);
        BreakerOfChains water = new BreakerOfChains(0,"water",null);
        BreakerOfChains ice = new BreakerOfChains(0,"ice",null);
        BreakerOfChains land = new BreakerOfChains(0,"land",null);
        BreakerOfChains space = new BreakerOfChains(0,"space",null);
        emblem.put("air","owl");
        emblem.put("fire","dragon");
        emblem.put("water","octopus");
        emblem.put("ice","mammouth");
        emblem.put("land","panda");
        emblem.put("space","gorilla");

        empires.add(air);
        empires.add(fire);
        empires.add(water);
        empires.add(ice);
        empires.add(land);
        empires.add(space);

		// user view
		System.out.println("Who is the ruler of Southeros?");
		System.out.println("Output: ");
		System.out.println("Allies of ruler?");
		System.out.println("none");
		System.out.println("Enter the kingdoms competing to be the ruler: ");
		System.out.println("Input  :  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
			String afterSplit[] = input.split(" ");
			for (String i : afterSplit) {                          // input split read and  inserted into arraylist
                competingRulers.add(i);
            }
		    for (int i = 0; i < competingRulers.size(); i++) {
			    for (int j = 0; j < empires.size(); j++) {                                        ///input kingdoms compared with all kingdoms
				    if (competingRulers.get(i).equalsIgnoreCase(empires.get(j).kingdomName)) {
				        inputNames.add(empires.get(j).kingdomName);                                           //sending kingdoms ready
				    }
			    }
		    }
		    if(inputNames.size() == 1){
		        System.out.println("Competing Rulers count :  "+inputNames.size());             // if only single competent
		        System.out.println(" Ruler of Southeros  is  :  "+inputNames.get(0) );
		        System.exit(0);
            }

        loop:do {
                loopCount++;
                for(String  s :inputNames){                                                                  //receiver kingdoms ready
                    receiverNames.add(receivers(s,kingdoms));
                }
                bodyOfMessage=test.fileTest("boc-messages.txt");   //bodyofmessage ready

                for(int i=0,j=0;i<inputNames.size();i++,j++){					///messages in ballot
                    for(int z=0;z<receiverNames.get(j).size();z++) {
                        allMessages.add(message(inputNames.get(i), receiverNames.get(j).get(z), bodyOfMessage.get((int) (Math.random() * (bodyOfMessage.size() - 1)))));
                    }
                }
                selectedMessages= ballotBox(allMessages);                                   // random messages selected

                //// checking for allegiance
                selectedMessages=rule2(selectedMessages,inputNames);                                                      // check for rule 2

                selectedMessages=rule3(selectedMessages);                                                         //check for rule 3


                for(Message msg : selectedMessages){
                    for(BreakerOfChains obj: empires)
                        if(msg.receiver.compareToIgnoreCase(obj.kingdomName) == 0){                 //sending messages to kingdoms
                            obj.m = msg;
                        }
                }

                for( int i=0;i<selectedMessages.size();i++){                                    //check for rule 1
                    resultKingdoms.add(rule1(selectedMessages.get(i),emblem));
                }
                if(resultKingdoms.size() != 0) {
                    for (int i=0;i<resultKingdoms.size();i++) {                                              ///giving win count to kingdoms
                        for (int j=0;j<empires.size();j++) {
                            if (resultKingdoms.get(i).sender.isEmpty()) {
                            } else if (resultKingdoms.get(i).sender.compareToIgnoreCase(empires.get(j).kingdomName) == 0) {
                                    empires.get(j).winCount++;
                            }
                        }
                    }
                }

                System.out.println("Reults after round  "+loopCount+" ballot");
                for( int i=0;i<inputNames.size();i++){                              ///for sorting  of competing rulers
                    for(int j=0;j<empires.size();j++){
                        if(inputNames.get(i).compareToIgnoreCase(empires.get(j).kingdomName)==0){
                            emperorSort.add(empires.get(j));
                            System.out.println("Output: Allies for "+empires.get(j).kingdomName.toUpperCase()+"    :    "+empires.get(j).winCount);
                        }
                    }
                }
                 Collections.sort(emperorSort);               //sorted
                inputNames.clear();
                for(int i=0;i<resultKingdoms.size();i++){
                    if(resultKingdoms.get(i).sender.length() != 0){                                                                                // for check
                    }
                }


                inputNames = updatedInput( inputNames, emperorSort);

                 if(inputNames.size()==0){
                     break loop;
                 }
                 // clearing for next iteration
                empires = reset(empires);
                emperorSort.clear();
                resultKingdoms.clear();
                selectedMessages.clear();
                allMessages.clear();
                bodyOfMessage.clear();
		         } while(inputNames.size() !=0);

		        System.out.println("who is the ruler of Southeros?      ");
		        System.out.println(" Output  :    "+winner(emperorSort).kingdomName.toUpperCase());
                System.out.println("Allies of Ruler  :       ");
                System.out.print("Output  :  ");
		        for(int i=0;i < resultKingdoms.size(); i++) {
                    System.out.print(resultKingdoms.get(i).receiver.toUpperCase()+"   ");
                }
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}


