import { useEffect, useState } from "react";
import { useProjectDispatch } from "../hooks/customHook";
import { Actions } from "../../context/ProjectContext";
import { useUser } from "../../context/UserProvider";
import useFetch from "../hooks/useFetch";

export default function AddTasks({ projectId }) {
    const dispatch = useProjectDispatch();
    const [title, setTitle] = useState('');
    const [showSpinner, setShowSpinner] = useState(false);
    const [addTaskUrl, configureRequestType, configurePayload, response] = useFetch("", "", {}, null);
    const user = useUser();

    const handleInputChange = (event) => {
        console.log(event.target.value);
        setTitle(event.target.value);
    }

    useEffect(() => {
        setShowSpinner(false);
        setTitle('');
        if (response !== null) {
            dispatch({
                type: Actions.ADD_TASK,
                newTask: response
            });
        }
    }, [response]);

    const handleAddTask = (e) => {
        setShowSpinner(true);
        // Send the Task object to backend to persist the new task in database
        addTaskUrl("http://localhost:8080/projects/add-new-task");
        configurePayload(JSON.stringify({
            title: title,
            projectId: projectId,
            createdBy: user.username,
            createAt: new Date().toISOString(),
        }));
        configureRequestType("POST");
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