import { createContext, useState, useCallback } from 'react';
import axios from 'axios';

const MachineContext = createContext();

function Provider({children}) {
    const [items, setItems] = useState([]);

    const fetchItems = useCallback(async () => {
        const response = await axios.get('http://localhost:8080/items');
        setItems(response.data);
    }, []);

    const[balance, setBalance] = useState([]);
   
    const deposit = async (amount) => {
        await axios.post('http://localhost:8080/transaction/deposit', {
            transactionAmount: amount
        });
        
    };
    
    const makeSale = useCallback(async (location) => {
        const response = await axios.post('http://localhost:8080/transaction/sell', {
            location
        });

        const updatedItems = [
            ...items,
            response.data
        ];
        setItems(updatedItems)
    }, [items]);

    const makeChange = useCallback(async () => {
        const response = await axios.post('http://localhost:8080/transaction/makeChange');

        setBalance(response.data);
    }, []);

    const valueToShare = {
        items,
        balance,
        deposit,
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

