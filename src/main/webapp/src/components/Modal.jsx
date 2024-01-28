import { forwardRef, useImperativeHandle, useRef } from "react";
import { createPortal } from "react-dom";
import Button from "./Button";

const Modal = forwardRef(function Modal({children, buttonCaption}, ref) {
    const dialog = useRef();

    // showModal() is the method provided by the html inbuilt dialog tag
    // but after using useImperativeHandle we are exposing a new method called open()
    // which can be used by other components in our project
    useImperativeHandle(ref, () => {
        return {
            open() {
                dialog.current.showModal();
            }
        };
    });

    return createPortal(
        <dialog ref={dialog} className="backdrop:bg-stone-900/90 p-4 rounded-md shadow-md">
            {children}
            <form method="dialog" className="mt-4 text-right">
                <Button>{buttonCaption}</Button>
            </form>
        </dialog>, document.getElementById('modal-root')
    );
});

export default Modal;