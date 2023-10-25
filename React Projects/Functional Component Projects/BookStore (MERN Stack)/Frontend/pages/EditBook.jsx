import { useEffect, useState } from "react";
import BackButton from '../components/BackButton';
import Loader from "../components/Loader";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useSnackbar } from "notistack";

const EditBook = () => {

    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [publishYear, setPublishYear] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const {id} = useParams();
    const {enqueueSnackbar} = useSnackbar();
    
    useEffect(() => {
        setLoading(true);
        axios.get(`http://localhost:9000/books/${id}`)
            .then((res) => {
                setTitle(res.data.title);
                setAuthor(res.data.author);
                setPublishYear(res.data.publishYear);
                setLoading(false);
            }).catch((err) => {
                console.log(err);
                setLoading(false);
                alert('an Error has occured (check console for more info!)')
            });
    }, []);

    const editBook = () => {
        const data = {
            title,
            author,
            publishYear
        };

        if(!title == "" || !author == "" || !publishYear == ""){
            return enqueueSnackbar('An error has occured!!!', {variant: 'error'});
        }

        setLoading(true);
        axios.put(`http://localhost:9000/books/${id}`, data)
            .then(() => {
                setLoading(false);
                navigate('/');
                enqueueSnackbar('Book was successfully edited!!!', {variant: 'success'});
            }).catch((err) => {
                setLoading(false);
                enqueueSnackbar('An error has occured!!!', {variant: 'error'});
                console.log(err);
                
            });
    };

    return (
        <div className="">
            <BackButton />
            <h1 className="">Edit Book</h1>
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
                            <button className="" onClick={editBook}>Save</button>
                        </td>
                    </tr>
                </tr>
                </tbody>
            </table>
        </div>
    );
}


export default EditBook;