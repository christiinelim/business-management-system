const axios = require('axios');
require('dotenv').config();

let jwtToken;
const email = process.env.ADMIN_EMAIL;
const password = process.env.ADMIN_PASSWORD;

async function getToken() {
    try {
        const response = await axios.post("http://localhost:8080/api/auth/login", {
            "email": email,
            "password": password
        });
        jwtToken = response.data.data.token;
        tokenExpirationTime = new Date().getTime() + response.data.expiresIn;
    } catch (error) {
        console.error('Error fetching token:', error);
        throw error;
    }
}

async function refreshTokenIfNeeded() {
    const currentTime = new Date().getTime();
    if (!jwtToken || currentTime >= tokenExpirationTime) {
        await getToken();
    }
}

async function getAccountsData() {
    await refreshTokenIfNeeded();
    const response = await axios.get("http://localhost:8080/api/account", {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    });
    const data = response.data.data;
    return data;
}

async function getAccountData(accountId) {
    await refreshTokenIfNeeded();
    const response = await axios.get(`http://localhost:8080/api/account/${accountId}`, {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    });
    const data = response.data.data;
    return data;
}

async function getAccountItemsData(accountId){
    await refreshTokenIfNeeded();
    const response = await axios.get(`http://localhost:8080/api/item/account/${accountId}`, {
        headers: {
            'Authorization': `Bearer ${jwtToken}`
        }
    });
    const data = response.data.data;
    return data;
}


async function postCustomerData(customerInfo){
    await refreshTokenIfNeeded();
    const apiUrl = 'http://localhost:8080/api/customer';
    try {
        const response = await axios.post(apiUrl, customerInfo, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function postOrderData(orderInfo){
    await refreshTokenIfNeeded();
    const apiUrl = 'http://localhost:8080/api/order';
    try {
        const response = await axios.post(apiUrl, orderInfo, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function postPurchaseData(purchaseInfo){
    await refreshTokenIfNeeded();
    const apiUrl = 'http://localhost:8080/api/purchase';
    try {
        const response = await axios.post(apiUrl, purchaseInfo, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function getOrder(orderId){
    await refreshTokenIfNeeded();
    try {
        const response = await axios.get(`http://localhost:8080/api/purchase/order/${orderId}`, {
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = response.data.data;
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

getToken();

module.exports = {
    getAccountsData,
    getAccountData,
    getAccountItemsData,
    postCustomerData,
    postOrderData,
    postPurchaseData,
    getOrder
};