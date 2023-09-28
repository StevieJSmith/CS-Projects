import csv
from collections import namedtuple

cars = []
car = namedtuple("car", "model year price transmission mileage fuelType tax mpg engineSize")

with open('vw.csv', 'r') as csvfile:
    reader = csv.reader(csvfile, skipinitialspace=True)
    next(reader)
    for row in reader:
        new_car = car(*row)
        cars.append(new_car)

most_expensive = cars[0]
golf_total = 0
golf_amount = 0
polo_mileage_total = 0
polo_amount = 0

for car in cars:
    if int(car.price) > int(most_expensive.price):
        most_expensive = car
    if car.model == "Golf":
        golf_total += int(car.price)
        golf_amount += 1
    if car.model == "Polo" and car.year == "2020":
        polo_mileage_total += int(car.mileage)
        polo_amount += 1

print(f"The most expensive car: {most_expensive}")
print(f"Golf models average price: £{round((golf_total / golf_amount), 2)}")
print(f"Polo mileage average : {round((polo_mileage_total / polo_amount), 2)}")
