import axios from "axios";
import Environments from "./env-util";


const http = axios.create({
    baseURL: Environments.getEvnProp("VITE_APP_WEB_PREFIX"),
    withCredentials: true
});

export default http;


