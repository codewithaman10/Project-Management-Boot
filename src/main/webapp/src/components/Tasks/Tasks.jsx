import TaskList from "./TaskList";
import AddTasks from "./AddTasks";

export default function Tasks({ projectTasks, dispatch, projectId }) {

    return(
        <section>
            <h2 className="text-2xl font-bold text-stone-700 mb-4">Tasks</h2>
            <AddTasks dispatch={dispatch} projectId={projectId}/>
            { projectTasks.length > 0 ? <TaskList tasks={projectTasks} dispatch={dispatch} /> :
               <p className="text-stone-800 my-4">No Task recorded for this project</p> }
        </section>
    );
}