import { createContext, useContext, useState, useEffect } from "react";
import Login from "../components/Login";

const UserContext = createContext(null);
const userDetails = {
    username: null,
    password: null
}

export const useUser = () => {
    return useContext(UserContext);
}

const UserProvider = ({ children }) => {
    const [ inputtedUserDetails, setInputtedUserDetails ] = useState(userDetails);
    const [ authResponse, setAuthResponse ] = useState(null);

    useEffect(() => {
        async function authenicateUser() {
            await fetch("/api/v1/auth/authenticate", {
                method: "POST",
                body: JSON.stringify(inputtedUserDetails),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                }
            }).then(response => response.json())
            .then(json => {
                console.log(json);
                setAuthResponse(json);
            });
        }

        // if the username and password are not null 
        if (inputtedUserDetails.username !== null && inputtedUserDetails.password !== null) {
            authenicateUser();
        }
    }, [inputtedUserDetails]);

    if (authResponse == null) {
        return <Login setInputtedUserDetails={setInputtedUserDetails} />;
    } else {
        return(
            <UserContext.Provider value={authResponse}>
                {children}
            </UserContext.Provider>
        );
    }
}

export default UserProvider;