import { useState } from "react";
import BackButton from '../components/BackButton';
import Loader from "../components/Loader";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useSnackbar } from "notistack";

const CreateBook = () => {

    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [publishYear, setPublishYear] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const {enqueueSnackbar} = useSnackbar();

    const saveBook = () => {
        const data = {
            title,
            author,
            publishYear
        };

        if(!title == "" || !author == "" || !publishYear == ""){
            return enqueueSnackbar('An error has occured!!!', {variant: 'error'});
        }

        setLoading(true);
        axios.post('http://localhost:9000/books', data)
            .then(() => {
                setLoading(false);
                navigate('/');
                enqueueSnackbar('Book was successfully created!!!', {variant: 'success'});
            }).catch((err) => {
                setLoading(false);
                enqueueSnackbar('An error has occured!!!', {variant: 'error'});
                console.log(err);
                
            });
    };

    return (
        <div className="">
            <BackButton />
            <h1 className="">Create Book</h1>
            {loading? <Loader /> : ''}

            <table className=''>
                <tbody>
                    <tr className=''>
                        <tr>
                            <td className=''>
                                <label className="type">Title: </label>
                                <input type="" value={title} onChange={(e) => setTitle(e.target.value)} className="" />
                            </td>
                    </tr>
                    <tr>
                            <td className=''>
                                <label className="type">Author: </label>
                                <input type="" value={author} onChange={(e) => setAuthor(e.target.value)} className="" />
                            </td>
                    </tr>
                    <tr>
                        <td className=''>
                            <label className="type">Publish Year: </label>
                            <input type="" value={publishYear} onChange={(e) => setPublishYear(e.target.value)} className="" />
                        </td>
                    </tr>
                    <tr>
                        <td className='type'>
                            <button className="" onClick={saveBook}>Save</button>
                        </td>
                    </tr>
                </tr>
                </tbody>
            </table>


        </div>
    );
}


export default CreateBook;