import { useUser } from "../.../context/UserProvider";

const useFetch = (url, params, payload=null) => {
    const user = useUser();

}

export default useFetch;