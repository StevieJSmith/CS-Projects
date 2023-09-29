employees = [ # an array of dictionaries on employee information
 {
 "first_name":"Jose",
 "last_name":"Lopez",
 "email":"joselopez0944@example.com",
 "age":25,
 "job_title":"Project Manager",
 "years_of_experience":1,
 "salary":8500,
 "department":"Product"
 },
 {
 "first_name":"Diane",
 "last_name":"Carter",
 "email":"dianecarter1228@example.com",
 "age":26,
 "job_title":"Machine Learning Engineer",
 "years_of_experience":2,
 "salary":7000,
 "department":"Product"
 },
 {
 "first_name":"Shawn",
 "last_name":"Foster",
 "email": None,
 "age":37,
 "job_title":"Customer Service Rep",
 "years_of_experience":14,
 "salary":17000,
 "department":"Business"
 },
 {
 "first_name":"Brenda",
 "last_name":"Fisher",
 "email":"brendafisher3185@example.com",
 "age":31,
 "job_title":"Web Developer",
 "years_of_experience":8,
 "salary":10000,
 "department":"Product"
 },
 {
 "first_name":"Sean",
 "last_name":"Hunter",
 "email": None,
 "age":35,
 "job_title":"Project Manager",
 "years_of_experience":11,
 "salary":14500,
 "department":"Product"
 },
 {
 "first_name":"Joshua",
 "last_name":"Jacobs",
 "email":"joshuajacobs5904@example.com",
 "age":28,
 "job_title":"Project Manager",
 "years_of_experience":3,
 "salary":10500,
 "department":"Business"
 },
 {
 "first_name":"Brianna",
 "last_name":"Marshall",
 "email":None,
 "age":33,
 "job_title":"Machine Learning Engineer",
 "years_of_experience":10,
 "salary":11000,
 "department":"Product"
 },
 {
 "first_name":"John",
 "last_name":"Tate",
 "email":"johntate7881@example.com",
 "age":33,
 "job_title":"Mobile Developer",
 "years_of_experience":10,
 "salary":11000,
 "department":"Product"
 },
 {
 "first_name":"Jillian",
 "last_name":"Byrd",
 "email":None,
 "age":34,
 "job_title":"Business Analyst",
 "years_of_experience":10,
 "salary":11000,
 "department":"Business"
 },
 {
 "first_name":"Melanie",
 "last_name":"Sharp",
 "email":"melaniesharp9256@example.com",
 "age":41,
 "job_title":"Web Developer",
 "years_of_experience":15,
 "salary":14500,
 "department":"Product"
 },
 {
 "first_name":"Brandy",
 "last_name":"Mckee",
 "email":None,
 "age":37,
 "job_title":"Marketing Manager",
 "years_of_experience":14,
 "salary":14000,
 "department":"Business"
 },
 {
 "first_name":"Robert",
 "last_name":"Simpson",
 "email":"robertsimpson11778@example.com",
 "age":36,
 "job_title":"Marketing Manager",
 "years_of_experience":12,
 "salary":15000,
 "department":"Business"
 },
 {
 "first_name":"George",
 "last_name":"Mckenzie",
 "email":"georgemckenzie12384@example.com",
 "age":28,
 "job_title":"Machine Learning Engineer",
 "years_of_experience":4,
 "salary":10000,
 "department":"Product"
 },
 {
 "first_name":"Joseph",
 "last_name":"Smith",
 "email":None,
 "age":40,
 "job_title":"Machine Learning Engineer",
 "years_of_experience":14,
 "salary":14000,
 "department":"Product"
 },
 {
 "first_name":"Dana",
 "last_name":"Crawford",
 "email":"danacrawford14310@example.com",
 "age":32,
 "job_title":"Project Manager",
 "years_of_experience":8,
 "salary":12000,
 "department":"Product"
 },
 {
 "first_name":"Christopher",
 "last_name":"Benson",
 "email":None,
 "age":29,
 "job_title":"Web Developer",
 "years_of_experience":5,
 "salary":7500,
 "department":"Product"
 },
 {
 "first_name":"Nicole",
 "last_name":"Smith",
 "email":"nicolesmith16360@example.com",
 "age":26,
 "job_title":"Designer",
 "years_of_experience":4,
 "salary":10000,
 "department":"Product"
 },
 {
 "first_name":"Peter",
 "last_name":"Jimenez",
 "email":"peterjimenez17791@example.com",
 "age":28,
 "job_title":"UX Designer",
 "years_of_experience":3,
 "salary":6500,
 "department":"Business"
 },
 {
 "first_name":"Sergio",
 "last_name":"Boyle",
 "email":"sergioboyle18425@example.com",
 "age":31,
 "job_title":"Tester",
 "years_of_experience":6,
 "salary":9000,
 "department":"Product"
 },
 {
 "first_name":"Brianna",
 "last_name":"Moss",
 "email":None,
 "age":31,
 "job_title":"Designer",
 "years_of_experience":5,
 "salary":10500,
 "department":"Product"
 },
 {
 "first_name":"Taylor",
 "last_name":"Garner",
 "email":"taylorgarner20196@example.com",
 "age":32,
 "job_title":"Machine Learning Engineer",
 "years_of_experience":6,
 "salary":11000,
 "department":"Product"
 },
 {
 "first_name":"Michael",
 "last_name":"Padilla",
 "email":"michaelpadilla21381@example.com",
 "age":29,
 "job_title":"Customer Service Rep",
 "years_of_experience":5,
 "salary":9500,
 "department":"Business"
 },
 {
 "first_name":"Yvette",
 "last_name":"Walker",
 "email":None,
 "age":26,
 "job_title":"Designer",
 "years_of_experience":2,
 "salary":7000,
 "department":"Product"
 },
 {
 "first_name":"Kristina",
 "last_name":"Pena",
 "email":"kristinapena23750@example.com",
 "age":34,
 "job_title":"Business Analyst",
 "years_of_experience":11,
 "salary":12500,
 "department":"Business"
 }
]

