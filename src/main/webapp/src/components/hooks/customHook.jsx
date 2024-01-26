import { useContext } from "react";
import { ProjectContext, ProjectDispatchContext } from "../../context/ProjectContext";

// Named exports
export function useProject() {
    return useContext(ProjectContext);
}

export function useProjectDispatch() {
    return useContext(ProjectDispatchContext);
}