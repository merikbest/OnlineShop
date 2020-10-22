import axios from 'axios';

const API_BASE_URL = "http://localhost:8080";

class ShopService {

    getProducts() {
        return axios.get(API_BASE_URL + "/rest");
    }

    getProductById(id) {
        return axios.get(API_BASE_URL + "/rest/product/" + id);
    }

    login(user) {
        return axios.post(API_BASE_URL + "/rest/login", user);
    }

}

export default new ShopService();