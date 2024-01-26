import { Actions } from "../context/ProjectContext";
import { useProject, useProjectDispatch } from "./hooks/customHook";

export default function Projects() {
    const projectsData = useProject();
    const dispatch = useProjectDispatch();
    const projects = projectsData.projects;
    const selectedProjectId = projectsData.selectedProjectId;

    const onSelect = (projectId) => {
        dispatch({
            type: Actions.SELECT_PROJECT,
            projectId: projectId
        });
    }

    return (
        <ul className="mt-8">
            {
                projects.map(project => {
                    let cssClass = "w-full text-left px-2 py-1 rounded-sm my-1 hover:text-stone-200 hover:bg-stone-800";
                    if (project.id === selectedProjectId) {
                        cssClass += ' bg-stone-800 text-stone-200';
                    } else {
                        cssClass += ' text-stone-400';
                    }

                    return (
                        <li key={project.id}>
                            <button onClick={() => onSelect(project.id)}
                                className={cssClass}>
                                {project.title}
                            </button>
                        </li>
                    )
                })
            } 
        </ul>
    );
}

