import { useEffect, useRef } from "react";
import Input from "./common/Input";
import Modal from "./common/Modal";
import { useProjectDispatch } from "./hooks/customHook";
import { Actions } from "../context/ProjectContext";

export default function NewProject() {
    const titleRef = useRef();
    const descriptionRef = useRef();
    const dueDateRef = useRef();
    const modal = useRef();
    const dispatch = useProjectDispatch();

    const handleSave = () => {
        const title = titleRef.current.value;   // .value since all the textarea and input html tag have value in which they stores the value
        const description = descriptionRef.current.value;
        const dueDate = dueDateRef.current.value;

        // Validation of these values
        if (title.trim() === '' || description.trim() === '' || dueDate.trim() === '') {
            // In case the above validations fails we should show the user an error modal or just a simple error message
            modal.current.open();
            return;
        }

        dispatch({
            type: Actions.ADD_PROJECT,
            title: title,
            description: description, 
            dueDate: dueDate,
        });
    }

    const handleCancleEvent = () => {
        dispatch({
            type: Actions.CANCEL_PROJECT_SELECT
        });
    }

    useEffect(() => {
        // Once this component is mounted we'll focus the title input area
        titleRef.current.focus();

        // No CleanUp function is required
        return () => {
            console.log("CleanUp is called for NewProject.");
        };
    }, []);

    return (
        <>
            <Modal ref={modal} buttonCaption="Close">
                <h2 className="text-xl font-bold text-stone-700 my-4">Invalid Input</h2>
                <p className="text-stone-600 mb-4">Please provide valid value for all the inputs.</p>
            </Modal>
            <div className="w-[50rem] mt-16">
                <menu className="flex items-center justify-end gap-4 my-4">
                    <li>
                        <button onClick={handleCancleEvent} className="text-stone-800 hover:text-stone-950">Cancel</button>     
                    </li>
                    <li>
                        <button onClick={handleSave} className="bg-stone-800 text-stone-50 hover:bg-stone-950 px-6 py-2 rounded-md">Save</button>
                    </li>
                </menu>
                <div>
                    <Input ref={titleRef} label="TITLE" placeholder="Enter Project Title"/>
                    <Input ref={descriptionRef} label="DESCRIPTION" isTextArea placeholder="Description about the project..."/>
                    <Input ref={dueDateRef} label="DUE DATE" type="date" />
                </div>
            </div>
        </>
    );
}