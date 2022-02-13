package com.revature;


import java.io.IOException;

import java.util.Scanner; //import scanner class

class School {
     double n;
     int k;
     double c;
    //String fname;
   public School(int x, int y){
       this.n = x;
       this.k = y;
   }
   public void addNumber(){// add two numbers
       System.out.println("Sum: "+ (n + k));
   }
    public void multiNumber(){//multiply two numbers
        System.out.println("Product: "+ (n * k));
    }
    public void divNumber(){//division of two numbers
        System.out.println("Result: "+ (n / k));
        try{
            c = n/k;
        }catch (ArithmeticException ex){
            System.out.println("k cannot be 0");
        }
    }
    public void minusNumber(){//subtract two numbers
        System.out.println("Difference: "+ (n -k));

    }
    //System.out.println(");
}

    public class Numbers {
        public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            //int nums = 0;
            //int anums = 0;
            System.out.println("Please type your first name: ");
            String firstName = scan.nextLine(); //read input

            System.out.println("Please type your last name: ");
            String lastName = scan.nextLine(); //read input

            System.out.println(" Hi " + firstName +" "+ lastName);

           /* System.out.println("Please select a math option: \n");
            System.out.println("1-Add\n 2-Subtract\n 3-Multiply\n 4-Divide\n");*/

            Scanner sc = new Scanner(System.in);
            //int num = scan.nextInt();

           // System.out.println("You selected "+num);

            System.out.println("Please enter your first number to calculate ");
            int nums =scan.nextInt();
            System.out.println("Type your second number: ");
            int anums = scan.nextInt();
            School calc1 = new School(nums,anums );
            calc1.addNumber();
            calc1.minusNumber();
            calc1.divNumber();
            calc1.multiNumber();

        }
    }


