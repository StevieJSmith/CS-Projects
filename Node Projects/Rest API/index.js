import express from 'express';
import bodyParser from 'body-parser';
import usersRoutes from './routes/users.js'



const app = express();
const PORT = 9000;

app.use(bodyParser.json());

app.use('/users', usersRoutes);

app.get('/', (req, res) => { // homepage
    res.send("Welcome to my REST API!!!");
});


app.listen(PORT, () => {
    console.log(`The server is running on port: ${PORT} !!!`)
});
