import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
public class GameWheel {
    private ArrayList<Slice> slices;
    private int currentPos;
    private ArrayList<Slice> red = new ArrayList<Slice>();
    private ArrayList<Slice> blue = new ArrayList<Slice>();
    private ArrayList<Slice> black = new ArrayList<Slice>();


    public GameWheel(){
        this.slices = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            boolean even;
            if (i % 2 == 0) {
                even = true;
            }else{
                even = false;
            }

            if (i % 5 == 0) {
                Slice e = new Slice("Black", 1000 * i);
                this.slices.add(e);
            }else if(even){
                Slice e = new Slice("Blue", 100 * i);
                this.slices.add(e);
            }else{
                Slice e = new Slice("Red", 200 * i);
                this.slices.add(e);
            }

        }
    }

    public String toString(){
        String temp = "";
        for (int i = 0; i < this.slices.size(); i++) {
            temp = temp + i + " - " + this.slices.get(i).toString() + "\n";
        }
        return temp;
    }

    public void split(){
        for (Slice slice : this.slices) {
            if (slice.getColor().equals("Black")) {
                this.black.add(slice);
            }else if(slice.getColor().equals("Red")){
                this.red.add(slice);
            }else if(slice.getColor().equals("Blue")){
                this.blue.add(slice);
            }
        }
    }
    //empty split lists
    public void scramble(){
        resetSplitLists();
        split();
        shuffleListPos();
        this.slices.clear();
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0) {
                this.slices.add(black.get(0));
                this.black.remove(0);
            }else if(i % 2 == 0){
                this.slices.add(blue.get(0));
                this.blue.remove(0);
            }else{
                this.slices.add(red.get(0));
                this.red.remove(0);
            }
        }
    }

    public void sort(){
        resetSplitLists();
        split();
        sortListPos();
        this.slices.clear();
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0) {
                this.slices.add(black.get(0));
                this.black.remove(0);
            }else if(i % 2 == 0){
                this.slices.add(blue.get(0));
                this.blue.remove(0);
            }else{
                this.slices.add(red.get(0));
                this.red.remove(0);
            }
        }
    }

    public Slice spinWheel(){
        this.currentPos = 0 + (int)(Math.random() * ((19 - 0) + 1));
        return getSlice(this.currentPos);

    }

    private void resetSplitLists(){
        this.red.clear();
        this.blue.clear();
        this.black.clear();
    }

    private void shuffleListPos(){
        Collections.shuffle(this.black);
        Collections.shuffle(this.red);
        Collections.shuffle(this.blue);
    }
    private void sortListPos(){
        
        for (int i = 0; i < this.black.size(); i++) {
            for (int j = i + 1; j < this.black.size(); j++) {
                if (this.black.get(i).getPrizeAmount() < this.black.get(j).getPrizeAmount()) {
                    Collections.swap(black, i, j);
                }
            }
        }

        for (int i = 0; i < this.red.size(); i++) {
            for (int j = i + 1; j < this.red.size(); j++) {
                if (this.red.get(i).getPrizeAmount() < this.red.get(j).getPrizeAmount()) {
                    Collections.swap(red, i, j);
                }
            }
        }

        for (int i = 0; i < this.blue.size(); i++) {
            for (int j = i + 1; j < this.blue.size(); j++) {
                if (this.blue.get(i).getPrizeAmount() < this.blue.get(j).getPrizeAmount()) {
                    Collections.swap(blue, i, j);
                }
            }
        }
    }

    public Slice getSlice(int i){
        if(i > 19 || i < 0){
            return this.slices.get(0);
        }else{
            return this.slices.get(i);
        }
    }


}
