export const getServerUrl = () => {
    if (process.env.NODE_ENV === "development") {
        return "http://localhost:8080";
    }

    return window.location.origin;
};

export const getApiUrl = () => {
    const serverUrl = getServerUrl();
    return `${serverUrl}`;
}