# Exercise 3 Assignment 2

def player_tuple(string): # takes a string separates it and places it inside a tuple
    string = string.split(" ")
    string_tuple = tuple(string)
    return string_tuple


def player_info(atuple): # takes a tuple and assigns names to each item in the tuple then returns formatted items
    (team, position, first, last, salary) = atuple
    return("{0:<15} {1:<15} {2:<15} {3:<15} {4:<15}".format(last + ",", first, salary, position, team))

def database_title(): # formatted title used for the database of items (player infomation)
    print("=====")
    print("Football Players Database:")
    print("=====")
    print("{0:<15} {1:<15} {2:<15} {3:<15} {4:<15}".format("Last-name", "First-name", "Salary", "Position", "Team"))
    print("{0:<15} {1:<15} {2:<15} {3:<15} {4:<15}".format("---------", "----------", "---------", "---------", "---------"))

def ascending_salary(thetuple): # A function that retrieves the player salaries in a list so it can be called on later 
    salary_list = [] # ensuring that the player in the database is in ascending order of their salaries
    for player in thetuple:
        (team, position, first, last, salary) = player
        salary = salary.replace(",", "")
        salary = int(salary)
        salary_list.append(salary)
    salary_list = sorted(set(salary_list))
    return salary_list

while True:
    football_players = []
    file = input("Please enter the name of the file you wish to load:")
    if file == "":
        print("Exiting to menu!")
        break
    try: # try and except to try to structure the infomation retrieved by the file in a specific way
        directfile = open(file) # if any errors occur the except will catch them and produce a message to 
        for line in directfile: # user so they can try again
            line = line.rstrip()
            newline = player_tuple(line)
            football_players.append(newline)
        directfile.close()
        print("-----")
        print("Data has been loaded!")
        break
    except FileNotFoundError:
        print("The file chosen does not exist")
    except PermissionError:
        print("You need permission to access this file")
    except:
        print("An error has occurred!")

database_title()
for data in football_players:
    print(player_info(data))
    print("-----")

