package agents;

import java.util.Random;
import java.util.Scanner;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class Smoker extends Agent {
	private AID[] informers; //list of informers
	protected void setup()
	{
		System.out.println("smoker agent "+getAID().getLocalName()+" is ready");
		addBehaviour(new StartSmoke());
	}
	
	private class StartSmoke extends CyclicBehaviour{
		private static final long serialVersionUID = 1L;
		private String smoke_status;
		private int msg_snd = 0;
		
		public void action(){
			if(Globals.cmplt_cnt<5){
			  if(msg_snd == 0){
				System.out.println("\nDo you want me("+getAID().getLocalName()+") to smoke yes or no?");
				Scanner in = new Scanner(System.in);
				smoke_status = in.nextLine();
				if( smoke_status.compareTo("yes") == 0 ){
					Random rand = new Random();
					int info_no = rand.nextInt(Globals.informer_count) + 1;
				
					ACLMessage inf = new ACLMessage(ACLMessage.INFORM);
					if(info_no == 1){
						inf.addReceiver(new AID("informer1",AID.ISLOCALNAME));
					}
					else
						inf.addReceiver(new AID("informer2",AID.ISLOCALNAME));
					inf.setContent( getAID().getLocalName() );
					myAgent.send(inf);
					msg_snd = 1;
				}
				/*else{
					System.out.println("no");
				}*/
			}
			else{
				ACLMessage msg = myAgent.receive();
				if(msg != null && msg.getPerformative() == ACLMessage.INFORM){
					String msg_content = msg.getContent();
					System.out.println("smoker agent "+getAID().getLocalName()+" has been arrested");
					msg_snd = 0;
				}
				else
					block();
			}
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

