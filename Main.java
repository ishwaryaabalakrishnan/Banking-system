import java.util.Scanner;
abstract class Account{
    long Accnum;
    String name;
    String branch_name;
    long phnum;
    String IFSC;
    double bal;
    Account(long Accnum,String name,String branch_name,long phnum,String IFSC,double bal){
        this.Accnum=Accnum;
        this.name=name;
        this.branch_name=branch_name;
        this.phnum=phnum;
        this.IFSC=IFSC;
        this.bal=bal;
    }abstract void deposit(double amt);
    abstract void withdraw(double amt);
    abstract void transfer(String IFSC,String branch_name);
}class Bank extends Account{
    Bank(long Accnum,String name,String branch_name,long phnum,String IFSC,double bal){
        super(Accnum,name,branch_name,phnum,IFSC,bal);
    }@Override
         public void deposit(double amt){
        bal+=amt;
        System.out.println("Deposit:"+bal);
    }@Override
         public void withdraw(double amt){
        if(amt<=bal){
            bal-=amt;
            System.out.println("withdraw:"+amt);
            System.out.println("Balance:"+bal);
        }else{
            System.out.println("Insufficient bal");
        }
    }
    @Override
    public void transfer(String IFSC,String branch_name){
        this.IFSC=IFSC;
        this.branch_name=branch_name;
        System.out.println("Trasnferred Successfully");
    }
public void showDetails(){
    System.out.println("Account num:"+Accnum);
    System.out.println("Name:"+name);
    System.out.println("Branch:"+branch_name);
    System.out.println("phone:"+phnum);
    System.out.println("IFSC:"+IFSC);
    System.out.println("Balance:"+bal);
}
}
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
       Bank b1=new Bank(33442,"Ishu","Chennai",56787,"ISDRT",5000.67);
        
       //b1.deposit(1000);
        //b1.withdraw(3000);
        int ch=sc.nextInt();
        switch(ch){
                case 1:
                double n=sc.nextDouble();
                b1.deposit(n);
                break;
            case 2:
                double a=sc.nextDouble();
                b1.withdraw(a);
                break;
            case 3:
                String IFSC=sc.next();
                String branch_name=sc.next();
                b1.transfer(IFSC,branch_name);
                break;
            case 4:
                b1.showDetails();
                break;
            case 5:
                System.out.println("Exit");
                break;
            default:
                System.out.println("invalid");
                break;
                
        }
        
        
    }
}
