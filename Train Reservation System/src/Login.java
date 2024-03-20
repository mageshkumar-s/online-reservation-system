import java.util.Scanner;

public class Login {

    String userName;
    String passWord;
    String mobileNo;

    Login(int userOpt){
        Scanner scan = new Scanner(System.in);
        switch (userOpt) {
            case 1:
                System.out.println("----------------------------------------  Create Your Account  ---------------------------------------");
                System.out.print("\n                                      Enter username : ");
                userName = scan.next();
                System.out.print("                                      Enter password : ");
                passWord = scan.next();
                System.out.print("                                      Enter Mobile no : ");
                mobileNo = scan.next();
                
                break;
            case 2:
                System.out.println("----------------------------------------  Login your Account   --------------------------------------");
                System.out.print("\n                                      Enter username : ");
                userName = scan.next();
                System.out.print("                                      Enter password : ");
                passWord = scan.next();
                
                break;
            default:
                break;
        }
    }
}
