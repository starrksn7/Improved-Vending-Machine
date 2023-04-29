import { createContext, useState, useCallback } from 'react';
import axios from 'axios';

const MachineContext = createContext();

function Provider({children}) {

    const [items, setItems] = useState([]);

    const fetchItems = useCallback(async () => {
        const response = await axios.get('http://localhost:8080/items');
        setItems(response.data);
    }, []);

    const[fetchedBalance, setBalance] = useState([]);
   
    const deposit = async (amount) => {
        await axios.post('http://localhost:8080/transaction/deposit', {
            transactionAmount: amount
        });
        
    };
    
    const getBalance = useCallback(async () => {
        const response = await axios.get('http://localhost:8080/bank');
        setBalance(response.data);
    }, [])

    const makeSale = useCallback(async (location) => {
        const response = await axios.post('http://localhost:8080/transaction/sell', {
            location
        });

        const updatedItems = [
            ...items,
            response.data
        ];
        setItems(updatedItems)
       

        if (location.includes("A")) {
            console.log({fetchedBalance});
            alert(`Crunch, Crunch Yum! Your balance is ${getBalance()}`);
        } else if (location.includes("B")) {
            alert(`Munch, Munch, Yum! Your balance is ${getBalance}`);
        } else if (location.contains("C")) {
            alert(`Glug, Glug Yum! Your balance is ${getBalance}`);
        } else {
            alert(`Chew, Chew Yum! Your balance is ${getBalance}`);
        } 

    }, [items, fetchedBalance, getBalance]);

    const makeChange = useCallback(async () => {
        const response = await axios.post('http://localhost:8080/transaction/makeChange');

        setBalance(response.data);
    }, []);

    const valueToShare = {
        items,
        fetchedBalance,
        deposit,
        makeSale,
        makeChange,
        fetchItems,
        getBalance
    }

    return <MachineContext.Provider value={valueToShare}>
        {children}
    </MachineContext.Provider>
}

export {Provider};
export default MachineContext;

