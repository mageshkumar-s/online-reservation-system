import java.util.Scanner;

public class Login {

    String userName;
    String passWord;
    String mobileNo;

    Login(){
        System.out.println("----------Enter your option.----------\n 1. Create account\n 2. Login\n");
        Scanner scan = new Scanner(System.in);
        int userOpt = scan.nextInt();
        switch (userOpt) {
            case 1:
                System.out.println("-------Create Your Account-----");
                System.out.print("Enter username : ");
                userName = scan.next();
                System.out.print("Enter password : ");
                passWord = scan.next();
                System.out.print("Enter Mobile no : ");
                mobileNo = scan.next();
                break;
            case 2:
                System.out.println("-------Login your Account-----");
                System.out.print("Enter username : ");
                userName = scan.next();
                System.out.print("Enter password : ");
                passWord = scan.next();
                break;
            default:
                break;
        }
    }
}
