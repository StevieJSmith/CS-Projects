import express from "express";
import { mongoDBURL } from "./config.js";
import mongoose from "mongoose";
import booksRoute from './booksRoutes.js';
import cors from 'cors';

const app = express();

app.use(express.json());
app.use(cors());
// app.use(
//     cors({
//         origin: 'http://localhost9000',
//         methods: ['GET', 'POST', 'PUT', 'DELETE'],
//         allowedHeaders: ['Content-Type']
//     })
// );

const PORT = "9000";

app.get('/', (req, res) => { // homepage
    res.send("Welcome to my MERN Stack Project")
});


app.use('/books', booksRoute); // handle the requests from the books path!



mongoose.connect(mongoDBURL)
    .then(() => {
        console.log('App has been connected to MongoDB :D');
        app.listen(PORT, () => {
            console.log(`Server is running on Port: ${PORT}!`);
        });
    
    }).catch((err) => {
        console.log(err);
    });

