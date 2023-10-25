import { useState } from "react";
import BackButton from '../components/BackButton';
import Loader from "../components/Loader";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useSnackbar } from "notistack";

const DeleteBook = () => {

    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const {id} = useParams();
    const {enqueueSnackbar} = useSnackbar();

    const deleteBook = () => {
        setLoading(true);
        axios.delete(`http://localhost:9000/books/${id}`)
            .then(() => {
                setLoading(false);
                navigate('/');
                enqueueSnackbar('Book was successfully deleted!!!', {variant: 'success'});
            }).catch((err) => {
                setLoading(false);
                enqueueSnackbar('An error has occured!!!', {variant: 'error'});
                console.log(err);
                
            });
    };

    return (
        <div>
            <BackButton />
            <h1 className="">Delete Book</h1>
            {loading? <Loader /> : ""}
            <table className=''>
                    <thead>
                        <tr>
                            <th className=''><h3 className="">Are you 100% you want to delete this book permanently </h3></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr className=''>
                            <td className=''><button className="" onClick={deleteBook}>Delete Permanently</button></td>
                        </tr>
                    </tbody>
                </table>
        </div>
    );
}


export default DeleteBook;