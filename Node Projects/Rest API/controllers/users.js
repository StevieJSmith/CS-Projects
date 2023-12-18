import { v4 as uuidv4 } from 'uuid';

let users = []; // user may get deleted so this array can not be const!

export const createUser = (req, res) => {

    // contains firstname, lastname and age 
    const user = req.body;

    const newUser = {...user, id: uuidv4() }; // takes the user properties and adds a unique id

    users.push(newUser);

    res.send(`User added to database with Name: ${user.firstName} ${user.lastName}!`);
};

export const getUsers = (req, res) => { // route = /users
    res.send(users);
};

export const getUserById = (req, res) => {
    const chosenId = req.params.id;

    const chosenUser = users.find((user) => chosenId == user.id); // find user with provided id

    res.send(chosenUser);
};

export const deleteUser = (req, res) => {
    const chosenId = req.params.id;

    users = users.filter((user) => user.id != chosenId); // if the chosen user id matches itll will be filtered out of our users array!

    res.send(`User with ID: ${chosenId} was successfully deleted from the database!`);
};

export const updateUser = (req, res) => {
    const chosenId = req.params.id; 
    const {firstName, lastName, age} = req.body; // inside body assign variable names to found properties

    const chosenUser = users.find((user) => chosenId == user.id); // find a match within the array using provided id

    // if firstName is provided update, etc ...
    if(firstName) {chosenUser.firstName = firstName;}
    if(lastName) {chosenUser.lastName = lastName;}
    if(age) {chosenUser.age = age;}

    res.send(`User with ID: ${chosenId} has been successfully updated!`)

};
