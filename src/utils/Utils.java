package utils;

import entities.User;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean stayMenu(Scanner scanner) {
        System.out.println("Stay on this menu? (Y/N)");
        String chooseContinue = scanner.nextLine();
        return chooseContinue.equalsIgnoreCase("Y");
    }
    public static boolean isValidUsername(String username) {
        // Định dạng regex cho username
        String usernameRegex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches() && !username.isEmpty();
    }
    /*public static boolean isValidInteger(int inputInt) {
        if (in)
    }*/
    public static boolean isValidTaxCode(int taxCode) {
        String taxCodeStr = String.valueOf(taxCode);
        return taxCodeStr.length() == 10;
    }
    public static boolean isValidPassword(String password) {
        // Định dạng regex cho mật khẩu
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.,-_;])(?!.*\\s).{7,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email) {
        // Định dạng regex cho địa chỉ email
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidTemplateSeries(String templateSeries) {
        // Định dạng regex cho địa chỉ email
        String templateSeriesRegex = "^[a-zA-Z]{2}$";
        Pattern pattern = Pattern.compile(templateSeriesRegex);
        Matcher matcher = pattern.matcher(templateSeries);
        return matcher.matches();
    }
    public static String checkExistsEmail(String email, Map<String, User> staffs) {
        for (Map.Entry<String, User> entry : staffs.entrySet()) {
            if (entry.getValue().getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already exists, try again!");
                return null;
            }
        }
        return email;
    }
    public static boolean checkValidPositiveInt(int number) {
        if (number <= 0) {
            System.out.println("Number entered can't be less than or equal to 0, please re-enter!");
            return false;
        }
        else return true;
    }
    public static boolean checkValidPositiveNumber(double number) {
        if (number <= 0) {
            System.out.println("Number entered is outside the valid range, please re-enter!");
            return false;
        }
        else return true;
    }
    public static boolean checkUserIsAdmin(User user) {
        if (!user.isAdmin()) {
            System.out.println("This user don't have permission to perform this function!");
            return false;
        }
        else return true;
    }
}
