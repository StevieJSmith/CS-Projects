import express from 'express';
import {createUser, getUsers, getUserById, deleteUser, updateUser} from '../controllers/users.js';


const router = express.Router();

// /users is the default route inside this file !!!
router.get('/', getUsers);

router.post('/', createUser);

router.get('/:id', getUserById);

router.delete('/:id', deleteUser);

router.patch('/:id', updateUser);


export default router;