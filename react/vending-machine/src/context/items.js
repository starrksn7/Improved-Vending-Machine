import { createContext, useState, useCallback } from 'react';
import axios from 'axios';

const itemContext = createContext();

function Provider({children}) {
    const [items, setItems] = useState([]);

    const fetchItems = useCallback(async () => {
        const response = await axios.get('http://localhost:8080/items');

        setItems(response.data);
    });

    
}