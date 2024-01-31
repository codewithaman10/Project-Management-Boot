import axios from 'axios';

export const request = (method, url, data) => {
    return axios({
        method: method,
        url: url,
        data: data
    });
}