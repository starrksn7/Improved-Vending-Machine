import { createContext, useState, useCallback } from 'react';
import axios from 'axios';

const transactionContext = createContext();

function Provider({children}) {
    const [transaction, setTransaction] = useState([]);

    const depositMoney = useCallback(async (amount) => {
        const response = await axios.post('http://localhost:8080/deposit');

        setTransaction(response.data);
    });

    const makeSale = useCallback(async (itemId) => {
        const response = await axios.post('http://localhost:8080/sell');

        setTransaction(response.data);
    });

    const makeChange = useCallback(async () => {
        const response = await axios.post('http://localhost:8080/makeChange');

        setTransaction(response.data);
    })
}