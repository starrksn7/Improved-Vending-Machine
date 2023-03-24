import {useState} from 'react';
import useTransactionContext from '../context/transactions'

function DepositButton () {

    const {amount, setAmount} = useState();
    const {depositMoney} = useTransactionContext;

    const onClick = (event) =>{
        depositMoney(amount);

    };

    //This will accept the value for the button, but it needs to have the correct $$ value passed down to it from the 
    //content portion of the sections array in the AccordionPage.  I believe it can be passed in by giving the button
    //a value prop and having a specific number listed for each button.  
    return (
        <div onClick={onClick}>
            <button>Deposit ${amount}</button>
        </div>
    )
}

export default DepositButton;