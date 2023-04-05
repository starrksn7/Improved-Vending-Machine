import Accordion from "../components/Accordion"
import ItemList from "../components/ItemList";
import MachineContext from "../context/machine";
import DepositPage from "../components/DepositPage";
import {useEffect, useContext} from 'react';

function AccordionPage() {

    const {fetchItems, deposit} = useContext(MachineContext);

    useEffect(() => {
        fetchItems();
    }, [fetchItems])

    useEffect((amount) => {
        deposit(amount)
    }, [deposit])

    const sections = [
        {
            id: 1,
            label: "Deposit Money",
            content: <div><DepositPage /></div>
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