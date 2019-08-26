import java.io.IOException;

public class Date {
    private int day, month, year;
    private Boolean AM = null;

    public Date(String deadline) {
        String[] datetime = deadline.split(" ", 2);
        String[] date = datetime[0].split("/", 3);

        day = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);

        int time = Integer.parseInt(datetime[1]);
        if(time > 1200){
            AM = false;
            time = (time -1200) / 100;
        } else {
            AM = false;
            time = time / 100;
        }

    }

    public static Boolean isDate(String deadline) {
        try{
            String[] datetime = deadline.split(" ", 2);
            String[] date = datetime[0].split("/", 3);
            int time = Integer.parseInt(datetime[1]);
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
        } catch (IllegalArgumentException a){
            return false;
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public String returnDate(){
        String date = null;

        switch(day){
            case 1:
                date = "1st";
                break;
            case 2:
                date = "2nd";
                break;
            case 3:
                date = "3rd";
                break;
            case 21:
                date = "21st";
                break;
            case 22:
                date = "22nd";
                break;
            case 23:
                date = "23rd";
                break;
            case 31:
                date = "31st";
                break;
            default:
                date = day + "th";
                break;
        }

        date = date + " " + Month.returnMonth(month) + " " + year;

        return date;
    }

}
