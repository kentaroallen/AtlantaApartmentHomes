package AAH;

/**
 * Created by AmierNaji on 11/29/14.
 */
public class ApartmentAllotmentChoice {

    public static String prospective_applicant;
    public static int rent;
    public static String category;

    public static void setApplicant(String s, int r, String cat) {
        prospective_applicant = s;
        rent = r;
        category = cat;
    }

    public static String getApplicant() { return prospective_applicant;}
    public static int getRent() { return rent; }
    public static String getCategory() { return category; }
}
