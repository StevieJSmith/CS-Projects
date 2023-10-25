import { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import BackButton from "../components/BackButton";
import Loader from "../components/Loader";


const ShowBook = () => {

    const [book, setBook] = useState({});
    const [loading, setLoading] = useState(false);
    const {id} = useParams();

    useEffect(() => {
        setLoading(true);
        axios.get(`http://localhost:9000/books/${id}`)
            .then((res) => {
                setBook(res.data);
                setLoading(false);
            }).catch((err) => {
                console.log(err);
                setLoading(false);
            });
    }, []);


    return (
        <div className="">
            <BackButton />
            <h1 className="">Show Book</h1>
            {loading? ( <Loader /> ) :
            ( <table className=''>
            <tbody>
                <tr className=''>
                    <tr>
                    <td className=''>
                        <span className='type'>ID: </span>
                        <span className="value">{book._id}</span>
                    </td>
                    </tr>
                    <tr>
                    <td className=''>
                        <span className='type'>Title: </span>
                        <span className="value">{book.title}</span>
                    </td>
                    </tr>
                    <tr>
                    <td className=''>
                        <span className='type'>Author: </span>
                        <span className="value">{book.author}</span>
                    </td>
                    </tr>
                    <tr>
                    <td className=''>
                        <span className='type'>Publish Year: </span>
                        <span className="value">{book.publishYear}</span>
                    </td>
                    </tr>
                    <tr>
                    <td className=''>
                        <span className='type'>Create Time: </span>
                        <span className="value">{new Date(book.createdAt).toString()}</span>
                    </td>
                    </tr>
                    <tr>
                    <td className=''>
                        <span className='type'>Last Updated Time: </span>
                        <span className="value">{new Date(book.updatedAt).toString()}</span>
                    </td>
                    </tr>
                </tr>
            </tbody>
        </table>    
            )}
        </div>
    );
}


export default ShowBook;