while True:
    print("""
===========================
{} {} {} {} {} {} {} {} {}
---------------------------
 ***  EXERCISE THREE   ***
---------------------------
{} {} {} {} {} {} {} {} {}
===========================
1. Full details (last-name)
2. Full details (salary)
3. Full name (team)
4. Full details (position
   and team)
5. Full details, ascending
   order using salary
   (range and position)
6. Add a player
7. View database
X. Exit
===========================
---------------------------
 Please choose an option
( 1, 2, 3, 4, 5, 6, 7 or X)
 words in brackets are used
   as a search critera !
---------------------------
===========================
""")
    print("=====")
    option = input(" Which part of the exercise would you like to view ? :").upper()
    print("=====")
    if option == "1":
        lastname = input("Please enter the LAST NAME of the football player you wish to view:").title()
        counter = 0
        database_title()
        for player in football_players: # when the last name is entered if it matches any data then the data will be formatted and shown to the user
            (team, position, first, last, salary) = player
            if last == lastname:
                counter = + 1
                print(player_info(player))
                print("-----")
        if counter == 0:
            print("=====")
            print("... Sorry no results were found!")
        print("=====")
        print("END OF EXERCISE 3 PART 1 OF 6!")
    elif option == "2": # input is used to ensure if a comma was placed into the input, no errors were given! the input is transformed into a int afterwards
        minsalary = input("Please enter the MINIMUM SALARY for the search critera's range:")
        print("-----")
        maxsalary = input("Please enter the MAXIMUM SALARY for the search critera's range:")
        minsalary, maxsalary  = minsalary.replace(",", ""), maxsalary.replace(",", "")
        minsalary, maxsalary  = int(minsalary), int(maxsalary)
        counter = 0
        database_title()
        for player in football_players: # if the salary is within the range the right data will be displayed to the user
            (team, position, first, last, salary) = player
            salary = salary.replace(",", "")
            salary = int(salary)
            if salary >= minsalary and salary <= maxsalary:
                counter = + 1
                print(player_info(player))
                print("-----")
        if counter == 0:
            print("=====")
            print("... Sorry no results were found!")
        print("=====")
        print("END OF EXERCISE 3 PART 2 OF 6!")
    elif option == "3":
        footballteam = input("Please enter the TEAM you wish to view:").title()
        print("=====")
        print("Football Players Database:")
        print("=====")
        counter = 0
        print("{0:<15} {1:<15}".format("First-name", "Last-name"))
        print("{0:<15} {1:<15}".format("---------", "----------"))
        for player in football_players:
            (team, position, first, last, salary) = player
            if team == footballteam: # if the team match only the first and last name is produced
                counter = + 1
                print("{0:<15} {1:<15}".format(first + ",", last))
                print("-----")
        if counter == 0:
            print("=====")
            print("... Sorry no results were found!")
        print("=====")
        print("END OF EXERCISE 3 PART 3 OF 6!")
    elif option == "4":
        playerposition = input("Please enter the POSITION of the players you wish to view:").title()
        print("-----")
        footballteam = input("Please enter the TEAM you wish to view:").title()
        counter = 0
        database_title()
        for player in football_players:
            (team, position, first, last, salary) = player
            if position == playerposition and team == footballteam:
                counter = + 1
                print(player_info(player)) # if the position and team are both found in player info them their data will be shown
                print("-----")
        if counter == 0:
            print("=====")
            print("... Sorry no results were found!")
        print("=====")
        print("END OF EXERCISE 3 PART 4 OF 6!") 
    elif option == "5":
        playerposition = input("Please enter the POSITION of the players you wish to view:").title()
        print("-----")
        minsalary = input("Please enter the MINIMUM SALARY for the search critera's range:")
        print("-----")
        maxsalary = input("Please enter the MAXIMUM SALARY for the search critera's range:")
        minsalary, maxsalary  = minsalary.replace(",", ""), maxsalary.replace(",", "")
        minsalary, maxsalary  = int(minsalary), int(maxsalary)
        counter = 0
        database_title()
        for i in ascending_salary(football_players): # ascending function is used looping through the salaries lowest to highest 
            for player in football_players: # each player is compared against the salaries 
                (team, position, first, last, salary) = player
                salary = salary.replace(",", "")
                salary = int(salary) # if all requirements are met then the infomation is shown to the user
                if position == playerposition and salary >= minsalary and salary <= maxsalary and salary == i:
                    counter = + 1
                    print(player_info(player))
                    print("-----")
        if counter == 0:
            print("=====")
            print("... Sorry no results were found!")
        print("=====")
        print("END OF EXERCISE 3 PART 5 OF 6!")
    elif option == "6": # informaion is requested from the user separately, placing capitals in the required data
        newfirstname = input("Please enter the players FIRST-NAME:").title()
        print("-----")
        newlastname = input("Please enter the players LAST-NAME:").title()
        print("-----")
        newteam = input("Please enter the players TEAM:").title()
        print("----")
        newposition = input("Please enter the players POSITION:").title()
        print("-----")
        newsalary = input("Please enter the players SALARY (including COMMAS!):")
        if "," not in newsalary: # This part of the code ensures that no data is stored differently for others in the database
            print("=====")
            print("The salary was not correctly given with commas!")
            print("No new infomation will be added to the database!")
            print("=====")
            continue
        info = newteam , newposition , newfirstname , newlastname , newsalary
        football_players.append(info) # data are then combined into a tuple, structured as the file data has been structured
        print("=====")
        print("END OF EXERCISE 3 PART 6 OF 6!") # a message to let the user know that the exercise part has come to a close
    elif option == "7":
        database_title() # a option to present the user with the whole database (this includeds infomation added to the database)
        for player in football_players:
            print(player_info(player))
            print("-----")
    elif option == "X": # A option to close the program from the menu
        print("Thank you for viewing my Exercise 3 !")
        print("... PROGRAM CLOSING")
        break
    else: # A message if the user has input a invalid option
        print("-----")
        print("Please enter a valid option (1, 2, 3, 4, 5, 6, 7 or X) !")
        print("-----")
