import {Routes, Route} from 'react-router-dom';
import "./index.css";
import Home from './pages/Home';
import CreateBook from './pages/CreateBook';
import DeleteBook from './pages/DeleteBook';
import EditBook from './pages/EditBook';
import ShowBook from './pages/ShowBook';


const App = () => {
  return (
    <Routes>
      <Route index path='/' element ={<Home />} />
      <Route path='/books/create' element ={<CreateBook />} />
      <Route path='/books/details/:id' element ={<ShowBook />} />
      <Route path='/books/edit/:id' element ={<EditBook />} />
      <Route path='/books/delete/:id' element ={<DeleteBook />} />
    </Routes>
    
  );
};

export default App;