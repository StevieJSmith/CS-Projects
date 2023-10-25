import {useEffect, useState} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import {AiOutlineEdit} from 'react-icons/ai';
import {BsInfoCircle} from 'react-icons/bs';
import {MdOutlineAddBox, MdOutlineDelete} from 'react-icons/md';
import Loader from '../components/Loader';



const Home = () => {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => { // run each time a change occurs
        setLoading(true);
        axios.get('http://localhost:9000/books') // get out url
            .then((res) => { // get data and set as books
                setBooks(res.data.data);
                setLoading(false); // finished task so set loading to false
            }).catch((err) => {
                console.log(err);
                setLoading(false);
            });

    }, []);

    return (
        <div className=''>
            <div className='top-container'>
                <h1 className=''>Books List</h1>
                <Link to='/books/create'>
                    <MdOutlineAddBox className='create' />
                </Link>
            </div>
            {loading? (
                <Loader /> ) :
                (<table className=''>
                    <thead>
                        <tr>
                            <th className=''>No</th>
                            <th className=''>Title</th>
                            <th className=''>Author</th>
                            <th className=''>Publish Year</th>
                            <th className=''>Operations</th>

                        </tr>
                    </thead>
                    <tbody>
                        {books.map((book, index) => (
                            <tr key={book._id} className=''>
                                <td className=''>{index+1}</td>
                                <td className=''>{book.title}</td>
                                <td className=''>{book.author}</td>
                                <td className=''>{book.publishYear}</td>
                                <td className=''>
                                    <div className=''>
                                        <Link to={`/books/details/${book._id}`}>
                                            <BsInfoCircle className='info' />
                                        </Link>
                                        <Link to={`/books/edit/${book._id}`}>
                                            <AiOutlineEdit className='edit' />
                                        </Link>
                                        <Link to={`/books/delete/${book._id}`}>
                                            <MdOutlineDelete className='delete' />
                                        </Link>
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default Home;