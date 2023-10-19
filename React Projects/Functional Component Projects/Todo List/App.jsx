import { useEffect, useState } from 'react';
import "./styles.css";


function App() {

  const [toDo, setToDo] = useState(() => {
    const localValue = localStorage.getItem("ITEMS"); // get items inside localStorage
   
    if(localValue == null) return [] // if nulll return an empty array
    else return JSON.parse(localValue); // else return the items
  });
  const [newItem, setNewItem] = useState(""); // items inside todo list 

  useEffect(() => { // store todos inside local storage when todo state changes
    localStorage.setItem("ITEMS", JSON.stringify(toDo))
  }, [toDo]); 


  const handleChange = (e) => { // updateitem on change in user input field 
    setNewItem(e.target.value);
  };

const handleSubmit = (e) => { // when form is submitted
    e.preventDefault();

    if(newItem === "") return // if not empty
    
    setToDo((currentToDo) => { // set the new item at the end of the todo array
      return [...currentToDo, {id: crypto.randomUUID(), title: newItem, completed: false},]
    })
    
    setNewItem("") // clear input field 
  };




  const toggleToDo = (id, completed) => { // when checkbox is interacted with
    setToDo(currentToDo => {
      return currentToDo.map((todo) => {
        if(todo.id === id) { // if the id of the checked box item is equal to on in the list
          return {...todo, completed} // show checkbox as completed (ticked)
        }
        return todo; // if no match return same array
      })
    })
  };

  const deleteToDo = (id) => {
    setToDo(currentToDo => {
      return currentToDo.filter((todo) => todo.id !== id)
    }) // if the id is a match filter it out of the array
  }; 

  return (
    <>
      <form onSubmit={handleSubmit} className='new-item-form'>
      <div className='form-row'>
        <label htmlFor='item'>New Item</label>
        <input value={newItem} onChange={handleChange} type='text' id='item'></input>
      </div>
      <button className='btn'>Add</button>
      </form>

      <h1 className='header'>ToDo List</h1>

      <ul className='list'>
        {toDo.length === 0 && "Please add something to do!!!"}
        {toDo.map((todo) => { // map the todo array displaying all 
          return ( // current items inside the list
            <li key={todo.id}>
              <div className='added'>
                <label>
                  <input checked={todo.completed} onChange={e => toggleToDo(todo.id, e.target.checked)} type='checkbox'></input>
                  {todo.title}
                </label>
                <button onClick={() => deleteToDo(todo.id)} className='btn btn-danger'>Delete</button>
                </div>
            </li>
        )
        })}
      
      </ul>
    
      </>
  );
  
}

export default App