import { BsArrowLeft } from 'react-icons/bs';
import {Link} from 'react-router-dom';

 const BackButton = ({destination = '/'}) => {
    return (
        <div>
        <Link to={destination} className=''>
            <BsArrowLeft className='back' />
        </Link>
    </div>
    );
 };

 export default BackButton;