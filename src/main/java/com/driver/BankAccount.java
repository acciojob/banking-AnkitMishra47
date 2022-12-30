package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    public String accountNumber;

    public BankAccount(String name, double balance, double minBalance) {
            this.name = name;
            this.balance = balance;
            this.minBalance = minBalance;
            this.accountNumber = "";
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        findNDigitNums(digits , sum);
        String str = this.accountNumber;

        if (str == ""){
            throw new Exception("Account Number can not be generated");
        }
        this.accountNumber = "";
        return str;
    }

    void findNDigitNumsUtil(int n, int sum, char out[],
                            int index)
    {
        // Base case
        if (index > n || sum < 0)
            return;

        if (index == n)
        {

            if(sum == 0)
            {
                String num = String.valueOf(out);
                this.accountNumber = num.substring(0 , num.length()-1);
            }
            return;
        }


        for (int i = 0; i <= 9; i++)
        {
            out[index] = (char)(i + '0');

            findNDigitNumsUtil(n, sum - i, out, index + 1);
        }
    }


    void findNDigitNums(int n, int sum)
    {

        char[] out = new char[n + 1];

        for (int i = 1; i <= 9; i++)
        {
            out[0] = (char)(i + '0');
            findNDigitNumsUtil(n, sum - i, out, 1);
        }
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