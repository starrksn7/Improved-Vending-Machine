import useMachineContext from '../hooks/use-machine-context'

function DepositButton() {


    const {deposit} = useMachineContext();

    const handleDeposit = amount => event =>{
        event.preventDefault();
        deposit(amount);
    };

    return (    
        <div>
            <button onClick={handleDeposit(1)}>Deposit $1</button>
            <button onClick={handleDeposit(2)}>Deposit $2</button>
            <button onClick={handleDeposit(5)}>Deposit $5</button>
        </div>
            
   
    )
}

export default DepositButton;