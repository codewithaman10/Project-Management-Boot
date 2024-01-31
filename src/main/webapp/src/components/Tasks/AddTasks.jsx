import { useState } from "react";
import { useProjectDispatch } from "../hooks/customHook";
import { Actions } from "../../context/ProjectContext";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faArrowDown } from "@fortawesome/free-solid-svg-icons";

export default function AddTasks({ projectId }) {
    const dispatch = useProjectDispatch();
    const [title, setTitle] = useState('');
    const [showSpinner, setShowSpinner] = useState(false);

    const handleInputChange = (event) => {
        console.log(event.target.value);
        setTitle(event.target.value);
    }

    const handleAddTask = (e) => {
        console.log("Dispatching action: ", Actions.ADD_TASK);
        setShowSpinner(true);
        // Send the Task object to backend to persist the new task in database
        fetch("/projects/add-new-task", {
            method: "POST",
            body: JSON.stringify({
                title: title,
                lastUpdatedAt: new Date().toISOString(),
                lastUpdatedBy: "User1",
                projectId: projectId,
                createdBy: "User1",
                createAt: new Date().toISOString,
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            }
        }).then(response => response.json())
          .then(json => {
                setShowSpinner(false);
                setTitle('');

                // Here we are telling React "what the user just did" by dispatching the below action
                // Unlike instead of telling react "what to do" by setting state
                dispatch({
                    type: Actions.ADD_TASK,
                    newTask: json
                });

           })
           .catch(error => console.error(error));
    }

    return(
        <div className="flex items-center gap-4">
            <input value={title} className="w-11/12 p-2 rounded-md bg-stone-200" placeholder="# Add new Task"
            onChange={handleInputChange} onKeyDown={(e) => {
                if(e.key === 'Enter') handleAddTask(e);
            }}/>
            <button className="text-stone-600 hover:text-stone-950 w-2/12 disabled:text-solid-500 bg-stone-200 p-2 rounded-lg" onClick={handleAddTask} disabled={title === ''}>
                Add Task
            </button>
        </div>
    );
}