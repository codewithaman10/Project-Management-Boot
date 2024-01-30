import { Actions } from "../context/ProjectContext";
import Button from "./Button";
import Projects from "./Projects";
import { useProjectDispatch } from "./hooks/customHook";

export default function ProjectSidebar({ projectsData }) {
    const dispatch = useProjectDispatch();

    const handleOnStartAddProject = () => {
        dispatch({
            type: Actions.ADD_NEW_PROJECT
        });
    }

    return (
        <aside className="w-1/3 px-8 py-16 bg-stone-900 text-stone-50 md:w-72 rounded-r-xl">
            <h2 className="mb-8 font-bold uppercase md:text-xl text-stone-200">Your Projects</h2>
            <div>    
                <Button onClick={handleOnStartAddProject}>+ Add Project</Button>
            </div>
            <Projects />
        </aside>
    );
}