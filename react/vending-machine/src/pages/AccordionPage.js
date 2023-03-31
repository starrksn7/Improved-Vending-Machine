import Accordion from "../components/Accordion"
import ItemList from "../components/ItemList";
import MachineContext from "../context/machine";
import {useEffect, useContext} from 'react';

function AccordionPage() {

    const {fetchItems} = useContext(MachineContext);

    useEffect(() => {
        fetchItems();
    }, [fetchItems])

    const sections = [
        {
            id: 1,
            label: "Deposit Money",
            content: <div>Deposit Money functions go here</div>
        },
        {
            id: 2,
            label: "Purchase Items",
            content: <div><ItemList /></div>
        },
        {
            id: 3,
            label: "Get Change and Exit",
            content: <div>Give Change functions go here</div>
        }    
    ];

    

    return <Accordion sections={sections} />

}

export default AccordionPage;