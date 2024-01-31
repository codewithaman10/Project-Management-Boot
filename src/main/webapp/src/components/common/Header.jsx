import Button from "./Button";

export default function Header() {

    return(
        <div className="bg-stone-700 h-14 flex relative">
            <span className="self-center ml-2 text-xl text-stone-300">Project Manager | <b>Welcome Aman</b></span>
            <button className="text-stone-300 absolute right-4 top-2 self-center hover:text-stone-950 bg-stone-500 rounded-md p-2">logout</button>
        </div>
    );
}