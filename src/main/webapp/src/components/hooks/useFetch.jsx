import { useEffect, useState } from "react";
import { useUser } from "../../context/UserProvider";

const useFetch = () => {
    const user = useUser();
    const [url, setUrl] = useState("");
    const [requestType, setRequestType] = useState(null);
    const [payload, setPayLoad] = useState(null);
    const [response, setResponse] = useState(null);
    
    useEffect(() => {
        let valid = true;
        const callApi = () => {
            const requestDetails = {
                method: requestType,
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                    'Authorization': `Bearer ${user.token}`
                }
            };
            if (payload !== null) {
                requestDetails.body = payload;
            }
    
            fetch(url, requestDetails)
                .then(response => response.json())
                .then(json => {
                    if(valid){
                        setResponse(json);
                    };
                })
                .catch(error => console.log(error));
        };

        if (url !== "" && requestType !== "" || ((requestType === "PUT" || requestType === "POST") && payload !== null)) {
            callApi();
        }

        return () => {
            valid = false;
        }
    }, [url, requestType, payload, user]);

    return [setUrl, setRequestType, setPayLoad, response];
}

export default useFetch;