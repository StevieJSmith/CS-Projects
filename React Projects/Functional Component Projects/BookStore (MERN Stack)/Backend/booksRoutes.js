import express from 'express';
import {Book} from './bookModel.js';

const router = express.Router();

router.post('/', async (req, res) => { // create a book for the database
    try {
        if(!req.body.title || !req.body.author || !req.body.publishYear) {
            return res.send({message: 'Please include all fields of the book when submitting (title, author, publishYear)'});
        }

        const newBook = {
            title: req.body.title,
            author: req.body.author,
            publishYear: req.body.publishYear
        };

        const book = await Book.create(newBook);
        return res.send(book);

    }catch(err) {
        console.log(err);
        res.send({message: err.message});
    }
});


router.get("/", async (req, res) => { // get all books within the database!
    try {
        const books = await Book.find({});
        return res.json({
            count: books.length,
            data: books
        });
    }catch(err) {
        console.log(err);
        res.send({message: err.message});
    }
});


router.get("/:id", async (req, res) => { // get a specific book within the database!
    try {
        const {id} = req.params;
        const chosenBook = await Book.findById(id);

        return res.json(chosenBook);

    }catch(err) {
        console.log(err);
        res.send({message: err.message});
    }
});


router.put("/:id", async (req, res) => { // override a book within the database (update)
    try {
        if(!req.body.title || !req.body.author || !req.body.publishYear) {
            return res.send({message: 'Please include all fields of the book when submitting (title, author, publishYear)'});
        }

        const {id} = req.params;

        const chosenBook = await Book.findByIdAndUpdate(id, req.body);

        if(!chosenBook){
            return res.json({message: 'Book was not found in the Database!'});
        }
        return res.send({message: 'Book was Successfully Updated!'});

    }catch(err) {
        console.log(err);
        res.send({message: err.message});
    }
});

router.delete('/:id', async (req, res) => { // delete a book by id
    try {
        const {id} = req.params;

        const chosenBook = await Book.findByIdAndDelete(id);

        if(!chosenBook) {
            return res.json({message: "Book was not found in the Database!"})
        }

        return res.send({message: 'Book was successfully deleted from the Database!'})


    }catch(err) {
        console.log(err);
        res.send({message: err.message});
    }
});

export default router;