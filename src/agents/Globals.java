package agents;

public class Globals {
	public static int informer_count = 2;
	public static int cmplt_cnt = 0;
	public static int infmr1_rewd = 0;
	public static int infmr2_rewd = 0;
	/*
	public static void incInformerCount(){
		informer_count += 1;
	}
	*/
	public static void incCmpltcnt(){
		cmplt_cnt += 1 ;
	}
	
	public static void incInfmr1(){
		infmr1_rewd += 500;
	}
	
	public static void incInfmr2(){
		infmr2_rewd += 500;
	}
}
