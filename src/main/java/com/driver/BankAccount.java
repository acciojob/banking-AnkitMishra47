package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
            this.name = name;
            this.balance = balance;
            this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        String str = findNDigitNums(digits , sum);

        if (str == null){
            throw new Exception("Account Number can not be generated");
        }

        return null;
    }

    static String findNDigitNumsUtil(int n, int sum, char out[],int index)
    {
        // Base case
        if (index > n || sum < 0)
            return null;

        // If number becomes N-digit
        if (index == n)
        {
            // if sum of its digits is equal to given sum,
            // print it
            if(sum == 0)
            {
                out[index] = '\0'   ;
                return String.valueOf(out);
            }
        }

        // Traverse through every digit. Note that
        // here we're considering leading 0's as digits
        for (int i = 0; i <= 9; i++)
        {
            // append current digit to number
            out[index] = (char)(i + '0');

            // recurse for next digit with reduced sum
            findNDigitNumsUtil(n, sum - i, out, index + 1);
        }
        return null;
    }

    // This is mainly a wrapper over findNDigitNumsUtil.
    // It explicitly handles leading digit
    static String findNDigitNums(int n, int sum)
    {
        // output array to store N-digit numbers
        char[] out = new char[n + 1];


        for (int i = 1; i <= 9; i++)
        {
            out[0] = (char)(i + '0');
            String str = findNDigitNumsUtil(n, sum - i, out, 1);
            if (str != null){
                return str;
            }
        }
        return null;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if (this.minBalance < this.balance - amount){
            throw new Exception("Insufficient Balance");
        }
        this.balance -= amount;
    }
}