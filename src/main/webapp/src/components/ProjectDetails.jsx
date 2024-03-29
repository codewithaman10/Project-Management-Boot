import { Actions } from "../context/ProjectContext";
import Tasks from "./Tasks/Tasks";
import { useProject, useProjectDispatch } from "./hooks/customHook";
import { useUser } from "../context/UserProvider"

export default function ProjectDetails({ selectedProject }) {
    const project = selectedProject.project;
    const projectTasks = selectedProject.tasks;
    const dispatch = useProjectDispatch();
    const user = useUser();
    
    const formattedDate = new Date(project.dueDate).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });

    const handleOnDelete = () => {

        fetch(`http://localhost:8080/projects/delete-project/${project.id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${user.token}`
            }
        })
            .then(response => response.text())
            .then(text => {
                console.log(text);
                dispatch({
                    type: Actions.DELETE_PROJECT
                });
            })
            .catch(error => console.log(error));
    }


    return (
        <div className="w-[70rem] mt-16 overflow-auto">
            <header className="pb-4 mb-4 border-b-2 border-stone-300">
                <div className="flex items-center justify-between">
                    <h1 className="text-3xl font-bold text-stone-600 mb-2">{project.title}</h1>
                    <button className="text-stone-600 hover:text-stone-950 bg-gray-200 rounded-lg p-2 hover:bg-gray-400" onClick={handleOnDelete}>Delete</button>
                </div>
                <p className="mb-4 text-stone-400">{formattedDate}</p>
                <p className="text-stone-600 whitespace-pre-wrap">{project.description}</p>
            </header>
            <Tasks projectTasks={projectTasks} dispatch={dispatch} projectId={project.id}/>
        </div>
    );
}