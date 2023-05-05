import Accordion from "../components/Accordion"
import ItemList from "../components/ItemList";
import MachineContext from "../context/machine";
import DepositButton from "../components/DepositButton";
import ChangeButton from "../components/ChangeButton";
import {useEffect, useContext} from 'react';

function AccordionPage() {

    const {fetchItems, deposit, getBalance} = useContext(MachineContext);

    useEffect((amount) => {
        fetchItems();
        deposit(amount);
        getBalance();
    }, [fetchItems, deposit, getBalance])

    const sections = [
        {
            id: 1,
            label: "Deposit Money",
            content: <div><DepositButton /></div>
        },
        {
            id: 2,
            label: "Purchase Items",
            content: <div><ItemList /></div>
        },
        {
            id: 3,
            label: "Get Change and Exit",
            content: <div><ChangeButton /> </div>
        }    
    ];

    

    return (
        <div class="accordion">
            <Accordion sections={sections} />
        </div>
    )
}

export default AccordionPage;