import { createContext, useState, useCallback } from 'react';
import axios from 'axios';

const MachineContext = createContext();

function Provider({children}) {
    const [items, setItems] = useState([]);

    const fetchItems = useCallback(async () => {
        const response = await axios.get('http://localhost:8080/items');
        setItems(response.data);
    }, []);

    const [transactions, setTransaction] = useState([]);

    const depositMoney = useCallback(async (amount) => {
        const response = await axios.post('http://localhost:8080/deposit');

        setTransaction(response.data);
    }, []);

    const makeSale = useCallback(async (itemId) => {
        const response = await axios.post('http://localhost:8080/sell');

        setTransaction(response.data);
    }, []);

    const makeChange = useCallback(async () => {
        const response = await axios.post('http://localhost:8080/makeChange');

        setTransaction(response.data);
    }, []);

    const valueToShare = {
        items,
        transactions,
        depositMoney,
        makeSale,
        makeChange,
        fetchItems
    }

    return <MachineContext.Provider value={valueToShare}>
        {children}
    </MachineContext.Provider>
}

export {Provider};
export default MachineContext;

