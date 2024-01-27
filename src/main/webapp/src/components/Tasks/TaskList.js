import { Fragment, useState } from "react";
import { Actions } from "../../context/ProjectContext";

// Get the List of Tasks and convert them to JSX
export default function TaskList({ tasks, dispatch }) {

    return(
        <ul className="p-4 mt-8 rounded-md bg-stone-200">
            {
                tasks.map(task => (
                    <li key={task.id}>
                        <Task task={task} dispatch={dispatch}/>
                    </li>
                ))
            }
        </ul>
    );
}

export function Task({task, dispatch}) {
    // Here we can update the task title if required and can mark the task as done after clicking on the checkbox
    const [updatedTitle, setUpdatedTitle] = useState(task.title);
    const [isEditing, setIsEditing] = useState(false);

    const handleCheckBox = (event) => {
        dispatch({
            type: Actions.UPDATE_TASK,
            taskId: task.id,
            done: event.target.checked,
            updatedAt: new Date().toISOString(),
        });
    }

    // We already know which task to update and which task to delete based on the task prop
    const handleTitleUpdate = () => {
        dispatch({
            type: Actions.UPDATE_TASK,
            taskId: task.id,
            title: updatedTitle,
            updatedAt: new Date().toISOString(),
        });
        
        // Once the action is dispatched to update the Task List set isEditing to false again
        setIsEditing(false);
    }

    const handleTaskDelete = () => {
        dispatch({
            type: Actions.DELETE_TASK,
            taskId: task.id,
        });
    }

    return(
        <div className="flex bg-stone-100 p-2 m-2 rounded-lg" >
            <div className="flex items-center h-7">
                <input id="helper-checkbox" aria-describedby="helper-checkbox-text" type="checkbox" 
                value="" 
                onChange={handleCheckBox}
                className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
            </div>
            <div className="ms-2 text-md">
                {
                !isEditing ? 
                <div className="flex justify-between">
                    <div >
                        <label for="helper-checkbox" className="font-medium text-stone-900 dark:text-stone-800">
                            <span className={task.done ? "line-through":""}>{task.title}</span>
                        </label>
                        <p id="helper-checkbox-text " className="text-xs font-normal text-gray-500 dark:text-gray-600">{task.updatedAt}</p>
                    </div>
                    {/* <span className="timeStamp">{task.updatedAt}</span> */}
                    <button disabled={task.done} className="inline text-stone-800 hover:text-stone-900 px-2 py-2 ml-2 bg-gray-200 rounded-lg disabled:text-stone-400" onClick={() => setIsEditing(true)}>Edit</button>      {/* This button will update the local isEditing state of Task component */}
                    <button className="inline text-red-400 hover:text-red-900 px-2 py-2 bg-gray-200 rounded-lg ml-2" onClick={handleTaskDelete}>Delete</button>  {/* This button will dispatch the DELETE action to the Reducer */}
                </div>
                : 
                // Show the input text box to update the title
                <div className="flex justify-between">
                    <input className="px-2 py-1 rounded-md bg-stone-200"
                    type="text" value={updatedTitle} onChange={
                        (e) => {
                            {/* Just update the local state to track the updated title */}
                            setUpdatedTitle(e.target.value);
                        }} 
                    />
                    <button className="inline text-stone-600 hover:text-stone-900 px-1 py-1 ml-1 bg-gray-200 rounded-lg disabled:text-stone-400" 
                        disabled={updatedTitle.length === 0}
                        onClick={handleTitleUpdate}>
                        Save
                    </button>
                </div> 
            }
            </div>
        </div>
    );
}