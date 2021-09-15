

//Import pakages
import java.util.ArrayList;
import java.util.Iterator;

interface Soccer {
    public void regObs(Observer o); 

    public void notifyObs(); 

    public void unregObs(Observer o); 
}


class SoccerData implements Soccer {
    int TotalgoalsA; 
    int TotalgoalsB; 
    ArrayList<Observer> observerList; 

    public SoccerData() {
        observerList = new ArrayList<Observer>(); 
    }

    public void regObs(Observer obs) {
        observerList.add(obs); 
    }

    public void unregObs(Observer obs) {
        observerList.remove(observerList.indexOf(obs)); 
    }


    public void notifyObs() {
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) {
            Observer obs = it.next();
            obs.update(TotalgoalsA, TotalgoalsB);
        }
    }

    private int getGoalsA() {
        
        return 6;
    }


    private int getGoalsB() {
        
        return 4;
    }

   
    public void dataupdated() {
        
        TotalgoalsA = getGoalsA();
        TotalgoalsB = getGoalsB();

        notifyObs(); 
    }
}


interface Observer {
    public void update(int goalsA, int goalsB);
}


class LeadScore implements Observer {
    private int lScore; 

    public void update(int goalsA, int goalsB) {
        this.lScore = (int) goalsA - goalsB; 

        displayData(); 
    }

    
    public void displayData() {
        System.out.println(
                "\n             Socccer Score Board  \n\n" + "  Lead_Score :" + lScore);
    }
}


class CurrentScore implements Observer {
    private int TotalgoalsA, TotalgoalsB; 

 
    public void update(int goalsA, int goalsB) {
        this.TotalgoalsA = goalsA;
        this.TotalgoalsB = goalsB;
        display();
    }

    public void display() {
        System.out.println("\n              Soccer Current_Score  \n\n" + "Current_TeamA_goals: " + TotalgoalsA
                + "      Current_TeamB_Goals:" + TotalgoalsB +"\n\n");
    }
}

// main class
public class App {
    public static void main(String[] args) throws Exception { 
        LeadScore lScore = new LeadScore();
        CurrentScore cScore = new CurrentScore();

     
        SoccerData SoccerData = new SoccerData();

      
        SoccerData.regObs(lScore);
        SoccerData.regObs(cScore);

        
        SoccerData.dataupdated();

    
        SoccerData.unregObs(lScore);

    }
}