highest_salary_employee = employees[0] # set to the first employee in the to compare against
business_salaries = 0 
combined_years = 0
average_length = 0
average_over_30_salary = 0
employees_without_email = []
employee_jobs = {}
for employee in employees: # employee assigned to each dictionary
    combined_years+= employee["years_of_experience"]
    if employee['salary'] > highest_salary_employee['salary']:
        highest_salary_employee = employee # the employee with the higher salary is assigned as highest_salary_employee
    if employee['email'] == None:
        employees_without_email.append(employee) # if employee info does not contain a email add to the employees_without_email array
    if employee['age'] > 30:
        average_over_30_salary+= employee['salary'] # total up the employees over 30s salaries
        average_length+= 1
    if employee['department'] == 'Product':
        product_salaries+= employee['salary'] # total up the companies product department salary
    else:
        business_salaries+= employee['salary'] # total up the companies business department salary
    if not employee['job_title'] in employee_jobs:
        employee_jobs[employee['job_title']] = 1 # add the job if it does not appear in employee_jobs as the key with a value of 1
    else:
        employee_jobs[employee['job_title']] += 1 # if the job is within employee_jobs, then increase the value by 1 

highest_salary_employee_name = highest_salary_employee['first_name'] +  " " +  highest_salary_employee["last_name"] # concat the first and last name of the highest employee
average_over_30_salary = int(average_over_30_salary / average_length) # find the average by dividing the total by the amount 
greater_cost = ""
if (product_salaries > business_salaries): # find the department with the greater cost to the company
    greater_cost = "Product"
else:
    greater_cost = "Business"

print(f"The employee with the Highest Salary is: {highest_salary_employee_name}")
print(f"The combined years of experience of all employees: {combined_years} years!")
print("Employess without email addresses: \n")
print(employees_without_email)
print(f"The deparment that costs the company more money in salaries: {greater_cost}")
print(f"Average employee salary for over 30's at the company: £{average_over_30_salary}")
print("Number of jobs at the company: ")
print(employee_jobs)
