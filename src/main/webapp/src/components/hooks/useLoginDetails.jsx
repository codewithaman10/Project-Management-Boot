import { useEffect, useState } from "react";

const userDetails = {
    username: null,
    password: null
}

const useLoginDetails = () => {
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
    

    return [ setInputtedUserDetails, authResponse ];
}

export default useLoginDetails;