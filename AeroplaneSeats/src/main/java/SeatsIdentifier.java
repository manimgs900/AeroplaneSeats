import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SeatsIdentifier {
    static List<Seats> allSeats = new ArrayList<>();
    static List<Seats> aisleSeats = new ArrayList<>();
    static List<Seats> windowSeats = new ArrayList<>();
    static List<Seats> centreSeats = new ArrayList<>();

    static int seatCount = 1;

    public static void main(String[] args) {
        int seats[][] = {{3,2}, {4,3}, {2,3}, {3,4}};

        identifySeats(seats);
        addAisleSeatCount();
        addWindowSeatCount();
        addCentreSeatCount();

        showSeatCount(seats);
    }

    public static void identifySeats(int seats[][]) {

        for(int k=0; k<seats.length; k++) {
            int entry[] = seats[k];

            int col = entry[0];
            int row = entry[1];

            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(k==0){
                        if(j==0){
                            createSeat(k, i, j, "Window");
                        } else if(j==col-1){
                            createSeat(k, i, j, "Aisle");
                        } else {
                            createSeat(k, i, j, "Centre");
                        }
                    } else if (k == seats.length-1) {
                        if(j==col-1){
                            createSeat(k, i, j, "Window");
                        } else if(j==0){
                            createSeat(k, i, j, "Aisle");
                        } else {
                            createSeat(k, i, j, "Centre");
                        }
                    } else {
                        if(j==0 || j==col-1){
                            createSeat(k, i, j, "Aisle");
                        } else {
                            createSeat(k, i, j, "Centre");
                        }
                    }
                }
            }
        }
    }

    public static void createSeat (int group, int row, int col, String type) {
        Seats seats = new Seats();
        seats.seatGroup = group;
        seats.row = row;
        seats.col = col;
        seats.type = type;

        if(seats.type.equalsIgnoreCase("Aisle")) {
            aisleSeats.add(seats);
        } else if(seats.type.equalsIgnoreCase("Window")) {
            windowSeats.add(seats);
        } else {
            centreSeats.add(seats);
        }

        allSeats.add(seats);
    }

    public static void addAisleSeatCount() {
        aisleSeats = aisleSeats.stream().sorted(Comparator.comparingInt(Seats::getRow)).collect(Collectors.toList());

        for(Seats seat : aisleSeats) {
            seat.seatNumber = seatCount;
            seatCount++;
        }
    }

    public static void addWindowSeatCount() {
        windowSeats = windowSeats.stream().sorted(Comparator.comparingInt(Seats::getRow)).collect(Collectors.toList());

        for(Seats seat : windowSeats) {
            seat.seatNumber = seatCount;
            seatCount++;
        }
    }

    public static void addCentreSeatCount() {
        centreSeats = centreSeats.stream().sorted(Comparator.comparingInt(Seats::getRow)).collect(Collectors.toList());

        for(Seats seat : centreSeats) {
            seat.seatNumber = seatCount;
            seatCount++;
        }
    }

    public static void showSeatCount(int seats[][]) {

        for (Seats seat : allSeats) {
            System.out.print(seat.type + seat.seatNumber + "  ");

            if(seats[seat.getSeatGroup()][0] == seat.getCol()+1) {
                System.out.println();
            }
        }
    }
}
