package agents;

import java.util.Random;
import java.util.Scanner;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class Informer extends Agent {
	private AID[] informers; //list of informers

	protected void setup()
	{
		System.out.println("informer agent "+getAID().getLocalName()+" is ready");
		addBehaviour(new StartInform());
	}
	
	private class StartInform extends CyclicBehaviour{
		private static final long serialVersionUID = 1L;
		private String smoke_status;
		private int msg_rcv = 0;
		
		
		public void action(){
			if(Globals.cmplt_cnt < 5){
				ACLMessage msg = myAgent.receive();
				
				if(msg != null && msg.getPerformative() == ACLMessage.INFORM){
					ACLMessage inf = new ACLMessage(ACLMessage.INFORM);
					inf.addReceiver(new AID("police1",AID.ISLOCALNAME));
					String msg_content = msg.getContent();
					System.out.println("informer agent "+getAID().getLocalName()+" has been informed");
					if(msg_content.compareTo("smoker1") == 0){
						if( getAID().getLocalName().compareTo("informer1") == 0){
							inf.setContent("11");
						}
						else
							inf.setContent("12");
					}
					
					else{
						if( getAID().getLocalName().compareTo("informer1") == 0){
							inf.setContent("21");
						}
						else
							inf.setContent("22");
					}
					myAgent.send(inf);
				}
				else
					block();
			}
			else
				doDelete();
		}
		protected void takeDown() {
			// Printout a dismissal message
			System.out.println("Agents are terminating.");
		}
		
	}
}

