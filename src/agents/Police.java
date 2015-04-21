package agents;

import java.util.Random;
import java.util.Scanner;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class Police extends Agent {
	private AID[] informers; //list of informers
	protected void setup()
	{
		System.out.println("police agent "+getAID().getLocalName()+" is ready");
		addBehaviour(new StartPatrol());
	}
	
	private class StartPatrol extends CyclicBehaviour{
		private static final long serialVersionUID = 1L;
		private String smoke_status;
		private int msg_rcv = 0;
		
		public void action(){
				ACLMessage msg = myAgent.receive();
				
				if(msg != null && msg.getPerformative() == ACLMessage.INFORM){
					ACLMessage inf = new ACLMessage(ACLMessage.INFORM);
					String msg_content = msg.getContent();
					System.out.println("police agent "+getAID().getLocalName()+" has been informed");
					if(msg_content.compareTo("11") == 0){
						inf.addReceiver(new AID("smoker1",AID.ISLOCALNAME));
						inf.setContent("11");
						Globals.incInfmr1();
					}
					else if(msg_content.compareTo("21") == 0){
						inf.addReceiver(new AID("smoker2",AID.ISLOCALNAME));
						inf.setContent("21");
						Globals.incInfmr1();
					}
					else if(msg_content.compareTo("12") == 0){
						inf.addReceiver(new AID("smoker1",AID.ISLOCALNAME));
						inf.setContent("12");
						Globals.incInfmr2();
					}
					else{
						inf.addReceiver(new AID("smoker2",AID.ISLOCALNAME));
						inf.setContent("22");
						Globals.incInfmr2();
					}
					Globals.incCmpltcnt();
					if(Globals.cmplt_cnt < 5)
						myAgent.send(inf);
					else{
						
						if(Globals.infmr1_rewd < Globals.infmr2_rewd){
							System.out.println("\ninformer2 won the game.....!!!!! :)");
						}
						else
							System.out.println("\ninformer1 won the game.....!!!!! :)");
						// Make the agent terminate
						doDelete();
					}	
					
				}
				else
					block();
			}
	}
	
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Agents are terminating.");
	}
}

