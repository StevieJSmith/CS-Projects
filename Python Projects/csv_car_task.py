import csv
from collections import namedtuple

cars = []
car = namedtuple("car", "model year price transmission mileage fuelType tax mpg engineSize") # use namedtuple to structure the format of each car by giving each element a unique key

with open('vw.csv', 'r') as csvfile:
    reader = csv.reader(csvfile, skipinitialspace=True) # obtain file content as reader
    next(reader) # skip over the first row (only column names within)
    for row in reader:
        new_car = car(*row) # map the whole row of data to the keys provided by namedtuple
        cars.append(new_car) # add to the cars array

most_expensive = cars[0]
golf_total = 0
golf_amount = 0
polo_mileage_total = 0
polo_amount = 0

for car in cars: # car equals current element 
    if int(car.price) > int(most_expensive.price):
        most_expensive = car # compare all cars to find the most expensive 
    if car.model == "Golf": # carry out the following if the model of the car is a 'Golf'
        golf_total += int(car.price)
        golf_amount += 1
    if car.model == "Polo" and car.year == "2020": # carry out the following if the model is a 'Polo' and manufacturing year is '2020'
        polo_mileage_total += int(car.mileage)
        polo_amount += 1

print(f"The most expensive car: {most_expensive}")
print(f"Golf models average price: £{round((golf_total / golf_amount), 2)}")
print(f"Polo mileage average : {round((polo_mileage_total / polo_amount), 2)}")
