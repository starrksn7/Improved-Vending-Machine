import useMachineContext from '../hooks/use-machine-context'

function DepositButton() {


    const {deposit} = useMachineContext();

    const handleClick = (event, amount) =>{
        event.preventDefault();
        deposit(amount);


    };

    return (    
        
            <button onClick={handleClick(1)}>Deposit $1</button>
   
    )
}

export default DepositButton;