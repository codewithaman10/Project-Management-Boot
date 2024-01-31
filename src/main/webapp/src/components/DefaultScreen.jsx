import noProjectSelected from "../assets/no-projects.png";
import { Actions } from "../context/ProjectContext";
import Button from "./common/Button";
import { useProjectDispatch } from "./hooks/customHook";

export default function DefaultScreen() {
    const dispatch = useProjectDispatch();

    const handleClick = () => {
        dispatch({
            type: Actions.ADD_NEW_PROJECT
        });
    }

    return(
        <div className="mt-24 text-center w-2/3">
            <img alt="An Empty Task list" src={noProjectSelected} className="w-16 h-16 object-contain mx-auto"/>
            <h2 className="text-xl font-bold text-stone-500 my-4">No Project Selected</h2>
            <p className="text-stone-400 mb-4">Select a project or get started with a new one</p>
            <p className="mt-8">
                <Button onClick={handleClick}>Create new Project</Button>
            </p>
        </div>
    );
}