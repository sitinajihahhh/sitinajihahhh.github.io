import { useState } from "react";
import "./styles.css";

export default function App() {
  const [text, setText] = useState("");
  const [todos, setTodos] = useState([]);

  function add(e) {
    setTodos([...todos, text]);
  }

  function handleOnChange(e) {
    setText(e.target.value);
  }

  function remove(index) {
    let newArray = todos.filter((el, i) => i !== index);
    setTodos(newArray);
  }

  return (
    <div className="App">
      <h1>Hello CodeSandbox</h1>
      <div>Todo List</div>
      <ul>
        {todos.map((data, index) => (
          <li>
            {data} <button onClick={() => remove(index)}>-</button>
          </li>
        ))}
      </ul>
      <input onChange={handleOnChange} />
      <button onClick={add}>Add</button>
    </div>
  );
}
