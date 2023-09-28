balance = 8549 # user balance
pin = 1234 # user pin

user_pin = int(input("Welcome to XYZ Bank, please enter your pin: ")) # pin request

if (user_pin == pin): # if pin matches another input will be prompted 
    print("Your Balance: £", balance)
    answer = input("Would you like to withdraw or deposit money?: ")
    if (answer.lower() == "withdraw"):
        amount = int(input("How much would you like to withdraw?: "))
        balance -= amount # subtract from balance
        print("Your Updated Balance: £", balance)
    elif (answer.lower() == "deposit"):
        amount = int(input("How much would you like to deposit?: "))
        balance += amount # add to balance
        print("Your Updated Balance: £", balance)
    else:
        print("Sorry you can only Withdraw or Deposit!")
        # displays if incorrect input is supplied

else:
    print("Your pin does not match!") # displays if pin does not match