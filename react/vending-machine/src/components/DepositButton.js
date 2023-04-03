import useMachineContext from '../hooks/use-machine-context'

function DepositButton() {


    const {deposit} = useMachineContext();

    const handleClick = (amount) =>{
        deposit(amount);

    };

    /* This is adding to the balance only when the accordion for the deposting money
    is opened.  the button itself isn't adding anything to the balance.  This also
    happened when there were multiple buttons and opening the deposit money div 
    added all of their amounts to the total. */
    return (<div>
        <div onClick={handleClick(1)}>
            <button>Deposit $1</button>
        </div>
        </div>
    )
}

export default DepositButton;