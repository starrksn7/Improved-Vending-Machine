import useMachineContext from "../hooks/use-machine-context";

function DisplayBalance(){

    const {balance} = useMachineContext();

    const renderedBalance = balance.map((amount) => {
        return <DisplayBalance key={amount.balance} balance={balance} />
    })

    return <div>
        {renderedBalance}
    </div>
}

export default DisplayBalance;