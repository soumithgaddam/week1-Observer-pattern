
//Observer pattern program to analyze criccket statistics and data

//Import pakages
import java.util.ArrayList;
import java.util.Iterator;

//Java code To implement observer pattern of Cricket Data For Match T20
//create interface having different  methods
interface Soccer {
    public void regObs(Observer o); // function1 for registeroberevers

    public void notifyObs(); // function2 for notifyingobservers

    public void unregObs(Observer o); // function3 for unregister obsever

}

// create class For cricket data inherit inteface cricket
class SoccerData implements Soccer {
    int TotalgoalsA; // variable declare to store total runs
    int TotalgoalsB; // variable declare to store wickets
    ArrayList<Observer> observerList; // declae array for observerlist

    public SoccerData() {
        observerList = new ArrayList<Observer>(); // object
    }

    public void regObs(Observer obs) {
        observerList.add(obs); // add registerobservers
    }

    public void unregObs(Observer obs) {
        observerList.remove(observerList.indexOf(obs)); // remove unregister observers from stadium
    }

    // Notify obsevsers by giving updates.
    public void notifyObs() {
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) {
            Observer obs = it.next();
            obs.update(TotalgoalsA, TotalgoalsB);
        }
    }

    // get latest runs
    private int getGoalsA() {
        // return 200 for simplicity
        return 6;
    }

    // get latest wickets
    private int getGoalsB() {
        // return 4 for simplicity
        return 4;
    }

    // This function is to update displays when data updated
    public void dataupdated() {
        // get latest data
        TotalgoalsA = getGoalsA();
        TotalgoalsB = getGoalsB();

        notifyObs(); // call notify method
    }
}

// create another interface for when data updates
interface Observer {
    public void update(int goalsA, int goalsB);
}

// display average score
class LeadScore implements Observer {
    private int lScore; // variable for runrate

    public void update(int goalsA, int goalsB) {
        this.lScore = (int) goalsA - goalsB; // calculate runrate

        displayData(); // call display to display data
    }

    // display method
    public void displayData() {
        System.out.println(
                "\n             Socccer Score Board  \n\n" + "  Lead_Score :" + lScore);
    }
}

// create current class for cuentscore
class CurrentScore implements Observer {
    private int TotalgoalsA, TotalgoalsB; // variables

    // update current score
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
    public static void main(String[] args) throws Exception { // main function here
        // create objects for testing
        LeadScore lScore = new LeadScore();
        CurrentScore cScore = new CurrentScore();

        // pass the displays to Cricket data
        SoccerData SoccerData = new SoccerData();

        // register display elements
        SoccerData.regObs(lScore);
        SoccerData.regObs(cScore);

        // call this function when data updated
        SoccerData.dataupdated();

        // call to unregobserver to emove
        SoccerData.unregObs(lScore);

    }
}